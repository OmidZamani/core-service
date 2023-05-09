package com.boxi.PriceList.payload.dto;

import com.boxi.core.request.DateDto;
import lombok.Data;

@Data
public class FindVehicleInException {
    private Long vehicleId;
    private DateDto dateDto;
}
