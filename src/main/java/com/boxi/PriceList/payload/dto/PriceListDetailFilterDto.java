package com.boxi.PriceList.payload.dto;


import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class PriceListDetailFilterDto {
    private Long id;
    private Boolean isActive;
    private Boolean isParametric;
    private Boolean isDeleted;
    private BigDecimal price;
    private String priceFormule;
    private Double fromWeight;
    private Double toWeight;
    private Double fromDim;
    private Double toDimension;
    private BigDecimal fromValue;
    private BigDecimal toValue;
    private Long fromNumber;
    private Long toNumber;
    private List<PriceDetailDevisionFilterDto> priceDetailDevisions;
    private CustomCountryDevisionDto customDevision;
    private SelectResponse product;
    private SelectResponse priceList;
    private SelectResponse consignmentType;
    private SelectResponse categoryType;


}

















