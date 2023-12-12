package com.boxi.transport.payload.dto;


import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = false)
@Data
public class VehicleDto extends JsonBase {

    private Long id;

    @NotBlank
    private String vehicleNumber0; //قسمت اول پلاک
    private String vehicleNumber1; //پلاک
    private String vehicleNumber2; //پلاک
    private String vehicleNumber3; //پلاک

    private Double volumeCapacity;

    private Double weightCapacity;

    private Double assignableVolume;

    private Double assignableWeight;

    @NotBlank
    private SelectResponse vehicleMakeSelect; //مدل ok

    @NotBlank
    private SelectResponse fleetTypeSelect;   // نوع ناوگان ok

    @NotBlank
    private SelectResponse selectHub;//ok

    private SelectResponse vehicleCategorySelect; //نوع وسیله ok

    private Boolean availableForLocalTrips;

    private Boolean availableForUpCountry;

    private Boolean availableForMidMile;

    private Boolean isActive;

    private SelectResponse selectStatus;

    private Boolean isOwnerShip = false;

    private SelectResponse selecttype;

    private BigDecimal allocatedWeight;

    private BigDecimal allocatedVolume;

    private String timeToStartWork;

    private String timeToFinishWork;
    private Long firstDriverId;
    private String firstDriverName;
    private Long secondDriverId;

    private Long fixedDriverId;
    private String fixedDriverName;
}
