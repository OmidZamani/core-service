package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.transport.entity.Vehicle;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
public class BagDto extends JsonBase {

    private Long id;

    private String bagNumber;

    private SelectResponse selectBagType;

    //    @NotNull
    private SelectResponse selectSourceHub;

    //    @NotNull
    private SelectResponse selectConsignmentsDestinationHub;

    //    @NotNull
    private SelectResponse selectDestinationHub;

    private SelectResponse selectOwnerHub;

    private SelectResponse selectCarrier;

    private Boolean isActive;

    private SelectResponse status;

    private SelectResponse selecttrip;

    private SelectResponse selectCurrentHub;

    private Double weight;

    private BigDecimal weightCapacity;

    private BigDecimal volumeCapacity;

    private BigDecimal allocatedWeight;

    private BigDecimal allocatedVolume;

    private Boolean extraLoad;

    private Long extraLoadInVehicleId;
}

















