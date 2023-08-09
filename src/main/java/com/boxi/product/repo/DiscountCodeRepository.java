package com.boxi.product.repo;

import com.boxi.product.entity.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode,Long> , JpaSpecificationExecutor<DiscountCode> {
}
