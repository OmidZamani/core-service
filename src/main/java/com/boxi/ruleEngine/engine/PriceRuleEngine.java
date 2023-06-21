package com.boxi.ruleEngine.engine;


import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.ruleEngine.dto.RuleFact;
import lombok.RequiredArgsConstructor;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PriceRuleEngine {

    private final Rules rules;
    private final RulesEngine rulesEngine;
    private final PriceListService priceListService;

    public Map<Rule, Boolean> process(RuleFact ruleFact) {
        Facts facts = new Facts();
        facts.put("ruleFact", ruleFact);
        facts.put("priceListService", priceListService);

        rulesEngine.fire(rules, facts);
        return rulesEngine.check(rules, facts);
    }

}