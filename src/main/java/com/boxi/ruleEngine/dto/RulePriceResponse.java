package com.boxi.ruleEngine.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Data
@Accessors(chain = true)
public class RulePriceResponse {

    int  firedRulesCount;

    private BigDecimal price;

}
