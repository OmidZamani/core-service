package com.boxi.product.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UsingProductDto extends JsonBase {
    private Long id;
    private SelectResponse parent;
    private SelectResponse child;
    private SelectResponse productAttribute;

}
