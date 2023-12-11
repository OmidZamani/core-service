package com.boxi.hub.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class ZoneHubDto implements Serializable {

    private static final long serialVersionUID = 2283187535253592219L;

    private Long hubId;
    private String hubCode;
    private String name;
    private String hubAdmin;
    private CountryDevisionSimpleDto province;
    private Double locLate;
    private Double locLong;
    private List<LocationDto> polygon;
    private SelectResponse types;
    private Boolean isActive;
    private Long vehicleId;
    private Long vehicleMdlId;

    private String color;
    private  Long polygonId;


}






