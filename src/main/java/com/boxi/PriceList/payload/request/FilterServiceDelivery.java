package com.boxi.PriceList.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
public class FilterServiceDelivery extends JsonBase {

    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
    private SelectResponse type;
    private String description;
    private SelectResponse service;
    private List<SelectResponse> saleschannels;
    private List<SelectResponse> customerSegments;
    private List<SelectResponse> serviceDeliveryCustomers;
}


