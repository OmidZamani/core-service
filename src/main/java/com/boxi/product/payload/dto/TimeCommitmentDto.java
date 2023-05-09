package com.boxi.product.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
public class TimeCommitmentDto extends JsonBase {

    private Long id;
    private String name;
    private String from;
    private String to;
    private SelectResponse SelecttedtimeUnit;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
}








