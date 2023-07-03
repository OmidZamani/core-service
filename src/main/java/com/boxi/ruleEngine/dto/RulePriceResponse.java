package com.boxi.ruleEngine.dto;


import lombok.Data;
import lombok.experimental.Accessors;
import org.jeasy.rules.api.Rule;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Accessors(chain = true)
public class RulePriceResponse {

    Map<Rule, Boolean> firedRules;

    private BigDecimal price;

    private String message="done";
}
