package com.boxi.hub.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class CustomDevisionDetailDto extends JsonBase {
    private Long id;
    private Boolean isActive;
    private Boolean isDeleted;
    private SelectResponse customDevision;
    private SelectResponse toCountryDevision;
    private SelectResponse fromCountryDevision;
}







