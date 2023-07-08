package com.boxi.ruleEngine.api;



import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.core.response.Response;
import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.engine.RuleExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Response process(@RequestBody ProductPriceRequest request) {
        return Response.ok().setPayload(ruleExecutionService.process(request));

    }



}
