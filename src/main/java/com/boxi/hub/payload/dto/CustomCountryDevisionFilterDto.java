package com.boxi.hub.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class CustomCountryDevisionFilterDto extends JsonBase {
    private Long id;
    private String name;
    private String code;
    private Boolean isDeleted;
    private Boolean isActive;
    private List<CustomDevisionDetailFilterDto> customDevisionDetails;

}












