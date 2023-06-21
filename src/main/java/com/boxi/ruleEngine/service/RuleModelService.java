package com.boxi.ruleEngine.service;

import com.boxi.ruleEngine.conf.RuleConverter;
import com.boxi.ruleEngine.entity.RuleModel;
import com.boxi.ruleEngine.repo.RuleModelRepo;
import org.jeasy.rules.api.Rules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RuleModelService {


    private final RuleModelRepo ruleModelRepo;

    private final RuleConverter ruleConverter;

    private final Rules rules;


    @Autowired
    public RuleModelService(RuleModelRepo ruleModelRepo, RuleConverter ruleConverter, Rules rules) {
        this.ruleModelRepo = ruleModelRepo;
        this.ruleConverter = ruleConverter;
        this.rules = rules;
    }

    public List<RuleModel> getAllRules(){
        return ruleModelRepo.findAll();
    }

    public RuleModel findByRuleCode(String code){
        return ruleModelRepo.findByCode(code);
    }


    public Iterable<RuleModel> findAll() {
        return ruleModelRepo.findAll();
    }

    public RuleModel findById(Long id) {
        return ruleModelRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rule not found, id: " + id));
    }

    public RuleModel save(RuleModel rule) {

        rules.register(ruleConverter.convertToRule(rule));
        try {
            return ruleModelRepo.save(rule);
        } catch (Exception e) {
            throw new IllegalArgumentException("Name should be unique, id: " + rule);
        }
    }

    public void delete(Long id) {
        ruleModelRepo.deleteById(id);
    }

}
