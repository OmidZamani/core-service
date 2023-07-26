package com.boxi.ruleEngine.service;

import com.boxi.ruleEngine.entity.FactLookup;
import com.boxi.ruleEngine.repo.FactLookupRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FactLookupService {


    private final FactLookupRepo factLookupRepo;



    public FactLookup findByLookupCode(String code){
        return factLookupRepo.findByCode(code);
    }


    public List<FactLookup> findAll() {
        return factLookupRepo.findAll();
    }

    public FactLookup findById(Long id) {
        return factLookupRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lookup not found, id: " + id));
    }


    public List<FactLookup> init() {
        if(factLookupRepo.count()==0) {
            persist("weight", "w", "وزن به کیلو", "-", "متغیر مستقیم");
            persist("countryDevision", "cd", "استان و شهر", "/core-service/cdt-info", "-");
            persist("countryDevisionType", "cdt", "برون استانی-درون استانی-همجوار", "-", "INNER,CLOSER,OUTER");
            persist("contentType", "cct", "محتوای بسته", "-", "DOCUMENT, NON_DOCUMENT, NEWS_PAPER");
            persist("consignmentType", "cot", "نوع بسته", "-", "-");
            persist("serviceName", "service", "سرویس های نام گذاری شده", "/core-api/service/available-service-list", "-");
            persist("Timecomitment", "t", "مدت ارایه خدمت", "-", "-");
            persist("AcceptTime", "at", "'زمان قبول مرسوله", "-", "-");
            persist("v", "consignmentValue", "ارزش مرسوله", "-", "-");
            persist("np", "numberOfPiece", "تعداد مرسولات سفارش", "-", "-");
        }
        return findAll();
    }

    public void persist(String a0,String a1,String a2,String a3,String a4) {
        FactLookup lookup=new FactLookup();
        lookup.setName(a0).setCode(a1).setDescription(a2).setIsActive(true).setServiceNameIfAvailable(a3).setValuesIfAvailable(a4);
        factLookupRepo.save(lookup);
    }
}
