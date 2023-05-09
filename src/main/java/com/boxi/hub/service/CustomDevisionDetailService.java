package com.boxi.hub.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.payload.dto.CustomDevisionDetailDto;
import com.boxi.hub.payload.request.FilterCustomDevisionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomDevisionDetailService {
    CustomDevisionDetailDto create(CustomDevisionDetailDto request);

    Page<CustomDevisionDetailDto> filter(FilterCustomDevisionDetail filter, Pageable pageable);

    SelectResponse toSelect(CustomDevisionDetail customDevisionDetail);

    CustomDevisionDetail fromSelect(SelectResponse select) ;

    Page<SelectResponse> select(String filter);

    void delete(Long id);

    CustomDevisionDetailDto edit(CustomDevisionDetailDto request);
}
