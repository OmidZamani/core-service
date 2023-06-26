package com.boxi.ruleEngine.engine;


import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.ruleEngine.conf.RuleConverter;
import com.boxi.ruleEngine.dto.RuleFact;
import com.boxi.ruleEngine.dto.RuleFactAction;
import com.boxi.ruleEngine.entity.RuleModel;
import com.boxi.ruleEngine.service.RuleModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriceRuleEngine {

    private final Rules rules;
    private final RulesEngine rulesEngine;
    private final RuleModelService ruleModelService;
    private final RuleConverter ruleConverter;

    public Map<Rule, Boolean> process(RuleFact ruleFact) {

        List<RuleModel> availableRules = ruleModelService.findAll();


        availableRules.forEach(rule->{
            Rule x = ruleConverter.convertToRule(rule);
            rules.register(x);
        });
        log.warn(">>>>>>>>>>>"+availableRules.size());


        Facts facts = new Facts();
        RuleFactAction action = new RuleFactAction();
        log.warn("ruleFact:"+ruleFact.getW());
        log.warn("before:"+action.getOut());
        facts.put("ruleFact", ruleFact);
        facts.put("ruleFactAction", action);


        rulesEngine.fire(rules, facts);

        log.warn("after:"+action.getOut());
        return rulesEngine.check(rules, facts);
    }

}