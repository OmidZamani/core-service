package com.boxi.crm.repo;

import com.boxi.PriceList.entity.ServiceDelivery;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SegmentCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment,Long> , JpaSpecificationExecutor<CustomerSegment> {

//    boolean existsByServiceDelivery(ServiceDelivery serviceDelivery);
    @Modifying
    void deleteBySegmentCustomers(SegmentCustomers  segmentCustomers);


    @Modifying
    @Query(value = "update CustomerSegment c set c.isDeleted=1 where c.Id = ?1")
    void logicalDelete(Long id);


    CustomerSegment findByCode(String text);
    Boolean existsByCode(String Code);
}
