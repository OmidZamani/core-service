package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.ServiceDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDeliveryRepository extends JpaRepository<ServiceDelivery,Long>, JpaSpecificationExecutor<ServiceDelivery> {

    @Modifying
    @Query("update ServiceDelivery s set s.isDeleted= true  where s.id =?1")
    void LogicalDelete(Long Id);


    boolean existsByCode(String code);
}
