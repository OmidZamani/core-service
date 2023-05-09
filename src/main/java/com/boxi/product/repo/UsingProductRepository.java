package com.boxi.product.repo;

import com.boxi.PriceList.payload.dto.SuggestionPriceDto;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.UsingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsingProductRepository extends JpaRepository<UsingProduct, Long>, JpaSpecificationExecutor<UsingProduct> {
//    Boolean existsUsingProductBy(String name);


    void deleteByProductAttribute(ProductAttribute Id);

    List<UsingProduct> findByProductAttribute(ProductAttribute Id);

    List<UsingProduct> findByChild(Product product);

    @Query(value = "SELECT distinct tp.NAME,TP1.PRICE FROM TBL_USINGSPRODUCTS tu\n" +
            "    INNER JOIN TBL_PRODUCT tp ON TU.FK_CHILD_ID = tp.PK_PRODUCT_ID\n" +
            "    INNER JOIN TBL_PRICELISTDETAIL tp1 ON TP.PK_PRODUCT_ID = TP1.FK_PRODUCT_ID\n" +
            "    INNER JOIN TBL_PRICELIST otp ON TP1.FK_PRICELIST_ID = OTP.PK_PRICELIST_ID\n" +
            "    INNER JOIN TBL_SERVICE ts ON ts.FK_PRICELIST_ID= otp.PK_PRICELIST_ID\n" +
            "                                  WHERE tu.FK_PARENT_ID=?1 AND otp.ISDELETED=0;" ,nativeQuery = true)
    List<SuggestionPriceDto> SuggestDetails(Long productId);



}
