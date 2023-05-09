package com.boxi.product.repo;

import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.ProductAttributeDevision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributeDevisionRepository extends JpaRepository<ProductAttributeDevision,Long>, JpaSpecificationExecutor<ProductAttributeDevision> {

    Boolean existsByid(Long id);

    @Query("Select p,c,cc from ProductAttributeDevision p " +
            "join CountryDevision c on c.id = p.toCountryDevision.id " +
            "join CountryDevision cc on cc.id = p.fromCountryDevision.id " +
//            "join CountryDevision ccp on ccp.parent.id = cc.id " +
//            "join CountryDevision ccp1 on ccp1.parent.id = c.id " +
            "where p.productAttribute=:id")
    List<ProductAttributeDevision> findByProductAttribute(ProductAttribute id);

    void deleteAllByProductAttribute(ProductAttribute productAttribute);

    @Modifying
    @Query(value = " delete from ProductAttributeDevision where productAttribute=:productAttribute " )
    void deleteAllByProductAttributeDivistion(ProductAttribute productAttribute);

    void deleteByProductAttribute(ProductAttribute productAttribute);


    @Query(value = "DELETE  FROM TBL_PRODUCTATTRIBUTE   WHERE FK_PRODUCT_ID = :ID  ",nativeQuery = true)
    void deleteAllByProduct(Long ID);

}
