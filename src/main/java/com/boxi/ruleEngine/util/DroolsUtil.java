package com.boxi.ruleEngine.util;

import com.boxi.ruleEngine.entity.RuleModel;
import com.boxi.ruleEngine.repo.RuleModelRepo;
import com.boxi.utils.FindOS;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringReader;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Component

public final class DroolsUtil {

    final RuleModelRepo ruleModelRepo;


/*
  //TODO Cache
   public List<RuleModel> rules;*/

    @Autowired
    public DroolsUtil(RuleModelRepo ruleModelRepo) {
        this.ruleModelRepo = ruleModelRepo;
    }



    public KieSession getKSession(String drl) {
        try {
            KieServices ks = KieServices.Factory.get();
            String inMemoryDrlFileName = "src/main/resources/rules/"+ Calendar.getInstance().getTimeInMillis() + ".drl";
            log.warn(inMemoryDrlFileName);
            KieFileSystem kfs = ks.newKieFileSystem();
            kfs.write(inMemoryDrlFileName, ks.getResources().newReaderResource(new StringReader(drl)).setResourceType(ResourceType.DRL));
            KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
            if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
                System.err.println(kieBuilder.getResults().toString());
            }
            KieContainer kContainer = ks.newKieContainer(kieBuilder.getKieModule().getReleaseId());
            KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
            KieBase kbase = kContainer.newKieBase(kbconf);
            return kbase.newKieSession();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}