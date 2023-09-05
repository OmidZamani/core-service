package com.boxi.hub.repo;

import com.boxi.hub.entity.PudoStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PudoStationRepository extends JpaRepository<PudoStation, Long>, JpaSpecificationExecutor<PudoStation> {
}
