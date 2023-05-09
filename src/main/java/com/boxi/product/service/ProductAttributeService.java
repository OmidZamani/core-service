package com.boxi.product.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.payload.dto.ProductAttributeDto;
import com.boxi.product.payload.request.FilterProductAttribute;
import com.boxi.product.response.ProductAttributeSelectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductAttributeService {

    Page<SelectResponse> select(String filter);

    ProductAttribute fromSelect(SelectResponse select);

    SelectResponse toSelect(ProductAttribute entity);

    List<ProductAttributeDto>  create(List<ProductAttributeDto> request);

    List<ProductAttributeDto> edit(List<ProductAttributeDto> request);



    List<ProductAttributeSelectDto>filter(FilterProductAttribute filter, Pageable pageable);

    List<ProductAttributeDto>check(FilterProductAttribute filter);

    void delete(Long id);

    ProductAttributeSelectDto SelectEdit(String filter);

    List<ProductAttributeDto> findById(Long id);

    List<ProductAttributeSelectDto> findByAttribute(Long id);
}
