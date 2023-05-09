package com.boxi.product.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.payload.dto.UsingProductDto;
import com.boxi.product.payload.request.FilterUsingProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsingProductService {
    Page<SelectResponse> select(String filter);

    UsingProduct fromSelect(SelectResponse select);

    SelectResponse toSelect(UsingProduct entity);

    UsingProductDto create(UsingProductDto request);

    UsingProductDto edit(UsingProductDto request);

    Page<UsingProductDto> filter(FilterUsingProduct filter, Pageable pageable);

    void delete(Long id);


}
