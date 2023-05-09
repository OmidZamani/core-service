package com.boxi.hub.repo;

import com.boxi.PriceList.payload.dto.CountryDevisionInsDto;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.entity.HubCategory;

import com.boxi.hub.enums.CountryType;
import com.boxi.product.response.ContryDevistionSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CountryDevisionRepository  extends JpaRepository<CountryDevision, Long>, JpaSpecificationExecutor<CountryDevision> {
    CountryDevision findTopByName(String name);
    CountryDevision findTopByNameAndParent(String name,CountryDevision parent);

    @Query(value = "SELECT TC.NAME as name,TC.TYPE as countryType,TC.PK_COUNTRYDEVISION_ID as id  \n" +
            "FROM TBL_COUNTRYDEVISION tc\n" +
            "START WITH TC.PK_COUNTRYDEVISION_ID = :Id\n" +
            "Connect By PK_COUNTRYDEVISION_ID IN (PRIOR TC.FK_PARENT_ID)" ,nativeQuery = true)
    List<CountryDevisionInsDto> findTreeToParent(Long Id);

    Boolean existsByCode(String countryDevision);

    CountryDevision findByCode(String text);

    List<CountryDevision>  findByHubId(Long hubid);

    CountryDevision  findByHubIdAndCountryType(Long hubid, CountryType countryType);

    @Query(value = "select c from CountryDevision c where c.countryType in(?1) and c.hubId = ?2")
    List<CountryDevision>  findSubZone(List<CountryType> countryTypes,Long hubid);

    List<CountryDevision> findByHubIdAndParentIsNotNull(Long hubid);
    List<CountryDevision> findAllByParent(CountryDevision countryDevision);

    @Query(value ="select h from CountryDevision  c join Hub  h on c.hubId = h.id where c.countryType in(?1) and c.id =?2" )
    List<Hub> findByCityDelivery(List<CountryType> countryTypes, Long countryDevition);



}
