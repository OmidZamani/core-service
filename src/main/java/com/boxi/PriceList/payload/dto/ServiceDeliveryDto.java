package com.boxi.PriceList.payload.dto;

import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceDeliveryDto
        extends JsonBase {

    private Long Id;
    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
    private SelectResponse type;
    private String description;
    private DateDto validDateFrom;
    private DateDto validDateTo;
    private SelectResponse service;
    private List<SelectResponse> saleschannels;
    private List<SelectResponse> customerSegments;
    private List<ServiceDeliveryCustomersDto> serviceDeliveryCustomers;
    private List<DeliveryDiscountDto> deliveryDiscounts;
    private BigDecimal discountPercent;

}


