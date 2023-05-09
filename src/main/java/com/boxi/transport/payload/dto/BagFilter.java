package com.boxi.transport.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.HubPermissionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class BagFilter extends JsonBase {
    private String bagNumber;
    private SelectResponse selectBagType;
    private String consignmentsDestinationHubName;
    private SelectResponse selectsourceHub;
    private SelectResponse selectdestinationHub;
    private String carrierNumber;
    private String vehicleNumber0; //قسمت اول پلاک
    private String vehicleNumber1; //پلاک
    private String vehicleNumber2; //پلاک
    private String vehicleNumber3; //پلاک
    private List<HubPermissionDto> hublist;
    private Boolean isActive;



}
