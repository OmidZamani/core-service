package com.boxi.core;


import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.dto.RuleFact;
import com.boxi.ruleEngine.dto.RulePriceResponse;
import com.boxi.ruleEngine.engine.RuleExecutionService;
import com.boxi.ruleEngine.entity.RuleModel;
import com.boxi.ruleEngine.service.RuleModelService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.boxi.ruleEngine.dto.RuleFactAction;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
@SpringBootTest
@RunWith(SpringRunner.class)
public class RuleEngineTest {

    ProductPriceRequest request;

    @Autowired
    RuleExecutionService ruleExecutionService;

    @Autowired
    RuleModelService ruleModelService;


    @Test()
    @DisplayName("update rule")
    public void update() {
        String rule="package com.boxi.price;\n" +
                "\n" +
                "import com.boxi.ruleEngine.dto.RuleFact;\n" +
                "\n" +
                "rule  \"YYYY\" \n" +
                "    no-loop \n" +
                "    when   \n" +
                "       ruleFact : RuleFact( $w: w!=null );\n" +
                "    then  \n" +
                "       ruleFact.setPrice($w);  \n" +
                "       update(ruleFact); \n" +
                "end";

        RuleModel n = new RuleModel().setContent(rule);
        RuleModel saved = ruleModelService.edit(22L, n);
        assertNotNull(saved);
    }

    @Test()
    @DisplayName("update s001")
    public void updateFormula_s001() {
        // (ruleFact.service=="s001") && (ruleFact.w <= 1) && (ruleFact.cdt=="INNER")
        // (ruleFact.service=="s001") && (ruleFact.w <= 1) && (ruleFact.cdt=="CLOSER") 23400
        //(ruleFact.service=="s001") && (ruleFact.w<=1) && (ruleFact.cdt=="OUTER") 26450

        // (ruleFact.service=="s001") && (ruleFact.w>1 && ruleFact.w<=2) && (ruleFact.cdt=="INNER")
        // (ruleFact.service=="s001") && (ruleFact.w>1 && ruleFact.w<=2) && ( ruleFact.cdt=="CLOSER")
        // (ruleFact.service=="s001") && (ruleFact.w>1 && ruleFact.w<=2) && ( ruleFact.cdt=="CLOSER")


        String rule="package com.boxi.price;\n" +
                "\n" +
                "import com.boxi.ruleEngine.dto.RuleFact;\n" +
                "\n" +
                "rule  \"s001_1\" \n" +
                "    no-loop \n" +
                "    when   \n" +
                "       ruleFact : RuleFact( $w: w!=null , $w <= 1.0 , cdt ==\"INNER\");\n" +
                "    then  \n" +
                "       ruleFact.setPrice(12650);  \n" +
                "       update(ruleFact); \n" +
                "end \n" +

                "rule  \"s001_2\" \n" +
                "    no-loop \n" +
                "    when   \n" +
                "       ruleFact : RuleFact( $w: w!=null , $w <= 1.0 , cdt ==\"CLOSER\");\n" +
                "    then  \n" +
                "       ruleFact.setPrice(23400);  \n" +
                "       update(ruleFact); \n" +
                "end \n" +

                "rule  \"s001_3\" \n" +
                "    no-loop \n" +
                "    when   \n" +
                "       ruleFact : RuleFact( $w: w!=null , $w <= 1.0 , cdt ==\"OUTER\");\n" +
                "    then  \n" +
                "       ruleFact.setPrice(25200);  \n" +
                "       update(ruleFact); \n" +
                "end \n" +

                "rule  \"s001_4\" \n" +
                "    no-loop \n" +
                "    when   \n" +
                "       ruleFact : RuleFact( $w: w!=null , $w > 1.0 , $w < 2.0 , cdt ==\"INNER\");\n" +
                "    then  \n" +
                "       ruleFact.setPrice(26450);  \n" +
                "       update(ruleFact); \n" +
                "end \n" +


                "rule  \"s001_5\" \n" +
                "    no-loop \n" +
                "    when   \n" +
                "       ruleFact : RuleFact( $w: w!=null , $w > 1.0 , $w < 2.0 , cdt ==\"CLOSER\");\n" +
                "    then  \n" +
                "       ruleFact.setPrice(40300);  \n" +
                "       update(ruleFact); \n" +
                "end \n" +


                "rule  \"s001_6\" \n" +
                "    no-loop \n" +
                "    when   \n" +
                "       ruleFact : RuleFact( $w: w!=null , $w > 1.0 , $w < 2.0 , cdt ==\"OUTER\");\n" +
                "    then  \n" +
                "       ruleFact.setPrice(43400);  \n" +
                "       update(ruleFact); \n" +
                "end \n";

        RuleModel n = new RuleModel().setContent(rule);
        RuleModel saved = ruleModelService.edit(22L, n);
        assertNotNull(saved);
    }



    @Test()
    @DisplayName("stress test ss001")
    public void stress_test_s001() throws InterruptedException {

        request=new ProductPriceRequest();
        request.setServiceCode("s001").setCdt("OUTER").setWeight(1.2);

        long startTime = System.currentTimeMillis();
        for(int i=0; i <100 ;i++){
                RulePriceResponse response = ruleExecutionService.process(request);
                System.out.println(response.getPrice());
            }
        long endTime = System.currentTimeMillis();
        Long t=(endTime - startTime);
        System.out.println("executionTime:"+ t +"ms" );
        assertThat(t+"",endTime>startTime);

    }




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
