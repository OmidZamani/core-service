package com.boxi.hub.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;

import java.util.List;

@Data
public class ZoneHubDto {
    private Long hubId;
    private String hubCode;
    private String name;
    private String hubAdmin;
    private CountryDevisionSimpleDto province;
    private Double locLate;
    private Double locLong;
    private List<LocationDto> polygon;
    private SelectResponse Types;
    private Boolean isActive;
    private Long vehicleId;
    private Long vehicleMdlId;
    private String color;
    private  Long polygonId;

}






