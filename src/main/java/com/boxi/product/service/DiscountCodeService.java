package com.boxi.product.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.payload.dto.DiscountCodeDto;
import com.boxi.product.payload.dto.FilterDiscountCodeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscountCodeService {
    DiscountCodeDto create(DiscountCodeDto dto);

    DiscountCodeDto edit(DiscountCodeDto dto);

    List<SelectResponse> select(String filter);

    void delete(Long id);

    DiscountCodeDto findById(Long id);

    Page<DiscountCodeDto> filter(FilterDiscountCodeDto filter, Pageable pageable);

}
