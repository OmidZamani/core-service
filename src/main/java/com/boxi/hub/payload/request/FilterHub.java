package com.boxi.hub.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.hub.payload.dto.HubPermissionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterHub extends JsonBase {
    private String code;
    private String name;
    private Long hubTypeId;
    private Long hubCategoryId;
    private Long parentHubId;
    private String pinCode;
    private Date locationStartDate;
    private Boolean mandatoryArrivalScan;//اسکن مرسوله در ور.دی هاب
    private Boolean isActive;
    private Boolean dropOffAllowed; // امکان تحویل مرسوله دارد
    private Long stateId;
    private Long cityId;
    private Long regionId;
    private List<HubPermissionDto> hublist;
    private Boolean isDeliveryPossible;
    private Boolean isPossibleConsignmentStorage;
    private Boolean isPossibleOrderRegistration;
    private Boolean isPickupPossible;
}
