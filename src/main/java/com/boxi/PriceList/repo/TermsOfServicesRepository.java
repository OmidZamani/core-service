package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.entity.TermsOfServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsOfServicesRepository extends JpaRepository<TermsOfServices,Long> , JpaSpecificationExecutor<TermsOfServices> {
    @Modifying
    @Query("delete from TermsOfServices t where t.service = ?1 ")
    void deleteByService(Services services);

}
