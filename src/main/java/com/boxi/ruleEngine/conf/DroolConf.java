package com.boxi.ruleEngine.conf;

import com.boxi.ruleEngine.service.ReloadDroolsService;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DroolConf {


    @Bean
    public KieContainer kieContainer(){
        return KieServices.Factory.get().getKieClasspathContainer();
    }
}