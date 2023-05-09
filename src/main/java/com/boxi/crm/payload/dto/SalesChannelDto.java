package com.boxi.crm.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalesChannelDto extends JsonBase {

    private Long Id;
    private Boolean isDeleted;
    private Boolean isActive;
    private String code;
    private String name;
    private String description;
}

