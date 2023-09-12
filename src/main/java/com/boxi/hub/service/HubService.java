package com.boxi.hub.service;


import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.entity.HubCategory;
import com.boxi.hub.payload.dto.*;
import com.boxi.hub.payload.request.CreateHubCategoryRequest;
import com.boxi.hub.payload.request.FilterHub;
import com.boxi.hub.payload.request.FilterHubCategory;
import com.boxi.hub.payload.response.CreateHubCategoryResponse;
import com.boxi.transport.payload.request.HubFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HubService {
    HubDto findHubById(Long id);

    CreateHubCategoryResponse findHubCatById(Long id);

    Page<SelectResponse> selectParentHub(String name);//TODO

    Page<SelectResponse> selectHubCategory(String filter);

    CreateHubCategoryResponse createHubCategory(CreateHubCategoryRequest request);

    HubDto createHub(HubDto request);

    Page<HubDto> filter(FilterHub filter, Pageable pageable, Jwt jwt);

    void createHubsByExcel(MultipartFile excel) throws IOException;

    Page<SelectResponse> select(String filter, HubFilter hubFilter);

    Page<SelectResponse> selectMainHub(String filter, HubFilter hubFilter);

    Hub fromSelect(SelectResponse select);

    HubCategory fromSelectHubCategory(SelectResponse select);//TODO

    SelectResponse toSelect(Hub hub);

    SelectResponse toSelectHubCategory(HubCategory hubCategory);

    Hub findById(Long id);

    void deleteHubCategory(Long id);

    void deleteHub(Long id);

    HubDto updateHub(HubDto request);

    CreateHubCategoryResponse updateHubCategory(CreateHubCategoryRequest request);

    Page<CreateHubCategoryResponse> filterHubCategory(FilterHubCategory filterHubCategory, Pageable pageable);

    List<HubPermissionDto> listOfHubPermission(String[] strings);

    List<SimpleHubDto> getHubsByCodes(SimpleArrayWrapper hubCodes);

    Map<Long, String> fetchHubsMapByIds(List<Long> deliveryHubId);

    List<ZoneHubDto> listOfAllZone();

    ZoneDto createZone(ZoneDto dto);

    ZoneDto createSubZone(ZoneDto dto);

    List<ZoneHubDto> findZoneById(Long id);

    List<ZoneHubDto> findByPosition(Double locLate, Double locLate1);

    List<ZoneHubDto> findByCity(Long cityId, String hubName, Long type);

    Page<HubDto> filterGroupBy(FilterHub request, Pageable pageable);

    HubDto updateHubZone(HubDto hubDto);

    ZoneHubDto findBySubZoneId(Long id);

    void deleteZoneHub(ZoneHubDto dto);

    void deActiveZoneHub(ZoneHubDto dto);

    List<HubWithLocationDto> hubLocation(String hubName, String cityId);

    Page<SelectResponse> selectBranchHub(String filter, HubFilter hubFilter);

    ZoneDto findRegionInZone(FindRegionInZoneDto dto);

    ZoneHubDto findByZoneRegionPolyGone(Long hubId);


    SelectResponse findByRegionCountryDivision(Long id);

    SelectResponse updateCountryDivision(SelectResponse dto);

    void deleteSubZonePolygon(ZoneHubDto dto);


    List<SelectResponse> findByCityInHub(Long id);

    List<SelectResponse> listOfParentHubList(List<Long> listOfHub);

    List<SelectResponse> findByRegionPositionInHubId(Long id);
}
