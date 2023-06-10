package com.boxi.product.service.impl;


import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.ProductGroup;
import com.boxi.product.payload.converter.ProductGroupConverter;
import com.boxi.product.payload.dto.ProductGroupDto;
import com.boxi.product.payload.request.FilterProduct;
import com.boxi.product.repo.ProductGroupRepository;
import com.boxi.product.service.ProductGroupService;
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


@Service
@Transactional
public class ProductGroupServiceImpl implements ProductGroupService {
    private final ProductGroupRepository productRepository;
    private final ProductGroupConverter productConvert;

    public ProductGroupServiceImpl(ProductGroupRepository productRepository, ProductGroupConverter productConvert) {
        this.productRepository = productRepository;
        this.productConvert = productConvert;
    }

    @Override
    public Page<SelectResponse> selectProduct(String filter) {

        return null;
    }

    @Override
    public ProductGroupDto createProductGroup(ProductGroupDto request) {
        request.setId(null);
        request.setIsDeleted(false);
        if (isExist(request.getCode()))
            throw BusinessException.valueException(EntityType.ProductGroup, "product.group.is.duplicate");
        ProductGroup product = productConvert.fromDtoToModel(request);
        return saveProductData(product);
    }

    @Override
    public Page<ProductGroupDto> filter(FilterProduct filter, Pageable pageable) {
        Page<ProductGroup> res = productRepository
                .findAll((Specification<ProductGroup>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

                    if (filter.getIsActive() == null)
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    else {
                        predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                    }
                    if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                        predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));
                    }
                    if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                        predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(productConvert::fromModelToDto);
    }

    @Override
    public Page<SelectResponse> select(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        Page<ProductGroup> res = productRepository
                .findAll((Specification<ProductGroup>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
                    if (filter != null && !filter.isEmpty()) {
                        predicates.add(criteriaBuilder.like(root.get("Code"), "%" + filter.trim() + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(this::toSelect);
    }


    private Boolean isExist(String code) {
        return productRepository.existsByCode(code);

    }

    @Override
    public ProductGroupDto edit(ProductGroupDto request) {

        if (! productRepository.existsById(request.getId()))
            throw BusinessException.valueException(EntityType.Product, "product.group.not.found");

        return saveData(request);
    }

    private ProductGroupDto saveData(ProductGroupDto product) {

        product.setIsDeleted(false);
        ProductGroup saved = productRepository.save(productConvert.fromDtoToModel(product));
        return productConvert.fromModelToDto(saved);

    }


    @Override
    public void delete(Long id) {
        if (findById(id) != null)
            productRepository.logicalDelete(id);
    }


    private ProductGroupDto saveProductData(ProductGroup product) {
        ProductGroup saved = productRepository.save(product);
        return productConvert.fromModelToDto(saved);
    }



    @Override
    public ProductGroup fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findById(select.getId());
    }

    @Override
    public SelectResponse toSelect(ProductGroup entity) {

        return new SelectResponse(entity.getId(), entity.selectToString());
    }


    private ProductGroup findById(Long id) {

        return productRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.ProductGroup, "product.group.not.found");
        });
    }


}
