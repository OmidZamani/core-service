package com.boxi.ruleEngine.api;



import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.service.RuleExecutionService;
import org.jeasy.rules.api.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/core-service/rule-execute")

public class RuleExecuteController {

    private final RuleExecutionService ruleExecutionService;
    private final PriceListService priceListService;

    @Autowired
    public RuleExecuteController(PriceListService priceListService, RuleExecutionService ruleExecutionService) {
        this.ruleExecutionService = ruleExecutionService;
        this.priceListService = priceListService;

    }


    @GetMapping
    public Iterable<PriceList> getAll() {
        return priceListService.findAll();
    }

    @GetMapping("/process")
    public Map<Rule, Boolean> process(@RequestBody ProductPriceRequest request) {
        return ruleExecutionService.process(request);
    }
}
