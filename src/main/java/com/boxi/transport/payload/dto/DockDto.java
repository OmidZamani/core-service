package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Data
public class DockDto extends JsonBase {

    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private SelectResponse selectHub;

    private Boolean isActive;

}
