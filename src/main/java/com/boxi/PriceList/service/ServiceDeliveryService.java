package com.boxi.PriceList.service;

import com.boxi.PriceList.payload.dto.*;
import com.boxi.PriceList.payload.request.DiscountRequest;
import com.boxi.PriceList.payload.request.FilterServiceDelivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceDeliveryService {

    ServiceDeliveryDto create(ServiceDeliveryDto request);

    Page<ServiceDeliveryDto> filter(FilterServiceDelivery filter, Pageable pageable);

    ServiceDeliveryDto SelectEdit(String filter);

    void delete(Long id);

    ServiceDeliveryDto edit(ServiceDeliveryDto request);

    boolean ExcelValidation(List<ServiceDeliveryExcelDto> exceptionExcelDtos);

    List<ServiceDeliveryDto> ImportExcel(List<ServiceDeliveryExcelDto> exceptionExcelDtos);

    void deleteDiscount(Long Id);

    BigDecimal findAllDiscountPercent(DiscountRequest dto);
}
