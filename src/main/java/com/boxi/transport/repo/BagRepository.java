package com.boxi.transport.repo;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.transport.entity.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BagRepository extends JpaRepository<Bag, Long>, JpaSpecificationExecutor<Bag> {

    Boolean existsByBagNumberAndIsDeletedFalse(String code);

    @Modifying
    @Query("update Bag b set b.isDeleted = 1 where b.id = ?1")
    void logicalDelete(Long id);

    boolean existsByBagNumber(String bagNumber);

    Bag findByBagNumber(String Code);

    @Query(value = "select count(b.status )as id,b.status as text  from Bag  b  where b.currentHub = ?1 and b.status is not null group by b.status")
    List<String> totalReport(Hub hub);
}
