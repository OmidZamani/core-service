package com.boxi.ruleEngine.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RuleFactAction {

    private Double price=0d;
    private BigDecimal priceB=BigDecimal.ZERO;


    public void setPrice(Double price) {
        this.price = price;
        setPriceB(BigDecimal.valueOf(this.price).stripTrailingZeros());
    }
}
