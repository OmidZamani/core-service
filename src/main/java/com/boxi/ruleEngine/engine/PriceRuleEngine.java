package com.boxi.ruleEngine.engine;


import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.ruleEngine.conf.RuleConverter;
import com.boxi.ruleEngine.dto.RuleFact;
import com.boxi.ruleEngine.dto.RuleFactAction;
import com.boxi.ruleEngine.dto.RulePriceResponse;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PriceRuleEngine {

    private final Rules rules;
    private final RulesEngine rulesEngine;
    private final RuleModelService ruleModelService;
    private final RuleConverter ruleConverter;

    public synchronized RulePriceResponse process(RuleFact ruleFact) {
        String exMessage = "done";

        List<RuleModel> availableRules = ruleModelService.findAll();

        rules.clear();
        availableRules.forEach(rule -> {
            Rule x = ruleConverter.convertToRule(rule);
            rules.register(x);
        });
        log.warn(">>>>>>>>>>>ruleCount:" + availableRules.size());
        Facts facts = new Facts();
        RuleFactAction action = new RuleFactAction();
        log.warn("ruleFact w:" + ruleFact.getW());
        log.warn("price before:" + action.getPriceB());
        facts.put("ruleFact", ruleFact);
        facts.put("ruleFactAction", action);

        try {
            rulesEngine.fire(rules, facts);
        } catch (Exception ex) {
            ex.printStackTrace();
            exMessage = ex.getMessage();
        }

        log.warn("price after:" + action.getPrice());
        Map<Rule, Boolean> which = rulesEngine.check(rules, facts);
        return new RulePriceResponse().setPrice(action.getPriceB()).setFiredRules(which).setMessage(exMessage);

    }

}