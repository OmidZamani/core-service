package com.boxi.product.repo;

import com.boxi.product.entity.DiscountCodesArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCodesArrangementRepository  extends JpaRepository<DiscountCodesArrangement,Long>  , JpaSpecificationExecutor<DiscountCodesArrangement> {
}
