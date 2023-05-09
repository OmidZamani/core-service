package com.boxi.product.repo;

import com.boxi.product.entity.TimeCommitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeCommitmentRepository extends JpaRepository<TimeCommitment, Long>, JpaSpecificationExecutor<TimeCommitment> {
    @Modifying
    @Query("update TimeCommitment t set t.isDeleted = 1 where t.id = ?1")
    void logicalDelete(Long id);

    Boolean existsByid(Long code);

    boolean existsByName(String timeCommitment);

    TimeCommitment findByName(String timeCommitment);
}
