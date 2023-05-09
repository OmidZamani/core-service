package com.boxi.PriceList.payload.dto;

import com.boxi.PriceList.Enum.DeliveryServiceType;
import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceDeliveryExcelDto
        extends JsonBase {
    private String code;
    private String name;
    private String service;
    private String validDateFromDay;
    private String validDateFromMonth;
    private String validDateFromYear;
    private String validDateToDay;
    private String validDateToMonth;
    private String validDateToYear;
    private DeliveryServiceType type;
    private Long discountPercent;
    private Boolean isActive;
    private List<ExcelSelectResponseDto> customerSegments;
    private List<ServiceDeliveryCustomersDto> serviceDeliveryCustomers;
    private List<ExcelSelectResponseDto> saleschannels;
    private List<DeliveryDiscountExcelDto> deliveryDiscounts;

}


