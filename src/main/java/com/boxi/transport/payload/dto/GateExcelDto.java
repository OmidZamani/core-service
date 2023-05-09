package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GateExcelDto extends JsonBase {

    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private String selectHub;

}
