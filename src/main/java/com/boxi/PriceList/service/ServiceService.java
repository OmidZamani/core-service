package com.boxi.PriceList.service;

import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.PriceListDto;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.PriceList.payload.request.FilterService;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import com.boxi.hub.entity.CustomDevisionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ServiceService {

    ServiceDto create(ServiceDto request);

    Page<ServiceDto> filter(FilterService filter, Pageable pageable);

    ServiceDto SelectEdit(String filter);

    void delete(Long id);
    void deletedetails(Long id);

    ServiceDto edit(ServiceDto request);

    List<ServiceDto> ImportExcel(List<CreateServiceExcelRequest> createServiceExcelRequests);

    boolean ExcelValidation(List<CreateServiceExcelRequest> createServiceExcelRequests);

    List<SelectResponse> select(String filter);

    ServiceDto findByPricelist(PriceListDto dto);

    List<SelectResponse> baseTypeSelect();

    List<SelectResponse> additionalTypeSelect();

    SelectResponse toSelect(Services services);

    ServiceDto findByid(Long id);
}
