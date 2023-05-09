package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.HubPermissionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterVehicle extends JsonBase {
    private String vehicleNumber0;
    private String vehicleNumber1;
    private String vehicleNumber2;
    private String vehicleNumber3;
    private String hubName;
    private String hubCode;
    private Boolean isActive;
    private String vehicleMakeSelect; //مدل
    private SelectResponse selectRoute;
    private List<HubPermissionDto> hublist;
    private SelectResponse status;
    private List<Long> transportVehicleList;
    private SelectResponse selecttype;
}
