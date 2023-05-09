package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Data
public class BagExcelDto extends JsonBase {



    private String bagNumber;

    private String selectBagType;

    @NotBlank
    private String selectSourceHub;

//    @NotBlank
//    private String selectConsignmentsDestinationHub;

//    @NotBlank
//    private String selectDestinationHub;




}
