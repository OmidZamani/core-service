package com.boxi.PriceList.payload.dto;

import lombok.Data;

@Data
public class TypesOfPackageDto {
    private Long id;
    private Boolean isActive;
    private String code;
    private String name;
    private String description;
    private Double volume;
    private Double length;
    private Double width;
    private Double height;
    private Double weight;
    private Double maxAcceptableWeight;
    private Double predefinedAcceptableWeight;
}
