package com.boxi.hub.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;

@Data
public class HubWithLocationDto {
    private Long id;
    private String name;
    private String code;
    private String addressLine;
    private Double locLate;
    private Double locLong;
    private String addressDetail;
    private SelectResponse selectCity;
    private Boolean isPickupPossible; //امکان جمع آوری
    private Boolean isPossibleConsignmentStorage;
    private Boolean isDeliveryPossible; // امکان توزیع
    private Boolean isPossibleOrderRegistration;
    private Boolean isArrivalScanPossible;//اسکن مرسوله در ورودی هاب
}
