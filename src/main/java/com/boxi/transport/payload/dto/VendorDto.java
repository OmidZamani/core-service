package com.boxi.transport.payload.dto;
import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Data
public class VendorDto  extends JsonBase {
    private Long id;

    @NotBlank
    private String name;

    private Boolean isActive;

    @NotBlank
    private String code;

    private String contactNumber;

    private String nationalCode;
}
