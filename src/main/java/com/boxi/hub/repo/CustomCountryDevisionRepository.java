package com.boxi.hub.repo;

import com.boxi.hub.entity.CustomCountryDevision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomCountryDevisionRepository extends JpaRepository<CustomCountryDevision,Long>, JpaSpecificationExecutor<CustomCountryDevision> {


    @Modifying
    @Query("update CustomCountryDevision c  set c.isDeleted = 1 where c.id = ?1")
    void logicalDelete(Long id);

    boolean existsByCode(String code);

    CustomCountryDevision findByCode(String customDevision);

}
