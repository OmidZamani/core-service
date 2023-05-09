package com.boxi.product.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterTimeCommitment extends JsonBase {
    private Long id;
    private String name;
    private String from;
    private String to;
    private SelectResponse timeUnit;
    private Long description;
    private Boolean isActive;
}
