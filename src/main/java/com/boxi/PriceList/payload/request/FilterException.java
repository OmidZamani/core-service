package com.boxi.PriceList.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterException extends JsonBase {

    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
}
