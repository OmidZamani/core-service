package com.boxi.product.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterUsingProduct extends JsonBase {
    private Long id;
    private Long parent;
    private Long child;
}
