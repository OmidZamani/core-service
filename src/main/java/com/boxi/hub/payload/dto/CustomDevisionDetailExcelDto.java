package com.boxi.hub.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class CustomDevisionDetailExcelDto extends JsonBase {


    private String code;
    private String toCountryDevision;
    private String fromCountryDevision;
    private Boolean isActive;
    private String customDevision;
}







