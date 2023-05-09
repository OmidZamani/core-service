package com.boxi.PriceList.payload.dto;

import com.boxi.PriceList.Enum.ExceptionType;
import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExceptionDto
extends JsonBase {
    private Long Id;
    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
    private ExceptionType type;
    private String description;
}











