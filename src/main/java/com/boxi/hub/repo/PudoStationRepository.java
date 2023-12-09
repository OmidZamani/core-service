package com.boxi.hub.repo;

import com.boxi.hub.entity.PudoStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PudoStationRepository extends JpaRepository<PudoStation, Long>, JpaSpecificationExecutor<PudoStation> {

//    @Query(value = "SELECT * FROM TBL_PUDOSTATION WHERE PK_PUDOSTATION_ID NOT IN (?1)", nativeQuery = true)
//    List<PudoStation> findByPudoStationIdAndHubId(List<Long> pudostationIds);

}
