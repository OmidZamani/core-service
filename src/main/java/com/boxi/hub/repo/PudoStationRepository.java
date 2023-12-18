package com.boxi.hub.repo;

import com.boxi.hub.entity.Hub;
import com.boxi.hub.entity.PudoStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PudoStationRepository extends JpaRepository<PudoStation, Long>, JpaSpecificationExecutor<PudoStation> {


    List<PudoStation> findAllByIsDeletedFalseAndIsActiveTrueAndHub(Hub hub);
    List<PudoStation> findAllByIdInAndHubAndIsActiveIsTrueAndIsDeletedIsFalse(Collection<Long> id, Hub hub);
    List<PudoStation> findAllByIdNotInAndHubAndIsActiveIsTrueAndIsDeletedIsFalse(Collection<Long> id, Hub hub);

}
