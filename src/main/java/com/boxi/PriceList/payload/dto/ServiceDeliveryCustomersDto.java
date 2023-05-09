package com.boxi.PriceList.payload.dto;


import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceDeliveryCustomersDto {

    private Long Id;
    private Long customerId;
    private String customerName;
    private String text;
    private SelectResponse serviceDelivery;
}


