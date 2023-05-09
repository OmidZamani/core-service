package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.ServiceDelivery;
import com.boxi.PriceList.entity.ServiceDeliveryCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDeliveryCustomersRepository extends JpaRepository<ServiceDeliveryCustomers, Long>, JpaSpecificationExecutor<ServiceDeliveryCustomers> {


    Boolean existsByserviceDelivery(ServiceDelivery serviceDelivery);

    Boolean existsByCustomerId(Long id);

    ServiceDeliveryCustomers findByCustomerIdAndServiceDelivery(Long id, ServiceDelivery serviceDelivery);

    List<ServiceDeliveryCustomers> findAllByCustomerId(Long senderCustomerId);
}
