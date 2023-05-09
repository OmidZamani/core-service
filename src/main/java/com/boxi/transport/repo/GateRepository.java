package com.boxi.transport.repo;

import com.boxi.hub.entity.Hub;
import com.boxi.transport.entity.Gate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GateRepository extends JpaRepository<Gate, Long>, JpaSpecificationExecutor<Gate> {
 Optional<Gate> findTopByHub(Hub hub);

    @Modifying
    @Query("update Gate g set g.isDeleted = 1 where g.id = ?1")
    void logicalDelete(Long id);

    Boolean existsByCodeAndIsDeletedFalse(String code);
}
