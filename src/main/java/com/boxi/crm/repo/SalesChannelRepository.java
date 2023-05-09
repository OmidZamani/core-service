package com.boxi.crm.repo;

import com.boxi.crm.entity.SalesChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesChannelRepository extends JpaRepository<SalesChannel,Long>, JpaSpecificationExecutor<SalesChannel> {


    SalesChannel findByCode(String text);


    boolean existsByCodeAndIsDeletedFalse(String code);
}
