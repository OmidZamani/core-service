package com.boxi.ruleEngine.engine;


import com.boxi.ruleEngine.conf.FactConverter;
import com.boxi.ruleEngine.dto.ProductPriceRequest;
import com.boxi.ruleEngine.dto.RuleFact;
import com.boxi.ruleEngine.dto.RuleFactAction;
import com.boxi.ruleEngine.dto.RulePriceResponse;
import com.boxi.ruleEngine.entity.RuleModel;
import com.boxi.ruleEngine.repo.RuleModelRepo;
import com.boxi.ruleEngine.service.ReloadDroolsService;
import com.boxi.ruleEngine.util.DroolsUtil;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RuleExecutionService {

    private final FactConverter factConverter;


    @Autowired
    private DroolsUtil droolsUtil;

    @Autowired
    private RuleModelRepo ruleModelRepo;

    @Autowired
    public RuleExecutionService(FactConverter factConverter) {
        this.factConverter = factConverter;
    }

    public RulePriceResponse process(ProductPriceRequest request) {
        Optional<RuleModel> rule = ruleModelRepo.findById(22L);
        String drl = rule.get().getContent();
        KieSession kieSession;
        kieSession = droolsUtil.getKSession(drl);
        RuleFactAction ruleFactAction = new RuleFactAction();
        RuleFact ruleFact = factConverter.convert(request);
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());
        log.warn(ruleFact.toString());
        kieSession.insert(ruleFact);
        kieSession.insert(ruleFactAction);
        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.dispose();
        return new RulePriceResponse().setPrice(ruleFactAction.getPriceB()).setFiredRulesCount(ruleFiredCount);
    }


}
