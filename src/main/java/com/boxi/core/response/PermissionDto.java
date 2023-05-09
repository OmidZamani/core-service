package com.boxi.core.response;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class PermissionDto extends JsonBase {
    private Long  id;

    @NotBlank
    private String code;
    @NotBlank
    private String name;

    private Boolean isActive;
}
