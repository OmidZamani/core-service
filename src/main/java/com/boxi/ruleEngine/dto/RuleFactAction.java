package com.boxi.ruleEngine.dto;

import lombok.Data;

import java.math.BigDecimal;


public class RuleFactAction {

    private Double price=0d;
    private BigDecimal priceB=BigDecimal.ZERO;
    private Double helper;


    public void setPrice(Double price) {
   //     System.out.println("____________________");
        this.price = getPrice();
        setPriceB(BigDecimal.valueOf(this.price).stripTrailingZeros());
    }

    public Double getPrice() {
        return price;
    }

    public BigDecimal getPriceB() {
        return priceB;
    }

    public void setPriceB(BigDecimal priceB) {
        this.priceB = priceB;
    }

    public Double getHelper() {
        return helper;
    }

    public void setHelper(Double helper) {
        this.helper = helper;
    }
}
