package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.entity.TermsOfServices;
import com.boxi.hub.entity.CountryDevision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TermsOfServicesRepository extends JpaRepository<TermsOfServices, Long>, JpaSpecificationExecutor<TermsOfServices> {
    @Modifying
    @Query("delete from TermsOfServices t where t.service = ?1 ")
    void deleteByService(Services services);

    Boolean existsTermsOfServicesByServiceAndFromCityAndToCityAndFromValueAndToValueAndFromWeightAndToWeightAndFromDimAndToDimensionAndTimeCommitmentFromAndTimeCommitmentToAndTimeCommitmentTimeUnitAndFromNumberAndToNumber(Services services
            , CountryDevision fromCity, CountryDevision Tocity, BigDecimal fromValue, BigDecimal toValue, Double fromWeight
            , Double toWeight, Double fromDim, Double toDim, Double timeCommitmentFrom, Double timeCommitmentTo, Long timeCommitmentTimeUnit,
            Long fromNumber, Long toNumber);


}
