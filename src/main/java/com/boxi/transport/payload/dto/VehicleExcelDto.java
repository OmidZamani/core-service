package com.boxi.transport.payload.dto;


import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Data
public class VehicleExcelDto extends JsonBase {


    @NotBlank
    private String vehicleNumber0; //قسمت اول پلاک
    private String vehicleNumber1; //پلاک
    private String vehicleNumber2; //پلاک
    private String vehicleNumber3; //پلاک

    @NotBlank
    private String fleetTypeSelect;   // نوع ناوگان

    @NotBlank
    private String selectHub;//ok

    @NotBlank
    private String vehicleMakeSelect; //مدل ok

    private String vehicleCategorySelect; //نوع وسیله ok

    private Boolean availableForLocalTrips;

    private Boolean availableForMidMile;
    private Boolean availableForUpCountry;


    private Double volumeCapacity;
    private Double weightCapacity;

    private Boolean isActive;




}
