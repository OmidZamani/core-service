package com.boxi.transport.payload.dto;


import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = false)
@Data
public class AdmVehicleDto extends JsonBase {

    private Long id;

    @NotBlank
    private String vehicleNumber0; //قسمت اول پلاک
    private String vehicleNumber1; //پلاک
    private String vehicleNumber2; //پلاک
    private String vehicleNumber3; //پلاک


    private Double volumeCapacity;

    private Double weightCapacity;

    @NotBlank
    private SelectResponse vehicleMakeSelect; //مدل

    /*وضعیت تملک از فرم حذف گردید بنا به مستند اصلاحی مشکلات ux*/

    private SelectResponse selectRoute;

    @NotBlank
    private SelectResponse selectHub;

    //.................................

    private DateDto dayToStartWork; //persian time format

    private String timeToStartWork;

    private DateDto dayToFinishWork; //persian time format

    private String timeToFinishWork;

    private Boolean isActive;



}
