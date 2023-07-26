package com.boxi.ruleEngine.conf;

import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.dto.RuleFact;
import org.springframework.stereotype.Component;

@Component
public class FactConverter {


   public RuleFact convert(ProductPriceRequest request){
        RuleFact fact=new RuleFact();
        if(request.getCdt()!=null)   fact.setCdt(request.getCdt());
        if(request.getCountryDevision()!=null) fact.setCd(request.getCountryDevision());
        if(request.getWeight()!=null) fact.setW(request.getWeight());
        if(request.getServiceCode()!=null) fact.setService(request.getServiceCode());
        if(request.getTimeCommitmentDuration()!=null) fact.setT(request.getTimeCommitmentDuration());
        if(request.getTimeCommitmentFrom()!=null) fact.setAt(request.getTimeCommitmentFrom());
        return fact;
    }
}
