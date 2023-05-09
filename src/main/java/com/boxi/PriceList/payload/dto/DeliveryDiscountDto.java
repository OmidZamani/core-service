package com.boxi.PriceList.payload.dto;


import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryDiscountDto {
    private Long Id;
    private Boolean isActive;
    private Boolean isDeleted;
    private SelectResponse type;
    private BigDecimal discountFrom;
    private BigDecimal discountTo;
    private BigDecimal discountPercent;
    private ServiceDeliveryDto serviceDelivery;
}






