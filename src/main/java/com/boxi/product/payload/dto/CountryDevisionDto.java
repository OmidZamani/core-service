package com.boxi.product.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.enums.CountryType;
import com.boxi.hub.payload.dto.LocationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = false)
@Data
public class CountryDevisionDto {
    private Long id;
    private String code;
    private String name;
    private SelectResponse countryType;
    private Double longtitude;
    private Double latitude;
    private SelectResponse selectResponsiblePersonel;
    private SelectResponse selectHub;
    private List<CountryDevision> childs;
    private CountryDevisionDto parent;
    private List<LocationDto> polygone;

    private String shahrCode;
    private String shahrestanCode;
    private String ostanCode;
}










