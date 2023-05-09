package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.payload.dto.PriceDetailDevisionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDetailDevisionRepository extends JpaRepository<PriceDetailDevision,Long>, JpaSpecificationExecutor<PriceDetailDevision> {

    @Modifying
    void deleteByPriceListDetail(PriceListDetail priceListDetail);

}
