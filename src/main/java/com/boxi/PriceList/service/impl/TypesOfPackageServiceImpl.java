package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.entity.TypesOfPackage;
import com.boxi.PriceList.payload.converter.TypesOfPackageConverter;
import com.boxi.PriceList.payload.dto.TypesOfPackageDto;
import com.boxi.PriceList.repo.TypesOfPackageRepository;
import com.boxi.PriceList.service.TypesOfPackageService;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TypesOfPackageServiceImpl implements TypesOfPackageService {

    private final TypesOfPackageRepository typesOfPackageRepository;
    private final TypesOfPackageConverter typesOfPackageConverter;

    public TypesOfPackageServiceImpl(TypesOfPackageRepository typesOfPackageRepository
            , TypesOfPackageConverter typesOfPackageConverter) {
        this.typesOfPackageRepository = typesOfPackageRepository;
        this.typesOfPackageConverter = typesOfPackageConverter;
    }

    @Override
    public TypesOfPackageDto create(TypesOfPackageDto dto) {
        return saveCreate(dto);
    }

    private TypesOfPackageDto saveCreate(TypesOfPackageDto dto) {
        TypesOfPackage typesOfPackage = typesOfPackageConverter.fromDtoToModel(dto);
        typesOfPackage.setId(null);
        typesOfPackage.setIsDeleted(false);
        return typesOfPackageConverter.fromModelToDto(typesOfPackageRepository.save(typesOfPackage));
    }

    @Override
    public TypesOfPackageDto edit(TypesOfPackageDto dto) {
        return saveEdit(dto);
    }

    private TypesOfPackageDto saveEdit(TypesOfPackageDto dto) {

        TypesOfPackage typesOfPackage = typesOfPackageConverter.fromDtoToModel(dto);
        typesOfPackage.setIsDeleted(false);
        return typesOfPackageConverter.fromModelToDto(typesOfPackageRepository.save(typesOfPackage));
    }

    private Boolean isExists(Long id) {
        return typesOfPackageRepository.existsById(id);
    }

    @Override
    public TypesOfPackageDto findById(Long id) {

        return typesOfPackageConverter.fromModelToDto(typesOfPackageRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.TypesOfPackage, "typesOfPackage.not.found");
        }));
    }

    @Override
    public void delete(Long id) {

        if (isExists(id) != null) {
            typesOfPackageRepository.deleteById(id);
        } else
            throw BusinessException.entityNotFoundException(EntityType.TypesOfPackage, "typesOfPackage.not.found");
    }

    @Override
    public Page<TypesOfPackageDto> filter(TypesOfPackageDto filter, Pageable pageable) {
        Page<TypesOfPackage> all = typesOfPackageRepository.findAll((Specification<TypesOfPackage>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

            if (filter.getIsActive() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
            }

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
            }
            if (StringUtils.hasText(filter.getCode())) {
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
        return all.map(typesOfPackageConverter::fromModelToDto);
    }

    @Override
    public List<SelectResponse> select(String filter) {

        List<TypesOfPackage> all = typesOfPackageRepository.findAll((Specification<TypesOfPackage>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            if (StringUtils.hasText(filter)) {
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + filter + "%");
                Predicate code = criteriaBuilder.like(root.get("code"), "%" + filter + "%");
                predicates.add(criteriaBuilder.or(name, code));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return all.stream().map(this::toSelect).collect(Collectors.toList());
    }

    private SelectResponse toSelect(TypesOfPackage typesOfPackage) {
        return new SelectResponse(typesOfPackage.getId(), typesOfPackage.getName());
    }
}
