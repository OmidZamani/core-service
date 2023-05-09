package com.boxi.product.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.ProductGroup;
import com.boxi.product.payload.dto.ProductGroupDto;
import com.boxi.product.payload.request.FilterProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductGroupService {

    Page<SelectResponse> selectProduct(String filter);

    ProductGroup fromSelect(SelectResponse select);

    SelectResponse toSelect(ProductGroup entity);

    ProductGroupDto createProductGroup(ProductGroupDto request);

    ProductGroupDto edit(ProductGroupDto request);

    Page<ProductGroupDto> filter(FilterProduct filter, Pageable pageable);

    void delete(Long id);

    Page<SelectResponse> select(String filter);
}
