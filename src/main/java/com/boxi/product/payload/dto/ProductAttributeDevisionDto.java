package com.boxi.product.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.response.ContryDevistionSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ProductAttributeDevisionDto extends JsonBase{

    private Long id;
    private Boolean isActive;
    private Boolean isDeleted;
    private ContryDevistionSelect fromCountryDevision;
    private ContryDevistionSelect toCountryDevision;
    private SelectResponse productAttribute;


}
