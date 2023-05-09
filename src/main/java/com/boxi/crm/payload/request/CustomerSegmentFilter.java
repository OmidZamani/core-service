package com.boxi.crm.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerSegmentFilter
        extends JsonBase {
    private Boolean isActive;
    private String code;
    private String name;
}
