package com.boxi.ruleEngine.repo;

import com.boxi.ruleEngine.entity.FactLookup;
import com.boxi.ruleEngine.entity.RuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FactLookupRepo extends JpaRepository<FactLookup, Long>, JpaSpecificationExecutor<FactLookup> {

    FactLookup findByCode(String code);
}
