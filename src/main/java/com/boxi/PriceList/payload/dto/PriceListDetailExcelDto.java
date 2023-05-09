package com.boxi.PriceList.payload.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
public class PriceListDetailExcelDto {

    private String ParentCode;
    private String product;
    private Boolean isParametric;
    private Boolean priceFormule;
    private Boolean editor;
    private BigDecimal price;
    private Boolean isActive;
    private String consignmentType;
    private Double fromWeight;
    private Double toWeight;
    private Double fromDim;
    private Double toDimension;
    private BigDecimal fromValue;
    private BigDecimal toValue;
//    private String customDevisiontype;
    private String customDevisionPrice;
    private String customDevision;
    private String priceDetailDevisionsfrom;
    private String priceDetailDevisionsto;


}

















