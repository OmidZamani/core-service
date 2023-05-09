package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.payload.dto.VendorDto;
import com.boxi.transport.payload.dto.VendorExcelDto;
import com.boxi.core.request.GenericFilter;
import com.boxi.transport.entity.Vendor;
import com.boxi.transport.payload.converter.VendorConverter;
import com.boxi.transport.repo.VendorRepository;
import com.boxi.transport.service.VendorService;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorConverter vendorConverter;


    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, VendorConverter vendorConverter) {
        this.vendorRepository = vendorRepository;
        this.vendorConverter = vendorConverter;
    }

    @Override
    public VendorDto createVendor(VendorDto request) {
        if (isExist(request.getCode()))
            throw BusinessException.valueException(EntityType.Vendor, "vendor.is.duplicate");

        if (isExistsnationalCode(request.getNationalCode()))
            throw BusinessException.valueException(EntityType.Vendor, "vendor.national.code.duplicate");


        Vendor vendor = vendorConverter.fromDtoToModel(request);
        return saveVendorData(vendor);
    }

    public Boolean isExistsnationalCode(String nationalCode) {

        return vendorRepository.existsByNationalCodeAndIsDeletedFalse(nationalCode);
    }

    private Boolean isExist(String code) {
        return vendorRepository.existsByCodeAndIsDeletedFalse(code);
    }

    @Override
    public VendorDto updateVendor(VendorDto request) {
        Vendor vendor = vendorRepository.findById(request.getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.Vendor, "vendor.not.found");
        });
        BeanUtils.copyProperties(request, vendor);
        return saveVendorData(vendor);
    }

    private VendorDto saveVendorData(Vendor vendor) {
        Vendor saved = vendorRepository.save(vendor);
        return vendorConverter.fromModelToDto(saved);
    }

    @Override
    public Page<VendorDto> filter(GenericFilter filter, Pageable pageable) {
        Page<Vendor> res = vendorRepository
                .findAll((Specification<Vendor>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
                    if (filter.getIsActive() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                    }
                    if (filter.getSearch() != null && StringUtils.isNotBlank(filter.getSearch())) {
                        Predicate code = criteriaBuilder.like(root.get("code"), "%" + filter.getSearch().trim() + "%");
                        Predicate name = criteriaBuilder.like(root.get("name"), "%" + filter.getSearch().trim() + "%");
                        Predicate contact = criteriaBuilder.equal(root.get("nationalCode"), filter.getSearch().trim());
                        predicates.add(criteriaBuilder.or(name, code, contact));
                    }
                    predicates.add(criteriaBuilder.and(isDeleted));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(vendorConverter::fromModelToDto);
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return vendorRepository.findAll((Specification<Vendor>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
            Predicate isActive = criteriaBuilder.equal(root.get("isActive"), true);
            if (filter != null && !filter.isEmpty()) {
                Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                predicates.add(criteriaBuilder.or(name, code));
            }
            predicates.add(criteriaBuilder.and(isDeleted));
            predicates.add(criteriaBuilder.and(isActive));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }

    @Override
    public Vendor fromSelect(SelectResponse select) {
        return findById(select.getId());
    }

    @Override
    public SelectResponse toSelect(Vendor vendor) {
        return new SelectResponse(vendor.getId(), vendor.selectToString());
    }


    @Override
    public Vendor findById(Long id) {
        if (id == 0) return null;
        return vendorRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.Vendor, "vendor.is.duplicate");
        });
    }

    @Override
    public void delete(Long id) {
        vendorRepository.logicalDelete(id);
    }

    @Override
    public void createByExcel(MultipartFile excel) {
//        try {
//            List<CreateVendorExcelRequest> hubs = ExcelToPojoUtils.toPojo(CreateVendorExcelRequest.class, excel.getInputStream());
//            // bulk map to dto after validation
//        } catch (Exception e) {
//            log.error("Error in excel saving");
//            e.printStackTrace();
//            throw BusinessException.valueException(EntityType.Vendor, "vendor.is.duplicate");
//        }
    }

    @Override
    public boolean ExcelValidation(List<VendorExcelDto> vendorExcelDtos) {

        int i = 1;
        for (VendorExcelDto vendorExcelDto : vendorExcelDtos) {
            if (vendorRepository.existsByCodeAndIsDeletedFalse(vendorExcelDto.getCode()))
                throw BusinessException.valueException(EntityType.Vendor,
                        "vendor.is.duplicate",
                        vendorExcelDto.getCode() + "  ردیف " + i);

            i++;
        }
        return true;

    }

    @Override
    public List<VendorDto> ImportExcel(List<VendorExcelDto> vendorExcelDtos) {
        List<VendorDto> vendorExcelDtoslist = new ArrayList<>();
        for (VendorExcelDto vendorExcelDto : vendorExcelDtos) {
            vendorExcelDtoslist.add(createVendor(vendorConverter.fromExcelDto(vendorExcelDto)));

        }

        return vendorExcelDtoslist;
    }


}
