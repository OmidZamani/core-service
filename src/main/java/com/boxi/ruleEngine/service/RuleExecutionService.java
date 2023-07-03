package com.boxi.ruleEngine.service;


import com.boxi.ruleEngine.conf.FactConverter;
import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.dto.RuleFact;
import com.boxi.ruleEngine.dto.RulePriceResponse;
import com.boxi.ruleEngine.engine.PriceRuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RuleExecutionService {

    private final PriceRuleEngine priceRuleEngine;
    private final FactConverter factConverter;


    @Autowired
    public RuleExecutionService(PriceRuleEngine priceRuleEngine, FactConverter factConverter) {
        this.priceRuleEngine = priceRuleEngine;
        this.factConverter = factConverter;
    }

    public RulePriceResponse process(ProductPriceRequest request) {
        RuleFact fact = factConverter.convert(request);
        return priceRuleEngine.process(fact);
    }
}
