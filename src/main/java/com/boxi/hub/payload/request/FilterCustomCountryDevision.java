package com.boxi.hub.payload.request;


import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterCustomCountryDevision extends JsonBase {
    private String code;
    private String name;
    private String fromCountryDevision;
    private String toCountryDevision;
    private Boolean isActive;
    private Boolean isDeleted;


}
