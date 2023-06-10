package com.boxi.hub.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.payload.converter.CustomCountryDevisionConverter;
import com.boxi.hub.payload.converter.CustomDevisionDetailConverter;
import com.boxi.hub.payload.dto.*;
import com.boxi.hub.payload.request.FilterCustomCountryDevision;
import com.boxi.hub.repo.CustomCountryDevisionRepository;
import com.boxi.hub.repo.CustomDevisionDetailRepository;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.hub.service.CustomCountryDevisionService;
import io.micrometer.core.instrument.util.StringUtils;
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
public class CustomCountryDevisionServiceImpl implements CustomCountryDevisionService {
    private final CustomCountryDevisionRepository customCountryDevisionRepository;
    private final CustomCountryDevisionConverter customCountryDevisionConverter;


    private final CustomDevisionDetailConverter customDevisionDetailConverter;
    private final CustomDevisionDetailRepository customDevisionDetailRepository;

    private final CountryDevisionService countryDevisionService;

    public CustomCountryDevisionServiceImpl(CustomCountryDevisionRepository customCountryDevisionRepository,
                                            CustomCountryDevisionConverter customCountryDevisionConverter,
                                            CustomDevisionDetailConverter customDevisionDetailConverter,
                                            CustomDevisionDetailRepository customDevisionDetailRepository,
                                            CountryDevisionService countryDevisionService) {
        this.customCountryDevisionRepository = customCountryDevisionRepository;
        this.customCountryDevisionConverter = customCountryDevisionConverter;
        this.customDevisionDetailConverter = customDevisionDetailConverter;
        this.customDevisionDetailRepository = customDevisionDetailRepository;
        this.countryDevisionService = countryDevisionService;
    }

    @Override
    public CustomCountryDevisionDto create(CustomCountryDevisionDto request) {
        if(customCountryDevisionRepository.existsByCode(request.getCode()))
            throw BusinessException.valueException(EntityType.CustomCountryDevision,"customCountryDevisionDetails.code.is.duplicate");
        request.setId(null);
        request.setIsDeleted(false);
        CustomCountryDevision customCountryDevision = customCountryDevisionConverter.fromDtoToModel(request);
        CustomCountryDevision save = customCountryDevisionRepository.save(customCountryDevision);

        for (CustomDevisionDetailDto customDevisionDetail : request.getCustomDevisionDetails()) {

            CustomDevisionDetail customDevisionDetail1 = customDevisionDetailConverter.fromDtoToModel(customDevisionDetail);
            customDevisionDetail1.setCustomCountryDevision(save);
            customDevisionDetailRepository.save(customDevisionDetail1);

        }


        return customCountryDevisionConverter.fromModelToDto(save);
    }

    @Override
    public Page<CustomCountryDevisionFilterDto> filter(FilterCustomCountryDevision filter, Pageable pageable) {
        Page<CustomCountryDevision> all = customCountryDevisionRepository.findAll((Specification<CustomCountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            if (filter.getIsActive() == null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            else
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

            if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode() + "%"));
            }
            if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
            }
            if (filter.getFromCountryDevision() != null && StringUtils.isNotBlank(filter.getFromCountryDevision())) {
                Join<CustomCountryDevision,CustomDevisionDetail> fromCountryDevision = root.join("customDevisionDetails");
                predicates.add(criteriaBuilder.like(fromCountryDevision.get("fromCountryDevision").get("name"), "%" + filter.getFromCountryDevision() + "%"));

            }
            if (filter.getToCountryDevision() != null && StringUtils.isNotBlank(filter.getToCountryDevision())) {
                Join<CustomCountryDevision,CustomDevisionDetail> toCountryDevision = root.join("customDevisionDetails");
                predicates.add(criteriaBuilder.like(toCountryDevision.get("toCountryDevision").get("name"), "%" + filter.getToCountryDevision() + "%"));

            }

            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        }, pageable);

        return all.map(customCountryDevisionConverter::fromModelToFilter);
    }

    @Override
    public SelectResponse toSelect(CustomCountryDevision countryDivision) {
        return new SelectResponse(countryDivision.getId(), countryDivision.getName());
    }

    @Override
    public CustomCountryDevision fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findById(select.getId());
    }

    private CustomCountryDevision findById(Long id) {
        if (id == 0)
            throw BusinessException.entityNotFoundException(EntityType.CustomCountryDevision, "countrydevision.not.found");

        return customCountryDevisionRepository.findById(id).orElseThrow(() ->
        {
            throw BusinessException.entityNotFoundException(EntityType.CustomCountryDevision, "countrydevision.not.found");

        });
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        Page<CustomCountryDevision> all = customCountryDevisionRepository.findAll((Specification<CustomCountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        }, pageable);
        return all.map(this::toSelect);


    }

    @Override
    public void delete(Long id) {
        CustomCountryDevision byId = findById(id);
        if (byId == null)
            throw BusinessException.valueException(EntityType.CustomCountryDevision, "countrydevision.not.found");

        customDevisionDetailRepository.deleteByCustomCountryDevision(byId);
        customCountryDevisionRepository.logicalDelete(id);
    }

    @Override
    public CustomCountryDevisionDto edit(CustomCountryDevisionDto request) {
        CustomCountryDevision byId = findById(request.getId());
        if (byId == null)
            throw BusinessException.valueException(EntityType.CustomCountryDevision, "countrydevision.not.found");

        for (CustomDevisionDetailDto customDevisionDetail : request.getCustomDevisionDetails()) {
            customDevisionDetail.setCustomDevision(new SelectResponse(byId.getId(), byId.getName()));
            CustomDevisionDetail customDevisionDetail1 = customDevisionDetailConverter.fromDtoToModel(customDevisionDetail);
            customDevisionDetail1.setCustomCountryDevision(byId);
            customDevisionDetailRepository.save(customDevisionDetail1);
        }
        customCountryDevisionConverter.updateFromDto(request, byId);
        return Update(byId);
    }

    @Override
    public boolean ExcelValidation(List<CustomCountryDevisionExcelDto> exceptionExcelList) {

        int i = 1;
        for (CustomCountryDevisionExcelDto exceptionExcelDto : exceptionExcelList) {

            if (exceptionExcelDto != null) {
                if (customCountryDevisionRepository.existsByCode(exceptionExcelDto.getCode()) && exceptionExcelDto.getCode() != null)
                    throw BusinessException.entityNotFoundException(EntityType.CustomCountryDevision,
                            "code.exist",
                            exceptionExcelDto.getCode() + "  ردیف " + i);

                int b = 1;

                if (exceptionExcelDto.getCustomDevisionDetails() != null) {
                    for (CustomDevisionDetailExcelDto customDevisionDetail : exceptionExcelDto.getCustomDevisionDetails()) {

                        if (customDevisionDetail.getToCountryDevision() != null) {
                            if (!countryDevisionService.existsCountry(customDevisionDetail.getToCountryDevision()))
                                throw BusinessException.entityNotFoundException(EntityType.CustomCountryDevision,
                                        "contry.not.exist",
                                        exceptionExcelDto.getCode() + "  ردیف " + i + " sheet " + " customDevisionDetail " + customDevisionDetail.getToCountryDevision() + b);

                            if (!countryDevisionService.existsCountry(customDevisionDetail.getFromCountryDevision()))
                                throw BusinessException.entityNotFoundException(EntityType.CustomCountryDevision,
                                        "contry.not.exist",
                                        exceptionExcelDto.getCode() + "  ردیف " + i + " sheet " + " customDevisionDetail " + customDevisionDetail.getFromCountryDevision() + b);
                        }


                        b++;
                    }
                }

                i++;
            } else {
                exceptionExcelList.remove(i);
            }

        }


        return true;

    }

    @Override
    public List<CustomCountryDevisionDto> ImportExcel(List<CustomCountryDevisionExcelDto> customCountryDivisionList) {
        List<CustomCountryDevisionDto> customCountryDevisionDtos1 = new ArrayList<>();
        for (CustomCountryDevisionExcelDto customCountryDevisionDto : customCountryDivisionList) {
            CustomCountryDevisionDto customCountryDevisionDto1 = customCountryDevisionConverter.fromExcelToDto(customCountryDevisionDto);
            List<CustomDevisionDetailDto> customDevisionDetailDtos = new ArrayList<>();
            for (CustomDevisionDetailDto customDevisionDetail : customCountryDevisionDto1.getCustomDevisionDetails()) {
                CustomDevisionDetailDto customDevisionDetailDto = new CustomDevisionDetailDto();
                SelectResponse To = countryDevisionService.findByIdInCode(customDevisionDetail.getToCountryDevision());
                SelectResponse From = countryDevisionService.findByIdInCode(customDevisionDetail.getFromCountryDevision());
                customDevisionDetailDto.setToCountryDevision(To);
                customDevisionDetailDto.setFromCountryDevision(From);
                customDevisionDetailDto.setIsActive(customDevisionDetail.getIsActive());
                customDevisionDetailDto.setIsDeleted(false);
                customDevisionDetailDtos.add(customDevisionDetailDto);
            }
            customCountryDevisionDto1.setCustomDevisionDetails(customDevisionDetailDtos);
            customCountryDevisionDtos1.add(create(customCountryDevisionDto1));
        }

        return customCountryDevisionDtos1;

    }

    private CustomCountryDevisionDto Update(CustomCountryDevision byId) {
        byId.setIsDeleted(false);
        CustomCountryDevision save = customCountryDevisionRepository.save(byId);
        return customCountryDevisionConverter.fromModelToDto(save);
    }
}
