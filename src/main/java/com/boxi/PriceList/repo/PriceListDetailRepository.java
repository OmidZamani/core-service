package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.PriceListDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceListDetailRepository extends JpaRepository<PriceListDetail,Long>, JpaSpecificationExecutor<PriceListDetail> {

    List<PriceListDetail> findByPriceList(PriceList priceList );

    @Modifying
    void deleteByPriceList(PriceList priceList );


    Boolean existsByPriceList(PriceList priceList);
}
