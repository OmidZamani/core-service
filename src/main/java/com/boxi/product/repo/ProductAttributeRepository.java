package com.boxi.product.repo;

import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.ProductAttributeDevision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute,Long>, JpaSpecificationExecutor<ProductAttribute> {
    @Override
    boolean existsById(Long aLong);

    @Query(value = "Select pat from ProductAttribute pat  where pat.product.id=:ID ")
    List<ProductAttribute> FeatchProductAttributeDevision(Long ID);



    List<ProductAttribute> findAllByProduct(Product product);



}
