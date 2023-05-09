package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data

public class VehicleMakeExcelDto extends JsonBase {



    private String code;

    private String name;

    private Double weightCapacity;

    private Double volumeCapacity;

    private Double consignmentCapacity;

    private String fuelTypeSelect;

    private String vendorSelect;

    private Boolean isActive;
}
