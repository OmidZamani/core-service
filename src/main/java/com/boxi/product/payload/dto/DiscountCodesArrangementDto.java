package com.boxi.product.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DiscountCodesArrangementDto {
    private Long id;
    private Short priority;
    private BigDecimal amount;
    private BigDecimal percent;
    private SelectResponse discountCode;
}




