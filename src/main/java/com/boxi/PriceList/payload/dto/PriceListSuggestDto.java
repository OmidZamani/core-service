package com.boxi.PriceList.payload.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceListSuggestDto {
    private Long pricelistid;
    private Long serviceid;
    private String servicename;
    private String serviceDescription;
    private String pricelistname;
    private BigDecimal price;


}
