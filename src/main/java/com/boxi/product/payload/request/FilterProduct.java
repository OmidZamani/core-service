package com.boxi.product.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterProduct extends JsonBase {

    private String code;
    private String name;
    private String productGroup;
    private Boolean isDeleted;
    private Boolean isActive;

}
