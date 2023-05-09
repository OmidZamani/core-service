package com.boxi.hub.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class CountryDevisionPolygonDto {
    private Long id;
    private String code;
    private String name;
    private SelectResponse countryType;
    private Double longtitude;
    private Double latitude;
    private SelectResponse selectResponsiblePersonel;
    private SelectResponse selectHub;
    private List<LocationDto> polygone;
}
