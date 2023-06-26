package com.boxi.ruleEngine.service;


import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.dto.RuleFact;
import com.boxi.ruleEngine.engine.PriceRuleEngine;
import org.jeasy.rules.api.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional
public class RuleExecutionService {

    private final PriceRuleEngine priceRuleEngine;

    @Autowired
    public RuleExecutionService(PriceRuleEngine priceRuleEngine) {
        this.priceRuleEngine = priceRuleEngine;
    }

    public Map<Rule, Boolean> process(ProductPriceRequest request) {
        RuleFact ruleFact=new RuleFact();
        if(request.getW()!=null)  ruleFact.setW(request.getW());
        if(request.getCdt()!=null) ruleFact.setCdt(request.getCdt());
       return priceRuleEngine.process(ruleFact);
    }
}
