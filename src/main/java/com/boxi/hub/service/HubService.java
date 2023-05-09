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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HubService {
    HubDto findHubById(Long id);

    CreateHubCategoryResponse findHubCatById(Long id);

    Page<SelectResponse> selectParentHub(String name);

    Page<SelectResponse> selectHubCategory(String filter);

    CreateHubCategoryResponse createHubCategory(CreateHubCategoryRequest request);

    HubDto createHub(HubDto request);

    Page<HubDto> filter(FilterHub filter, Pageable pageable);

    void createHubsByExcel(MultipartFile excel) throws IOException;

    Page<SelectResponse> select(String filter, HubFilter hubFilter);

    Page<SelectResponse> selectMainhub(String filter, HubFilter hubFilter);

    Hub fromSelect(SelectResponse select);

    HubCategory fromSelectHubCategory(SelectResponse select);

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

    List<ZoneHubDto> listofAllzone();

    ZoneDto createzone(ZoneDto dto);

    ZoneDto createSubzone(ZoneDto dto);

    List<ZoneHubDto> findzonebyid(Long id);

    List<ZoneHubDto> findByPosition(Double locLate, Double locLate1);

    List<ZoneHubDto> findbycity(Long cityid, String hubname,Long type);

    Page<HubDto> filtergroupby(FilterHub request, Pageable pageable);

    HubDto updateHubzone(HubDto hubDto);

    ZoneHubDto findbysubzoneid(Long id);

    void deletezobehub(ZoneHubDto dto);

    void deactivezonehub(ZoneHubDto dto);

    List<HubWithLocationDto> hubLocation(String hubname, String cityId);

    Page<SelectResponse> selectbranchhub(String filter, HubFilter hubFilter);

    ZoneDto findRegionInZone(FindRegionInZoneDto dto);

    ZoneHubDto findByZoneRegionPolygone(Long hubId);


    SelectResponse findByRegionCountryDevision(Long id);

    SelectResponse updateCountryDevision(SelectResponse dto);

    void deleteSubZonePolygon(ZoneHubDto dto);


    List<SelectResponse> findByCityinHub(Long id);
}
