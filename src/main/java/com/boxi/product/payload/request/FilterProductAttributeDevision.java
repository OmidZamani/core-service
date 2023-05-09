package com.boxi.product.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.product.entity.ProductAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterProductAttributeDevision  extends JsonBase {
    private Long id;
    private Long fromCountryDevision;
    private Long toCountryDevision;
    private ProductAttribute productAttribute;
    private Boolean isActive;

}
