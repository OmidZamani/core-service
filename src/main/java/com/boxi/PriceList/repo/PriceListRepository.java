package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.PriceList;
import com.boxi.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository  extends JpaRepository<PriceList,Long>, JpaSpecificationExecutor<PriceList> {

    @Modifying
    @Query("update PriceList p set p.isDeleted = 1 where p.id = ?1")
    void DeleteLogic(Long Id);

    PriceList findByPriceListCode(String PriceListName);
    PriceList findByIdAndIsActiveIsTrue(Long id);

    boolean existsByPriceListCode(String priceListCode);

    PriceList findByIdAndIsActiveIsTrueAndIsDeletedIsFalse(Long id);

    Boolean existsByPriceListDetails_Product(Product product);


}
