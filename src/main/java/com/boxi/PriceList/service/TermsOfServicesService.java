package com.boxi.PriceList.service;

import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.PriceList.payload.dto.TermsOfServicesDto;
import com.boxi.PriceList.payload.request.FilterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TermsOfServicesService {
    TermsOfServicesDto create(TermsOfServicesDto dto);

    TermsOfServicesDto createAsService(Services services);

    TermsOfServicesDto edit(TermsOfServicesDto dto);

    Page<TermsOfServicesDto> filter(TermsOfServicesDto filter, Pageable pageable);

    void delete(TermsOfServicesDto dto);
}
