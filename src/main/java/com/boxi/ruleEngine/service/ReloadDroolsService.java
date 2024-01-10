package com.boxi.ruleEngine.service;

import com.boxi.ruleEngine.entity.RuleModel;
import com.boxi.ruleEngine.repo.RuleModelRepo;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.Calendar;
import java.util.List;

@Service
public class ReloadDroolsService {

    public static KieContainer kieContainer;

    @Autowired
    private RuleModelRepo ruleModelRepo;

    public void reload() {
        KieContainer kieContainer = loadContainerFromString(loadRules());
        this.kieContainer = kieContainer;
    }


    private List<RuleModel> loadRules() {
        List<RuleModel> rules = ruleModelRepo.findAll();
  //     System.out.println(rules.toString());
        return rules;
    }



   private KieContainer loadContainerFromString(List<RuleModel> rules) {
        long startTime = System.currentTimeMillis();
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        for (RuleModel rule : rules) {
            if(rule.getContent()!=null) {
                String drl = rule.getContent();
         //       System.out.println(">>"+drl);
                kfs.write("src/main/resources/" + System.nanoTime() + ".drl", ks.getResources().newReaderResource(new StringReader(drl)).setResourceType(ResourceType.DRL));
            }
        }

        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("execution Time to build rules : " + (endTime - startTime) + " ms");
        startTime = System.currentTimeMillis();
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
        endTime = System.currentTimeMillis();
        System.out.println("execution Time to load container: " + (endTime - startTime) + " ms");
        return kContainer;
    }


}