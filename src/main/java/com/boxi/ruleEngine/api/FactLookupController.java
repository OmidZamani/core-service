package com.boxi.ruleEngine.api;

import com.boxi.core.response.Response;
import com.boxi.ruleEngine.entity.FactLookup;
import com.boxi.ruleEngine.service.FactLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/core-service/fact-lookup")
@RequiredArgsConstructor
public class FactLookupController {

    private final FactLookupService factLookupService;

    @GetMapping("/{id}")
    public FactLookup get(@PathVariable("id") Long id) {
        return factLookupService.findById(id);
    }

    @GetMapping
    public Response getAll() {
        return  Response.ok().setPayload(factLookupService.findAll());
    }



    @PostMapping
    public Response init(){
         return  Response.ok().setPayload(factLookupService.init());

    }


}