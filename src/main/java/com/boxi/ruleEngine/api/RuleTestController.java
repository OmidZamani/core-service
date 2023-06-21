package com.boxi.ruleEngine.api;



import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.ruleEngine.engine.PriceRuleEngine;
import org.jeasy.rules.api.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/persons")

public class RuleTestController {

    private final PriceListService priceListService;
    private final PriceRuleEngine priceRuleEngine;

    @Autowired
    public RuleTestController(PriceListService priceListService, PriceRuleEngine priceRuleEngine) {
        this.priceListService = priceListService;
        this.priceRuleEngine = priceRuleEngine;
    }


    @GetMapping
    public Iterable<PriceList> getAll() {
        return priceListService.findAll();
    }

    @PostMapping("/check-rule")
    public Map<Rule, Boolean> process(@RequestBody PriceList x) {
        return priceRuleEngine.process(x);
    }
}
