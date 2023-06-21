package com.boxi.PriceList.service;

import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.SuggestionServiceDto;
import com.boxi.PriceList.payload.dto.TermsOfServicesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TermsOfServicesService {
    TermsOfServicesDto create(TermsOfServicesDto dto);

    TermsOfServicesDto createAsService(Services services);

    TermsOfServicesDto edit(TermsOfServicesDto dto);

    Page<TermsOfServicesDto> filter(TermsOfServicesDto filter, Pageable pageable);

    List<SuggestionServiceDto> detailsSuggestService(Long id );

    void delete(TermsOfServicesDto dto);

}
