package com.boxi.transport.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = false)
@Data
public class VendorRequest extends JsonBase {
    @NotNull
    private String name;

    private Boolean isActive;

    @NotNull
    private String code;

    private String contactNumber;

    private String nationalCode;
}
