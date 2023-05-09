package com.boxi.crm.repo;

import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SegmentCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentCustomersRepository extends JpaRepository<SegmentCustomers,Long>, JpaSpecificationExecutor<SegmentCustomers> {

    void deleteBySegment(CustomerSegment customerSegment);

}
