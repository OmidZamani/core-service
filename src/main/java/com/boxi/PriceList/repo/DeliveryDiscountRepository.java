package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.DeliveryDiscount;
import com.boxi.PriceList.entity.ServiceDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDiscountRepository extends JpaRepository<DeliveryDiscount,Long>, JpaSpecificationExecutor<DeliveryDiscount> {

    List<DeliveryDiscount> findByServiceDelivery(ServiceDelivery serviceDelivery);


    boolean existsByServiceDelivery(ServiceDelivery serviceDelivery);
}
