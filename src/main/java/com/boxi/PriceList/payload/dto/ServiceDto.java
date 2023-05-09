package com.boxi.PriceList.payload.dto;


import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceDto
        extends JsonBase {
    private Long Id;
    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
    private SelectResponse type;

    @NotNull
    private String description;
    private Long minimumOrderQuantity;
    private DateDto validDateFrom;
    private DateDto validDateTo;
    private SelectResponse product;
    private SelectResponse priceList; //نرخ نامه
}












