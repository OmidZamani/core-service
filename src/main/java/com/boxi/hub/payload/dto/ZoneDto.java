package com.boxi.hub.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;

import java.util.List;

@Data
public class ZoneDto extends JsonBase {
    private SelectResponse selectHub;
    private SelectResponse selectuser;
    private SelectResponse countrydevision;
    private SelectResponse countrytype;
    private String polygon;
    private List<SelectResponse> consignmentList;
    private Long pudoExecutationId;
    private Long pudoVehicleId;
    private Long pudoVehiclePlanId;
    private Long hubGeoId;
    private String color;


}
