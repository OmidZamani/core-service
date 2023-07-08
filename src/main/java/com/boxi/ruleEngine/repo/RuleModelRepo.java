package com.boxi.ruleEngine.repo;

import com.boxi.ruleEngine.entity.RuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleModelRepo extends JpaRepository<RuleModel, Long>, JpaSpecificationExecutor<RuleModel> {

    RuleModel findByCode(String code);

    Boolean existsByCode(String code);
}
