package com.boxi.ruleEngine.conf;

import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.dto.RuleFact;
import org.springframework.stereotype.Component;

@Component
public class FactConverter {


   public RuleFact convert(ProductPriceRequest request){
        RuleFact fact=new RuleFact();
        if(request.getCdt()!=null)   fact.setCdt(request.getCdt());
        if(request.getWeight()!=null) fact.setW(request.getWeight());
        if(request.getServiceCode()!=null) fact.setService(request.getServiceCode());
        return fact;
    }
}
