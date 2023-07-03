package com.boxi.core;


import com.boxi.hub.repo.HubRepository;
import com.boxi.hub.service.impl.HubServiceImpl;
import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.dto.RulePriceResponse;
import com.boxi.ruleEngine.service.RuleExecutionService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
@SpringBootTest
@RunWith(SpringRunner.class)
public class RuleEngineTest {

    ProductPriceRequest request;

    @Autowired
    RuleExecutionService ruleExecutionService;



    @Test()
    @DisplayName("116 code")
    public void testRule116() {
        request=new ProductPriceRequest();
        request.setServiceCode("s001");
        request.setCdt("OUTER").setWeight(1.2);
        RulePriceResponse response = ruleExecutionService.process(request);
        System.out.println(">>>>>>>>>"+response.getPrice());
        assertThat(response.getPrice(), Matchers.comparesEqualTo(new BigDecimal(43400)));
    }



    @Test()
    @DisplayName("117 code")
    public void testRule117() {
        request=new ProductPriceRequest();
        request.setCdt("OUTER").setWeight(0.2).setServiceCode("m001");
        RulePriceResponse response = ruleExecutionService.process(request);
        System.out.println(">>>>>>>>>"+response.getPrice());
        assertThat(response.getPrice(), Matchers.comparesEqualTo(new BigDecimal(15180)));
    }


    @Test()
    @DisplayName("120 code")
    public void testRule120() {
        request=new ProductPriceRequest();
        request.setCdt("INNER").setWeight(3.0).setServiceCode("a001");
        RulePriceResponse response = ruleExecutionService.process(request);
        System.out.println(">>>>>>>>>"+response.getPrice());
        assertThat(response.getPrice(), Matchers.comparesEqualTo(new BigDecimal(46800)));
    }


}
