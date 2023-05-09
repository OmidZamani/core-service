package com.boxi.transport.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleMakeFilter
        extends JsonBase {

    private Long id;

    private Boolean isActive;

    private String name;

    private String code;

    private Double weightCapacity;

    private Double volumeCapacity;

    private Double consignmentCapacity;

    private SelectResponse fuelTypeSelect;

    private SelectResponse vendorSelect;

}
