package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.entity.Exception;
import com.boxi.PriceList.payload.converter.ExceptionConverter;
import com.boxi.PriceList.payload.dto.ExceptionDto;
import com.boxi.PriceList.payload.dto.ExceptionExcelDto;
import com.boxi.PriceList.payload.dto.FindVehicleInException;
import com.boxi.PriceList.repo.ExceptionRepository;
import com.boxi.PriceList.payload.request.FilterException;
import com.boxi.PriceList.service.ExceptionService;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExceptionServiceImpl implements ExceptionService {

    private final ExceptionRepository exceptionRepository;
    private final ExceptionConverter exceptionConverter;

    public ExceptionServiceImpl(ExceptionRepository exceptionRepository,
                                ExceptionConverter exceptionConverter) {
        this.exceptionRepository = exceptionRepository;
        this.exceptionConverter = exceptionConverter;
    }

    @Override
    public ExceptionDto create(ExceptionDto Dto) {
        if (exceptionRepository.existsByCode(Dto.getCode()))
            throw BusinessException.valueException(EntityType.EXCEPTION, "exception.code.exist");
        return createException(Dto);
    }

    public ExceptionDto createException(ExceptionDto Dto) {
        Dto.setIsDeleted(false);
        Dto.setId(null);
        Exception exception = exceptionConverter.fromDtoToModel(Dto);
        exception.setType(Dto.getType().getValue());
        return exceptionConverter.fromModelToDto(exceptionRepository.save(exception));

    }

    @Override
    public ExceptionDto edit(ExceptionDto Dto) {
        return editException(Dto);
    }

    public ExceptionDto editException(ExceptionDto Dto) {
        if (!exceptionRepository.existsById(Dto.getId()))
            throw BusinessException.valueException(EntityType.EXCEPTION, "exception.not.found");

        Dto.setIsDeleted(false);
        Exception save = exceptionRepository.save(exceptionConverter.fromDtoToModel(Dto));
        return exceptionConverter.fromModelToDto(save);
    }

    @Override
    public void delete(Long Id) {
        if (!exceptionRepository.existsById(Id))
            throw BusinessException.valueException(EntityType.EXCEPTION, "exception.not.found");
        exceptionRepository.DeleteLogic(Id);
    }

    @Override
    public Page<ExceptionDto> filter(FilterException filter, Pageable pageable) {
        Page<Exception> all = exceptionRepository.findAll((Specification<Exception>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

            if (filter.getIsActive() != null) //TODO FRONT Is Change getIsActive to isActive
                predicates.add(criteriaBuilder.equal(root.get("isActive"), false));

            if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));
            }

            if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        }, pageable);
        return all.map(exceptionConverter::fromModelToDto);
    }

    @Override
    public List<ExceptionDto> ImportExcel(List<ExceptionExcelDto> exceptionExcelDtos) {
        List<ExceptionDto> exceptionDtos = new ArrayList<>();
        for (ExceptionExcelDto exceptionExcelDto : exceptionExcelDtos) {
            if (exceptionExcelDto.getCode() != null) {
                ExceptionDto exceptionDto = exceptionConverter.fromExcelToDto(exceptionExcelDto);
                exceptionDto.setIsDeleted(false);
                Exception save = exceptionRepository.save(exceptionConverter.fromDtoToModel(exceptionDto));
                exceptionDto.setId(save.getId());

                exceptionDtos.add(exceptionDto);
            }
        }
        return exceptionDtos;
    }

    @Override
    public boolean ExcelValidation(List<ExceptionExcelDto> exceptionExcelDtos) {
        int i = 1;
        for (ExceptionExcelDto exceptionExcelDto : exceptionExcelDtos) {

            if (exceptionRepository.existsByCode(exceptionExcelDto.getCode()))
                throw BusinessException.valueException(EntityType.EXCEPTION,
                        "exception.code.exist",
                        exceptionExcelDto.getCode() + "  ردیف " + i);

            i++;
        }


        return true;
    }

    @Override
    public Exception findById(Long id) {
        return exceptionRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.EXCEPTION, "exception.not.found");
        });
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);

        Page<Exception> all = exceptionRepository.findAll((Specification<Exception>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));

            if(StringUtils.isNotBlank(filter)){
                Predicate code = criteriaBuilder.like(root.get("code"), "%" + filter.trim() + "%");
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + filter.trim() + "%");
                predicates.add(criteriaBuilder.or(code,name));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        }, pageable);

        return all.map(this::toselect);

    }

    @Override
    public List<SelectResponse> selectByType(String filter, Long type) {
        List<Exception> all = exceptionRepository.findAll((Specification<Exception>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
            if (StringUtils.isNotBlank(filter)) {
                Predicate code = criteriaBuilder.like(root.get("code"), "%" + filter.trim() + "%");
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + filter.trim() + "%");
                predicates.add(criteriaBuilder.or(code, name));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        return all.stream().map(this::toselect).collect(Collectors.toList());


    }

    @Override
    public List<ExceptionDto> vehicleException(FindVehicleInException vehicle) {
        return null;
    }

    public SelectResponse toselect(Exception exception){
        return new SelectResponse(exception.getId(),exception.getName());
    }
}