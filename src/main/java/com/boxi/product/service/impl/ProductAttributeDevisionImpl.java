package com.boxi.product.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.Enum.TimeUnit;
import com.boxi.product.entity.ProductAttributeDevision;
import com.boxi.product.payload.converter.ProductAttributeDevisionConverter;
import com.boxi.product.payload.dto.ProductAttributeDevisionDto;
import com.boxi.product.payload.request.FilterProductAttributeDevision;
import com.boxi.product.repo.ProductAttributeDevisionRepository;
import com.boxi.product.service.ProductAttributeDevisionService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductAttributeDevisionImpl implements ProductAttributeDevisionService {
    private final ProductAttributeDevisionRepository productAttributeDevisionRepository;
    private final ProductAttributeDevisionConverter productAttributeDevisionConverter;

    public ProductAttributeDevisionImpl(ProductAttributeDevisionRepository productAttributeDevisionRepository,
                                        ProductAttributeDevisionConverter productAttributeDevisionConverter) {
        this.productAttributeDevisionRepository = productAttributeDevisionRepository;
        this.productAttributeDevisionConverter = productAttributeDevisionConverter;
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return productAttributeDevisionRepository.findAll((Specification<ProductAttributeDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }

    private ProductAttributeDevision findProductAttributeDivisionById(Long id) {
        return productAttributeDevisionRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.ProductAttributeDevision, "product.attribute.division.not.found");
        });
    }

    @Override
    public ProductAttributeDevision fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findProductAttributeDivisionById(select.getId());
    }

    @Override
    public SelectResponse toSelect(ProductAttributeDevision entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    @Override
    public ProductAttributeDevisionDto create(ProductAttributeDevisionDto request) {
        request.setId(null);
        ProductAttributeDevision productAttributeDevision = productAttributeDevisionConverter.fromDtoToModel(request);
        return createData(productAttributeDevision);
    }

    @Override
    public List<ProductAttributeDevision> saveAll(List<ProductAttributeDevision> productAttributeDivisions) {

        return productAttributeDevisionRepository.saveAll(productAttributeDivisions);

    }

    private ProductAttributeDevisionDto createData(ProductAttributeDevision productAttributeDevision) {
        productAttributeDevision.setIsDeleted(false);
        ProductAttributeDevision saved = productAttributeDevisionRepository.save(productAttributeDevision);
        return productAttributeDevisionConverter.fromModelToDto(saved);
    }

    private ProductAttributeDevision findById(Long id) {

        return productAttributeDevisionRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.ProductAttributeDevision, "product.attribute.division.not.found");
        });
    }



    @Override
    public ProductAttributeDevisionDto edit(ProductAttributeDevisionDto request) {
        ProductAttributeDevision productAttributeDevision = findById(request.getId());

        productAttributeDevisionConverter.updateFromDto(request, productAttributeDevision);
        return saveData(productAttributeDevision);
    }

    private ProductAttributeDevisionDto saveData(ProductAttributeDevision productAttributeDevision) {
        ProductAttributeDevision saved = productAttributeDevisionRepository.save(productAttributeDevision);
        return productAttributeDevisionConverter.fromModelToDto(saved);
    }

    @Override
    public Page<ProductAttributeDevisionDto> filter(FilterProductAttributeDevision filter, Pageable pageable) {
        Page<ProductAttributeDevision> res = productAttributeDevisionRepository
                .findAll((Specification<ProductAttributeDevision>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    if (filter.getIsActive() == null)
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    else {
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                    }

                    if (filter.getProductAttribute() != null && StringUtils.isNotBlank(filter.getProductAttribute().getProduct().getName())) {
                        predicates.add(criteriaBuilder.equal(root.get("timeUnit"), Objects.requireNonNull(TimeUnit.findByFa(filter.getProductAttribute().getProduct().getName())).getValue()));

                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(productAttributeDevisionConverter::fromModelToDto);
    }

    @Override
    public void delete(Long id) {
        productAttributeDevisionRepository.deleteById(id);

    }
}
