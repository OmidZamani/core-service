package com.boxi.PriceList.payload.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailableDiscountResponse {

    private Long serviceId;
    private String txt;
    private BigDecimal discount;

}
