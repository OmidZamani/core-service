package com.boxi.product.repo;

import com.boxi.product.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup,Long>, JpaSpecificationExecutor<ProductGroup> {
    Boolean existsByCode(String name);

    @Modifying
    @Query("update ProductGroup p set p.isDeleted = 1 where p.id = ?1")
    void logicalDelete(Long id);

    ProductGroup findByCode(String productGroup);
}
