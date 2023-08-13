package com.boxi.transport.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleMake;
import com.boxi.transport.entity.Vendor;
import com.boxi.transport.enums.FuelType;
import com.boxi.transport.payload.converter.VehicleMakeConverter;
import com.boxi.transport.payload.dto.VehicleMakeDto;
import com.boxi.transport.payload.dto.VehicleMakeExcelDto;
import com.boxi.transport.payload.request.VehicleMakeFilter;
import com.boxi.transport.repo.VehicleMakeRepository;
import com.boxi.transport.repo.VendorRepository;
import com.boxi.transport.service.VehicleMakeService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehicleMakeServiceImpl implements VehicleMakeService {

    private final VehicleMakeRepository vehicleMakeRepository;
    private final VehicleMakeConverter vehicleMakeConverter;
    private final VendorRepository vendorRepository;


    @Autowired
    public VehicleMakeServiceImpl(VehicleMakeRepository vehicleMakeRepository, VehicleMakeConverter vehicleMakeConverter, VendorRepository vendorRepository) {
        this.vehicleMakeRepository = vehicleMakeRepository;
        this.vehicleMakeConverter = vehicleMakeConverter;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return vehicleMakeRepository.findAll((Specification<VehicleMake>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            if (filter != null && !filter.isEmpty()) {
                Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                predicates.add(criteriaBuilder.or(name, code));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }

    @Override
    public Page<VehicleMakeDto> filter(VehicleMakeFilter filter, Pageable pageable) {
        Page<VehicleMake> res = vehicleMakeRepository
                .findAll((Specification<VehicleMake>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    if (filter.getIsActive() != null) {
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                    }
                    if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode()))
                        predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%")));

                    if (filter.getName() != null && StringUtils.isNotBlank(filter.getName()))
                        predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));

                    if (filter.getFuelTypeSelect() != null )
                        predicates.add(criteriaBuilder.equal(root.get("fuelType"),filter.getFuelTypeSelect().getId()));

                    if (filter.getConsignmentCapacity() != null )
                        predicates.add(criteriaBuilder.equal(root.get("consignmentCapacity"),filter.getConsignmentCapacity()));

                    if (filter.getWeightCapacity() != null )
                        predicates.add(criteriaBuilder.equal(root.get("weightCapacity"),filter.getWeightCapacity()));

                    if (filter.getVolumeCapacity() != null)
                        predicates.add(criteriaBuilder.equal(root.get("volumeCapacity"), filter.getVolumeCapacity()));

                    if(filter.getVendorSelect()!=null) {
                        Join<VehicleMake, Vendor> vehicleMakeVehicleJoin = root.join("vendor");
                        predicates.add(criteriaBuilder.equal(vehicleMakeVehicleJoin.get("id"), filter.getVendorSelect().getId()));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(vehicleMakeConverter::fromModelToDto);

    }


    @Override
    public VehicleMakeDto create(VehicleMakeDto request) {
        if (vehicleMakeRepository.existsByCodeAndIsDeletedFalse(request.getCode()))
            throw BusinessException.valueException(EntityType.VehicleMake, "vehicle.make.is.duplicate");

        VehicleMake entity = vehicleMakeConverter.fromDtoToModel(request);
        entity.setIsDeleted(false);
        return saveVehicleMakeData(entity);
    }

    @Override
    public VehicleMakeDto edit(VehicleMakeDto request) {
        VehicleMake vehicleMake = vehicleMakeRepository.findById(request.getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.VehicleMake, "vehicle.make.not.found");
        });
        vehicleMakeConverter.updateFromDto(request, vehicleMake);
        return saveVehicleMakeData(vehicleMake);
    }

    private VehicleMakeDto saveVehicleMakeData(VehicleMake vehicleMake) {
        VehicleMake saved = vehicleMakeRepository.save(vehicleMake);
        return vehicleMakeConverter.fromModelToDto(saved);
    }

    @Override
    public VehicleMake findById(Long id) {
        if (id == 0)
            throw BusinessException.valueException(EntityType.VehicleMake, "vehicle.make.not.found");

        return vehicleMakeRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.VehicleMake, "vehicle.make.not.found");
        });
    }

    @Override
    public VehicleMake fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findById(select.getId());
    }

    @Override
    public SelectResponse toSelect(VehicleMake entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    @Override
    public void delete(Long id) {
        vehicleMakeRepository.logicalDelete(id);
    }

    @Override
    public VehicleMakeDto get(Long id) {
        VehicleMake o = findById(id);
        return vehicleMakeConverter.fromModelToDto(o);
    }

    @Override
    public boolean ExcelValidation(List<VehicleMakeExcelDto> vehicleMakeExcelList) {
        int i = 0;
        for (VehicleMakeExcelDto vehicleMakeExcelDto : vehicleMakeExcelList) {
            if (vehicleMakeRepository.existsByCodeAndIsDeletedFalse(vehicleMakeExcelDto.getCode()))
                throw BusinessException.valueException(EntityType.VehicleMake,
                        "vehicle.make.is.duplicate",
                        vehicleMakeExcelDto.getCode() + "  ردیف " + i);
            if(vehicleMakeExcelDto.getVolumeCapacity()<0 || vehicleMakeExcelDto.getWeightCapacity()<0)
                throw BusinessException.valueException(EntityType.VehicleMake,
                        "vehicle.make.is.not.mines.value",
                        vehicleMakeExcelDto.getVolumeCapacity() + "  ردیف " + i);
            i++;
        }

        return true;
    }

    @Override
    public List<VehicleMakeDto> ImportExcel(List<VehicleMakeExcelDto> vehicleMakeExcelList) {

        List<VehicleMakeDto> vehicleDtos = new ArrayList<>();
        for (VehicleMakeExcelDto vehicleMakeExcelDto : vehicleMakeExcelList) {
            VehicleMakeDto vehicleMakeDto = vehicleMakeConverter.fromExcelToDto(vehicleMakeExcelDto);
            Vendor byCode = vendorRepository.findByCode(vehicleMakeExcelDto.getVendorSelect());
            vehicleMakeDto.setVendorSelect(new SelectResponse(byCode.getId(), byCode.getName()));
            FuelType fuelType = FuelType.findByfa(vehicleMakeExcelDto.getFuelTypeSelect().trim());
            vehicleMakeDto.setFuelTypeSelect(new SelectResponse(fuelType.getValue(), fuelType.getFa()));

            vehicleDtos.add(create(vehicleMakeDto));
        }
        return vehicleDtos;
    }


}
