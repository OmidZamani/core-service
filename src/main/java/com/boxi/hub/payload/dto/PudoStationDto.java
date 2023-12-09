package com.boxi.hub.payload.dto;

import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import lombok.Data;

@Data
public class PudoStationDto extends JsonBase {
    private Long id;
    private Boolean isActive;
    private String code;
    private String name;
    private DateDto locationStartDate;
    private Double locLate;
    private Double locLong;
    private String addressLine;
    private String addressDetail;
    private Boolean isPickupPossible;
    private Boolean isDeliveryPossible;
    private Long hubId;
}
