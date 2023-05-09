package com.boxi.transport.payload.dto;
import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Data
public class VendorExcelDto extends JsonBase {


    @NotBlank
    private String code;
    @NotBlank
    private String name;

    private String nationalCode;

    private String contactNumber;

    private Boolean isActive;

}
