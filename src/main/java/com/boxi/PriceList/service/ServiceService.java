package com.boxi.PriceList.service;

import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.PriceListDto;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.PriceList.payload.dto.ServiceNameWithCodeDto;
import com.boxi.PriceList.payload.request.FilterService;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;


public interface ServiceService {

    ServiceDto create(ServiceDto request);

    Page<ServiceDto> filter(FilterService filter, Pageable pageable);

    ServiceDto SelectEdit(String filter);

    void delete(Long id);

    void deleteDetails(Long id);

    ServiceDto edit(ServiceDto request);

    List<ServiceDto> ImportExcel(List<CreateServiceExcelRequest> createServiceExcelRequests);

    boolean ExcelValidation(List<CreateServiceExcelRequest> createServiceExcelRequests);

    List<SelectResponse> select(String filter);

    ServiceDto findByPriceList(PriceListDto dto);

    List<SelectResponse> baseTypeSelect();

    List<SelectResponse> additionalTypeSelect();

    SelectResponse toSelect(Services services);

    ServiceDto findById(Long id);

    List<ServiceNameWithCodeDto> serviceNameWithCode();

    BigDecimal findByDefaultServicePrice(Long id);
}
