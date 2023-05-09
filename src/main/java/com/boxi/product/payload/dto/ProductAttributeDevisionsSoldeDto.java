package com.boxi.product.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ProductAttributeDevisionsSoldeDto {
    private Long id;
    private CountryDevision fromCountryDevision;
    private CountryDevision toCountryDevision;
    private SelectResponse productAttribute;
}
