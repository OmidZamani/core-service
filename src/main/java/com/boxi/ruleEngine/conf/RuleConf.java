package com.boxi.ruleEngine.conf;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RuleConf {

    @Bean
    public Rules rules() {
        return new Rules();
    }

    @Bean
    public RulesEngine rulesEngine() {
        return new DefaultRulesEngine();
    }

 /*   @Bean
    public List<CustomRuleAction> customRuleAction() {
        return List.of(
                new CustomRuleAction("personCanHaveInsurance",
                        "personService.personCanHaveInsurance(person);"));
    }*/
}