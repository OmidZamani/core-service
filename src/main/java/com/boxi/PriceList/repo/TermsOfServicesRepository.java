package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.TermsOfServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsOfServicesRepository extends JpaRepository<TermsOfServices,Long> , JpaSpecificationExecutor<TermsOfServices> {

}
