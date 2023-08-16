package com.boxi.hub.repo;


import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.enums.HubType;
import com.boxi.hub.payload.dto.ZonehubInterfaceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public interface HubRepository extends JpaRepository<Hub, Long>, JpaSpecificationExecutor<Hub> {

    List<Hub> findAllByCityAndIsActiveIsTrueAndIsDeletedIsFalse(CountryDevision city);

    Hub findByCode(String code);

    @Query(value = "select h from Hub  h where h.code in(?1) and h.isDeleted = false and h.isActive = true")
    List<Hub> findallByCodeList(List<String> codes);

    Hub findByCodeAndParentHubIsNotNull(String code);


    List<Hub> findByCodeIn(List<String> codes);

    List<Hub> findByIdIn(List<Long> ids);


    @Query("select h from Hub h where  h.code like ?1")
    List<Hub> findByCodeLike(String Code);


    Boolean existsByName(String name);

    Boolean existsByCodeAndIsDeletedFalse(String name);

    Hub findTopByCode(String parentHub);

    List<Hub> findTopByParentHubCode(String parentHub);

    List<Hub> findAllByParentHubCode(String parentHub);

    Boolean existsByLocLateAndLocLong(Double loclat, Double loclong);

    @Modifying
    @Query("update Hub h set h.isDeleted = 1 where h.id = ?1")
    void logicalDelete(Long id);

    Hub findTopByOrderByIdDesc();

    Hub findTopByParentHubOrderByIdDesc(SelectResponse selectParentHub);


    @Query("SELECT o.id As id,o.name as name FROM Hub o WHERE o.isActive=true And o.isDeleted = false And o.id IN :ids")
    List<Object[]> fetchByIds(@Param("ids") List<Long> ids);

    default Map<Long, String> fetchHubsMapByIds(@Param("ids") List<Long> ids) {

        return fetchByIds(ids)
                .stream()
                .collect(
                        Collectors.toMap(
                                t -> ((long) t[0]),
                                t -> ((String) t[1])
                        )
                );
    }

    @Query(value = "SELECT t.HUB_ID as HUB,SDO_UTIL.TO_WKTGEOMETRY(t.shape) POLYGON\n" +
            "      from HUB_GEO t\n" +
            "     where t.is_active = 1", nativeQuery = true)
    List<ZonehubInterfaceDto> listofZone();

    @Query(value = "SELECT t.HUB_ID as hub,SDO_UTIL.TO_WKTGEOMETRY(t.shape) as polygon\n" +
            "      from HUB_GEO t  " +
            " join TBL_Hub tb on tb.PK_HUB_ID = t.HUB_ID" +
            " JOIN TBL_COUNTRYDEVISION tc ON t.FK_COUNTRYDEVISION_ID = TC.PK_COUNTRYDEVISION_ID" +
            "     where t.hub_id = ?1\n" +
            "  and t.is_active = 1  AND tc.TYPE IN(3,4,5)", nativeQuery = true)
    List<ZonehubInterfaceDto> listofZonehub(Long id);

    @Query(value = "SELECT t.HUB_ID as hub,SDO_UTIL.TO_WKTGEOMETRY(t.shape) as polygon\n" +
            "      from HUB_GEO t  " +
            "     where t.FK_COUNTRYDEVISION_ID = ?1\n" +
            "  and t.is_active = 1 ", nativeQuery = true)
    ZonehubInterfaceDto findBysubZoneid(Long id);

    @Query(value = "SELECT  t.HUB_ID as hub,SDO_UTIL.TO_WKTGEOMETRY(t.shape) as polygon\n" +
            "      from HUB_GEO t  " +
            " join TBL_COUNTRYDEVISION tc  ON T.FK_COUNTRYDEVISION_ID = tc.PK_COUNTRYDEVISION_ID " +
            "     WHERE TC.TYPE IN (3,4,5) And  t.FK_COUNTRYDEVISION_ID = ?1\n" +
            "  and t.is_active = 1 and t.HUB_ID = ?2 ", nativeQuery = true)
    ZonehubInterfaceDto findBysubZoneidAndHubID(Long id, Long hubID);


    @Query(value = "SELECT  t.HUB_ID as hub,SDO_UTIL.TO_WKTGEOMETRY(t.shape) as polygon\n" +
            "      from HUB_GEO t  " +
            " left join TBL_COUNTRYDEVISION tc  ON T.FK_COUNTRYDEVISION_ID = tc.PK_COUNTRYDEVISION_ID " +
            "     WHERE TC.TYPE IN (2)\n" +
            "  and t.is_active = 1 and t.HUB_ID = ?1 ", nativeQuery = true)
    ZonehubInterfaceDto findByZoneidAndHubID(Long hubID);

    @Modifying
    @Query(value = "delete from HUB_Geo where HUB_ID=?1 and  FK_COUNTRYDEVISION_ID =?2", nativeQuery = true)
    void deletezonehub(Long hubid, Long city);

    @Modifying
    @Query(value = "update  HUB_Geo  set is_Active = 0 where HUB_ID=?1 and  FK_COUNTRYDEVISION_ID =?2", nativeQuery = true)
    void deactivezonehub(Long hubid, Long city);

    @Query(value = "SELECT g.hub_id as hub, tc.*\n" +
            "  FROM HUB_geo g\n" +
            " inner JOIN TBL_COUNTRYDEVISION tc\n" +
            "    ON tc.PK_COUNTRYDEVISION_ID = G.FK_COUNTRYDEVISION_ID\n" +
            " WHERE is_active = 1\n" +
            "   and SDO_ANYINTERACT(g.shape,\n" +
            "                       SDO_GEOMETRY(2001,\n" +
            "                                    4326,\n" +
            "                                    SDO_POINT_TYPE(?1,\n" +
            "                                                   ?2,\n" +
            "                                                   NULL),\n" +
            "                                    NULL,\n" +
            "                                    NULL)) = 'TRUE'\n" +
            "                                    AND tc.TYPE IN(3,5)", nativeQuery = true)
    List<ZonehubInterfaceDto> findbyPosition(Double lat, Double lon);

    @Query(value = "SELECT  tb.PK_HUB_ID as hub,SDO_UTIL.TO_WKTGEOMETRY(t.shape) as polygon\n" +
            "      from  TBL_HUB tb   " +
            " LEFT outer join\n" +
            "        HUB_GEO t\n" +
            "            on tb.PK_HUB_ID = t.HUB_ID   " +
            "     where   tb.FK_CITY_ID= ?1\n" +
            "AND tb.NAME LIKE '%'||?2||'%'" +
            "       and (t.is_active = 1  OR  t.is_active IS null) " +
            " and tb.TYPE = 0", nativeQuery = true)
    List<ZonehubInterfaceDto> findbycity(Long State, String Name);

    @Modifying
    @Procedure("savepolygon")
    void save_hub_polygon(Long p_hub_id, Long p_countrydevision_id, Long p_user_id, String p_polygon);

    @Query(value = "SELECT  g.hub_id as hub,tc.PK_COUNTRYDEVISION_ID as countrydevision, tc.*\n" +
            "  FROM HUB_geo g\n" +
            " inner JOIN TBL_COUNTRYDEVISION tc\n" +
            "    ON tc.PK_COUNTRYDEVISION_ID = G.FK_COUNTRYDEVISION_ID\n" +
            " WHERE is_active = 1\n" +
            "   and SDO_ANYINTERACT(g.shape,\n" +
            "                       SDO_GEOMETRY(2001,\n" +
            "                                    4326,\n" +
            "                                    SDO_POINT_TYPE(?1,\n" +
            "                                                   ?2,\n" +
            "                                                   NULL),\n" +
            "                                    NULL,\n" +
            "                                    NULL)) = 'TRUE'\n" +
            "                                    AND tc.TYPE IN(?3,5) and rownum = 1", nativeQuery = true)
    ZonehubInterfaceDto findByHubAndCityinZone(Double lat, Double lon, Long type);

    List<Hub> findByCityAndType(CountryDevision countryDevision, HubType type);

    List<Hub> findByCityInAndType(List<CountryDevision> countryDevision, HubType type);
}
