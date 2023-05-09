package com.boxi.PriceList.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExceptionExcelDto
extends JsonBase {


    private String code;
    private String name;
    private String type;
    private String description;
    private Boolean isActive;
}











