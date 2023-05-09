package com.boxi.product.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.ProductAttributeDevision;
import com.boxi.product.payload.dto.ProductAttributeDevisionDto;
import com.boxi.product.payload.request.FilterProductAttributeDevision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductAttributeDevisionService {
    Page<SelectResponse> select(String filter);

    ProductAttributeDevision fromSelect(SelectResponse select);

    SelectResponse toSelect(ProductAttributeDevision entity);

    ProductAttributeDevisionDto create(ProductAttributeDevisionDto request);

    List<ProductAttributeDevision> saveAll(List<ProductAttributeDevision> productAttributeDevisions);

    ProductAttributeDevisionDto edit(ProductAttributeDevisionDto request);

    Page<ProductAttributeDevisionDto> filter(FilterProductAttributeDevision filter, Pageable pageable);

    void delete(Long id);
}
