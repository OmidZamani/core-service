package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.SuggestDetailServiceInfDto;
import com.boxi.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long>, JpaSpecificationExecutor<Services> {

    Boolean existsByPriceList(PriceList priceList);

    Boolean existsByCode(String Code);

    Services findByCode(String text);

    Services findByPriceList(PriceList priceList);

    List<Services> findByTypeAndIsActiveIsTrue(Long type);

    Services findTopByPriceListAndProductAndType(PriceList priceList, Product product, Long serviceType);

    @Query(value = "SELECT  tp1.PRICE as price,tp1.fk_product_id as productId,tp1.fk_pricelist_id as priceListId,otp.validdatefrom,otp.validdateto\n" +
            "  FROM TBL_PRICELISTDETAIL tp1\n" +
            " INNER JOIN TBL_PRICELIST otp\n" +
            "    ON TP1.FK_PRICELIST_ID = OTP.PK_PRICELIST_ID\n" +
            " WHERE otp.ISDELETED = 0 and tp1.isdeleted=0 and otp.isactive=1 and tp1.isactive=1\n" +
            "   AND tp1.FK_PRODUCT_ID IN\n" +
            "       (SELECT tp.PK_PRODUCT_ID\n" +
            "          FROM TBL_USINGSPRODUCTS tu\n" +
            "         INNER JOIN TBL_PRODUCT tp\n" +
            "            ON TU.FK_CHILD_ID = tp.PK_PRODUCT_ID\n" +
            "         WHERE tu.FK_CHILD_ID = ?1 AND ?2 BETWEEN otp.validdatefrom AND otp.validdateto)",nativeQuery = true)
    List<SuggestDetailServiceInfDto> getsuggestDetails(Long productId , Date date);
}
