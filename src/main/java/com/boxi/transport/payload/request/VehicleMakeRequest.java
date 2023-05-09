package com.boxi.transport.payload.request;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class VehicleMakeRequest extends JsonBase {

    private Long isActive;

    private String name;

    private String code;

    private Double weightCapacity;

    private Double volumeCapacity;

    private Double consignmentCapacity;

    private  Long fuelTypeId;

    private Long vendorId;
}
