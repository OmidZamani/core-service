package com.boxi.product.repo;


import com.boxi.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {





    boolean existsBycode(String Code);

    @Modifying
    @Query("update Product p set p.isDeleted = 1 where p.id = ?1")
    void logicalDelete(Long id);


    Product  findByCode(String Code);

    boolean existsByCode(String code);
    boolean existsByCodeAndIsDeletedFalse(String code);

}
