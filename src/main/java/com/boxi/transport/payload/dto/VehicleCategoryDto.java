package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleCategoryDto extends JsonBase {

    private Long id;

    @NotBlank
    private Boolean isActive;

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
