package com.boxi.ruleEngine.service;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.ruleEngine.conf.RuleConverter;
import com.boxi.ruleEngine.entity.RuleModel;
import com.boxi.ruleEngine.repo.RuleModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RuleModelService {


    private final RuleModelRepo ruleModelRepo;

    private final RuleConverter ruleConverter;



    @Autowired
    public RuleModelService(RuleModelRepo ruleModelRepo, RuleConverter ruleConverter) {
        this.ruleModelRepo = ruleModelRepo;
        this.ruleConverter = ruleConverter;
    }

    public List<RuleModel> getAllRules(){
        return ruleModelRepo.findAll();
    }

    public RuleModel findByRuleCode(String code){
        return ruleModelRepo.findByCode(code);
    }


    public List<RuleModel> findAll() {
        return ruleModelRepo.findAll();
    }

    public RuleModel findById(Long id) {
        return ruleModelRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rule not found, id: " + id));
    }

    public RuleModel save(RuleModel rule) {

        if(ruleModelRepo.existsByCode(rule.getCode())){
            throw BusinessException.valueException(EntityType.RULEMODEL, "code.exist");
        }
       rule.setIsDeleted(false);
       return ruleModelRepo.save(rule);

    }

    public void delete(Long id) {
        ruleModelRepo.deleteById(id);
    }

    public RuleModel edit(Long id, RuleModel ruleModel) {
        RuleModel old = findById(id);
        old.setContent(ruleModel.getContent());
        return ruleModelRepo.save(old);
    }
}
