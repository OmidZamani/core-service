package com.boxi.hub.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;

import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.entity.HubCategory;
import com.boxi.hub.enums.CountryType;
import com.boxi.hub.enums.HubType;
import com.boxi.hub.payload.converter.HubCategoryConverter;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.hub.payload.converter.HubExcelConverter;
import com.boxi.hub.payload.dto.*;
import com.boxi.hub.payload.request.CreateHubCategoryRequest;
import com.boxi.hub.payload.request.FilterHub;
import com.boxi.hub.payload.request.FilterHubCategory;
import com.boxi.hub.payload.response.CreateHubCategoryResponse;
import com.boxi.hub.repo.CountryDevisionRepository;
import com.boxi.hub.repo.HubCategoryRepository;
import com.boxi.hub.repo.HubRepository;
import com.boxi.hub.service.HubService;
import com.boxi.transport.payload.excel.CreateHubExcelRequest;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.utils.ExcelToPojoUtils;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class HubServiceImpl implements HubService {

    private final HubCategoryRepository hubCategoryRepository;
    private final HubCategoryConverter hubCategoryConverter;
    private final HubRepository hubRepository;
    private final HubConverter hubConverter;
    private final HubExcelConverter hubExcelConverter;
    private final CountryDevisionRepository countryDevisionRepository;


    @Autowired
    public HubServiceImpl(HubCategoryRepository hubCategoryRepository,
                          HubRepository hubRepository,
                          HubConverter hubConverter,
                          HubExcelConverter hubExcelConverter,
                          HubCategoryConverter hubCategoryConverter,
                          CountryDevisionRepository countryDevisionRepository
    ) {
        this.hubCategoryRepository = hubCategoryRepository;
        this.hubRepository = hubRepository;
        this.hubConverter = hubConverter;
        this.hubExcelConverter = hubExcelConverter;
        this.hubCategoryConverter = hubCategoryConverter;


        this.countryDevisionRepository = countryDevisionRepository;

    }

    @Override
    public CreateHubCategoryResponse createHubCategory(CreateHubCategoryRequest request) {
        Boolean isExist = hubCategoryRepository.existsByName(request.getName());
        if (isExist) {
            throw BusinessException.valueException(EntityType.HubCategory, "hub.category.name.duplicate");
        }
        HubCategory category = hubCategoryConverter.fromDtoToModel(request);
        category.setIsActive(true);
        category.setIsDeleted(false);
        HubCategory saved = hubCategoryRepository.save(category);
        return hubCategoryConverter.fromModelToDto(saved);
    }

    @Override
    public CreateHubCategoryResponse updateHubCategory(CreateHubCategoryRequest request) {
        HubCategory category = findHubCategoryById(request.getId());
        hubCategoryConverter.updateFromDto(request, category);
        category.setIsActive(true);
        category.setIsDeleted(false);
        HubCategory saved = hubCategoryRepository.save(category);
        return hubCategoryConverter.fromModelToDto(saved);
    }

    @Override
    public Page<CreateHubCategoryResponse> filterHubCategory(FilterHubCategory filter, Pageable pageable) {
        Page<HubCategory> res = hubCategoryRepository
                .findAll((Specification<HubCategory>) (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                    if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                        predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));
                    }
                    if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                        predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }, pageable);
        return res.map(hubCategoryConverter::fromModelToDto);
    }


    private List<HubPermissionDto> findByParents(String hubCode) {
        List<Hub> topByParentHubCode = hubRepository.findAllByParentHubCodeAndIsActiveIsTrueAndIsDeletedIsFalse(hubCode);
        List<HubPermissionDto> list = new ArrayList<>();
        for (Hub parentCode : topByParentHubCode) {
            HubPermissionDto hubPermissionDto = new HubPermissionDto();
            hubPermissionDto.setId(parentCode.getId());
            hubPermissionDto.setValue(parentCode.getCode());
            hubPermissionDto.setLabel(parentCode.getName());
            log.warn(parentCode.getCode());
            if (parentCode.getParentHub() != null)
                hubPermissionDto.setParent(parentCode.getParentHub().getId());
            if (parentCode.getHubs() != null) {
                hubPermissionDto.setChildren(findByParents(parentCode.getCode()));
            }
            List<HubPermissionDto> collect = list.stream().filter(dto -> hubCode.equals(hubPermissionDto.getValue())).collect(Collectors.toList());
            if (collect.size() == 0)
                list.add(hubPermissionDto);

        }
        return list;
    }

    //sample
    @Override
    public List<HubPermissionDto> listOfHubPermission(String[] strings) {
        List<HubPermissionDto> list = new ArrayList<>();
        List<String> hubCode = new ArrayList<>();
        Collections.addAll(hubCode, strings);
        List<Hub> hubs = hubRepository.findallByCodeList(hubCode);
        for (Hub hub : hubs) {
            HubPermissionDto hubPermissionDto = new HubPermissionDto();
            hubPermissionDto.setId(hub.getId());
            hubPermissionDto.setValue(hub.getCode());
            hubPermissionDto.setLabel(hub.getName());
            if (hub.getParentHub() != null)
                hubPermissionDto.setParent(hub.getParentHub().getId());

            hubPermissionDto.setChildren(findByParents(hub.getCode()));
            list.add(hubPermissionDto);

        }


        return list.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<SimpleHubDto> getHubsByCodes(SimpleArrayWrapper request) {
        return hubRepository.findByCodeIn(request.getIn()).stream().map(hub -> new SimpleHubDto().setCode(hub.getCode()).setName(hub.getName()).setId(hub.getId())).collect(Collectors.toList());
    }


    @Override
    public Map<Long, String> fetchHubsMapByIds(List<Long> ids) {
        return hubRepository.fetchHubsMapByIds(ids);
    }


    public List<LocationDto> convertClobToList(Clob clob) {
        List<LocationDto> locationList = new ArrayList<>();
        try {
            String subString = clob.getSubString(1, (int) clob.length());
            String trim = subString.replace("POLYGON", "").replace("((", "").replace("))", "").replace(", ", ",").trim();
            log.warn(trim);
            String[] split = trim.split(",");
            for (String s : split) {
                String[] s1 = s.split(" ");
                LocationDto locationDto = new LocationDto();
                locationDto.setLocLong(Double.valueOf(s1[0]));
                locationDto.setLocLate(Double.valueOf(s1[1]));
                locationList.add(locationDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locationList;
    }


    @Override
    public List<ZoneHubDto> listOfAllZone() {


        List<ZonehubInterfaceDto> zoneHubInterfaceList = hubRepository.listofZone();
        List<ZoneHubDto> zoneHubList = new ArrayList<>();
        for (ZonehubInterfaceDto zonehubInterfaceDto : zoneHubInterfaceList) {
            ZoneHubDto zoneHubDto = new ZoneHubDto();
            zoneHubDto.setPolygon(convertClobToList(zonehubInterfaceDto.getpolygon()));
            Hub hub = hubRepository.findById(zonehubInterfaceDto.gethub()).orElseThrow();


            zoneHubDto.setHubId(hub.getId());
            zoneHubDto.setHubCode(hub.getCode());
            zoneHubDto.setName(hub.getName());
            zoneHubDto.setLocLong(hub.getLocLong());
            zoneHubDto.setLocLate(hub.getLocLate());
            zoneHubDto.setHubAdmin("-");


            zoneHubList.add(zoneHubDto);
        }
        return zoneHubList;
    }

    @Override
    public ZoneDto createZone(ZoneDto dto) {
        log.warn(dto.toJson());
        CountryDevision countryDev = new CountryDevision();
        CountryDevision byHubIdAndCountryType = countryDevisionRepository.findByHubIdAndCountryType(dto.getSelectHub().getId(), CountryType.hubRegion);
        if (byHubIdAndCountryType == null) {
            countryDev.setHubId(dto.getSelectHub().getId());
            countryDev.setCountryType(CountryType.hubRegion);
            countryDev.setName(dto.getSelectHub().getText());
            countryDev.setCode("hubRegion_" + dto.getCountrydevision().getId());
            countryDev.setParent(new CountryDevision().setId(dto.getCountrydevision().getId()));
            countryDev = countryDevisionRepository.save(countryDev);

        } else
            countryDev = byHubIdAndCountryType;


        CountryDevision countryDevision = countryDevisionRepository.findById(dto.getCountrydevision().getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.Hub, "countrydevision.not.found");
        });
        if (dto.getCountrytype() != null) {
            countryDevision.setCountryType(CountryType.findByValue(dto.getCountrytype().getId()));

            countryDevisionRepository.save(countryDevision);
        }
        hubRepository.save_hub_polygon(dto.getSelectHub().getId(), countryDev.getId(), dto.getSelectuser().getId(), dto.getPolygon());
        return dto;

    }

    @Override
    public ZoneDto createSubZone(ZoneDto dto) {
        CountryDevision countryDevision = countryDevisionRepository.findById(dto.getCountrydevision().getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.Hub, "countrydevision.not.found");
        });
        if (dto.getCountrytype() != null) {
            countryDevision.setCountryType(CountryType.findByValue(dto.getCountrytype().getId()));

            countryDevisionRepository.save(countryDevision);
        }
        hubRepository.save_hub_polygon(dto.getSelectHub().getId(), dto.getCountrydevision().getId(), dto.getSelectuser().getId(), dto.getPolygon());
        return dto;
    }

    @Override
    public List<ZoneHubDto> findZoneById(Long id) {
        List<ZonehubInterfaceDto> zoneHubInterfaceList = hubRepository.listofZonehub(id);
        List<ZoneHubDto> zoneHubList = new ArrayList<>();
        for (ZonehubInterfaceDto zonehubInterfaceDto : zoneHubInterfaceList) {
            ZoneHubDto zoneHubDto = new ZoneHubDto();
            zoneHubDto.setPolygon(convertClobToList(zonehubInterfaceDto.getpolygon()));
            Hub hub = hubRepository.findById(zonehubInterfaceDto.gethub()).orElseThrow();
            zoneHubDto.setHubId(hub.getId());
            zoneHubDto.setHubCode(hub.getCode());
            zoneHubDto.setName(hub.getName());
            zoneHubDto.setLocLong(hub.getLocLong());
            zoneHubDto.setLocLate(hub.getLocLate());
            zoneHubDto.setIsActive(hub.getIsActive());
            zoneHubDto.setTypes(new SelectResponse(hub.getType().getValue(), hub.getType().getFa()));
            if (hub.getCity() != null) {
                CountryDevisionSimpleDto countryDevisionSimpleDto = new CountryDevisionSimpleDto();
                countryDevisionSimpleDto.setId(hub.getCity().getId());
                countryDevisionSimpleDto.setText(hub.getCity().getName());
                countryDevisionSimpleDto.setCode(hub.getCity().getCode());
                zoneHubDto.setProvince(countryDevisionSimpleDto);
            }
            zoneHubDto.setHubAdmin("-");


            zoneHubList.add(zoneHubDto);
        }
        return zoneHubList;

    }

    @Override
    public List<ZoneHubDto> findByPosition(Double locLate, Double locLate1) {
        List<ZonehubInterfaceDto> zoneHubInterfaceLists = hubRepository.findbyPosition(locLate, locLate1);
        List<ZoneHubDto> zoneHubList = new ArrayList<>();
        for (ZonehubInterfaceDto zoneHubInterfaceList : zoneHubInterfaceLists) {
            ZoneHubDto zoneHubDto = new ZoneHubDto();
            Hub hub = hubRepository.findById(zoneHubInterfaceList.gethub()).orElseThrow();
            zoneHubDto.setHubId(hub.getId());
            zoneHubDto.setHubCode(hub.getCode());
            zoneHubDto.setName(hub.getName());
            zoneHubDto.setLocLong(hub.getLocLong());
            zoneHubDto.setLocLate(hub.getLocLate());
            zoneHubDto.setHubAdmin("-");
            zoneHubList.add(zoneHubDto);
        }
        return zoneHubList;

    }

    @Override
    public List<ZoneHubDto> findByCity(Long cityId, String hubName, Long type) {

        List<CountryDevision> allByParent = countryDevisionRepository.findAllByParent(new CountryDevision().setId(cityId));
        List<Hub> byCity = hubRepository.findByCityInAndTypeAndIsDeletedFalseAndIsActiveIsTrue(allByParent, HubType.findByValue(type));
        List<ZoneHubDto> zoneHubList = new ArrayList<>();
        for (Hub hub : byCity) {
            ZoneHubDto zoneHubDto = hubConverter.fromHubToZone(hub);
            ZonehubInterfaceDto bySubZoneId = hubRepository.findByZoneidAndHubID(hub.getId());
            if (bySubZoneId != null)
                if (bySubZoneId.getpolygon() != null)
                    zoneHubDto.setPolygon(convertClobToList(bySubZoneId.getpolygon()));
            zoneHubDto.setHubAdmin("-");
            if (hub.getManagerId() != null)
                zoneHubDto.setHubAdmin(hub.getManagerId() + "");

            if (hub.getCity() != null) {
                CountryDevision countryDevision = countryDevisionRepository.findById(hub.getCity().getId()).orElseThrow();
                zoneHubDto.setTypes(new SelectResponse(countryDevision.getCountryType().getValue(), countryDevision.getCountryType().getFa()));
                CountryDevisionSimpleDto countryDevisionSimpleDto = new CountryDevisionSimpleDto();
                countryDevisionSimpleDto.setId(countryDevision.getId());
                countryDevisionSimpleDto.setText(countryDevision.getName());
                countryDevisionSimpleDto.setCode(countryDevision.getCode());
                zoneHubDto.setProvince(countryDevisionSimpleDto);
            }
            zoneHubList.add(zoneHubDto);
        }
        return zoneHubList;
    }

    @Override
    public Page<HubDto> filterGroupBy(FilterHub request, Pageable pageable) {

        Page<Hub> city = hubRepository.findAll((Specification<Hub>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            query.groupBy(root.get("city"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        }, pageable);
        return city.map(hubConverter::fromModelToDto);
    }

    @Override
    public HubDto updateHubZone(HubDto hubDto) {

        Hub hub = hubRepository.findById(hubDto.getId()).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.not.found");
        });
        hub.setName(hubDto.getName());
        Hub save = hubRepository.save(hub);
        return hubConverter.fromModelToDto(save);
    }

    @Override
    public ZoneHubDto findBySubZoneId(Long id) {
        ZonehubInterfaceDto zoneHubInterfaceList = hubRepository.findBysubZoneid(id);

        if (zoneHubInterfaceList == null) throw BusinessException.valueException(EntityType.Hub, "zone.not.found");

        ZoneHubDto zoneHubDto = new ZoneHubDto();
        zoneHubDto.setPolygon(convertClobToList(zoneHubInterfaceList.getpolygon()));
        Hub hub = hubRepository.findById(zoneHubInterfaceList.gethub()).orElseThrow();

        zoneHubDto.setHubId(hub.getId());
        zoneHubDto.setHubCode(hub.getCode());
        zoneHubDto.setName(hub.getName());
        zoneHubDto.setLocLong(hub.getLocLong());
        zoneHubDto.setLocLate(hub.getLocLate());
        zoneHubDto.setIsActive(hub.getIsActive());
        zoneHubDto.setTypes(new SelectResponse(hub.getType().getValue(), hub.getType().getFa()));
        if (hub.getCity() != null) {
            CountryDevisionSimpleDto countryDevisionSimpleDto = new CountryDevisionSimpleDto();
            countryDevisionSimpleDto.setId(hub.getCity().getId());
            countryDevisionSimpleDto.setText(hub.getCity().getName());
            countryDevisionSimpleDto.setCode(hub.getCity().getCode());
            zoneHubDto.setProvince(countryDevisionSimpleDto);
        }
        zoneHubDto.setHubAdmin("-");
        return zoneHubDto;

    }

    @Override
    public void deleteZoneHub(ZoneHubDto dto) {
        countryDevisionRepository.deleteById(dto.getProvince().getId());
        hubRepository.deletezonehub(dto.getHubId(), dto.getProvince().getId());
    }

    @Override
    public void deActiveZoneHub(ZoneHubDto dto) {
        List<CountryType> countryTypes = new ArrayList<>();
//        countryTypes.add(CountryType.pickupRegion);
        countryTypes.add(CountryType.deliveryRegion);
        countryTypes.add(CountryType.pickupDeliveryRegion);

        List<CountryDevision> subZone = countryDevisionRepository.findSubZone(countryTypes, dto.getHubId());
        if (subZone.size() == 0) {
            CountryDevision byHubIdAndCountryType = countryDevisionRepository.findByHubIdAndCountryType(dto.getHubId(), CountryType.hubRegion);
            hubRepository.deactivezonehub(dto.getHubId(), byHubIdAndCountryType.getId());
        } else {
            throw BusinessException.valueException(EntityType.Hub, "hub.zone.not.access.delete");
        }
    }

    @Override
    public List<HubWithLocationDto> hubLocation(String hubName, String cityId) {
        List<Hub> all = hubRepository.findAll((Specification<Hub>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("isPossibleOrderRegistration"), true));
            predicates.add(criteriaBuilder.equal(root.get("isPickupPossible"), true));
            if (StringUtils.isNotBlank(hubName)) {
                Predicate name = criteriaBuilder.like(root.get("name"), "%" + hubName + "%");
                Predicate code = criteriaBuilder.like(root.get("code"), "%" + hubName + "%");
                predicates.add(criteriaBuilder.or(name, code));
            }
            if (StringUtils.isNotBlank(cityId))
                predicates.add(criteriaBuilder.equal(root.get("city").get("id"), cityId));


            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });


        return all.stream().map(hubConverter::fromModelToHubWithLocationDto).collect(Collectors.toList());
    }

    @Override
    public Page<SelectResponse> selectBranchHub(String filter, HubFilter hubFilter) {
        Pageable pageable = PageRequest.of(0, 100);
//        if (hubFilter != null) {
        return hubRepository.findAll((Specification<Hub>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
            Predicate isActive = criteriaBuilder.equal(root.get("isActive"), true);
            if (filter != null && !filter.isEmpty()) {
                Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                predicates.add(criteriaBuilder.or(name, code));
            }

            predicates.add(criteriaBuilder.equal(root.get("type"), HubType.BRANCH));
            predicates.add(criteriaBuilder.and(isDeleted));
            predicates.add(criteriaBuilder.and(isActive));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);

    }

    @Override
    public ZoneDto findRegionInZone(FindRegionInZoneDto dto) {
        ZonehubInterfaceDto byHubAndCityInZone = hubRepository.findByHubAndCityinZone(dto.getLat(), dto.getLon(), dto.getType());

        ZoneDto z = new ZoneDto();
        z.setCountrydevision(new SelectResponse(byHubAndCityInZone.getcountrydevision(), ""));
        Hub byId = findById(byHubAndCityInZone.gethub());
        z.setSelectHub(new SelectResponse(byHubAndCityInZone.gethub(), byId.getName()));

        CountryDevision countryDevision = countryDevisionRepository.findById(z.getCountrydevision().getId()).orElseThrow();
        z.setCountrydevision(new SelectResponse(countryDevision.getId(), countryDevision.getName()));

        return z;


    }

    @Override
    public ZoneHubDto findByZoneRegionPolyGone(Long hubId) {
        ZoneHubDto zoneHubDto = new ZoneHubDto();

        ZonehubInterfaceDto bySubZoneId = hubRepository.findByZoneidAndHubID(hubId);
        if (bySubZoneId != null)
            if (bySubZoneId.getpolygon() != null)
                zoneHubDto.setPolygon(convertClobToList(bySubZoneId.getpolygon()));
        zoneHubDto.setHubAdmin("-");
        assert bySubZoneId != null;
        Hub hub = hubRepository.findById(bySubZoneId.gethub()).orElseThrow();
        zoneHubDto.setLocLate(hub.getLocLate());
        zoneHubDto.setLocLong(hub.getLocLong());
        zoneHubDto.setHubId(hubId);
        zoneHubDto.setHubCode(hub.getCode());


        return zoneHubDto;
    }

    @Override
    public SelectResponse findByRegionCountryDivision(Long id) {
        CountryDevision byHubIdAndCountryType = countryDevisionRepository.findByHubIdAndCountryType(id, CountryType.hubRegion);
        return new SelectResponse(byHubIdAndCountryType.getId(), byHubIdAndCountryType.getName());
    }

    @Override
    public SelectResponse updateCountryDivision(SelectResponse dto) {
        CountryDevision countryDevision = countryDevisionRepository.findById(dto.getId()).orElseThrow();
        countryDevision.setName(dto.getText());
        CountryDevision save = countryDevisionRepository.save(countryDevision);
        return new SelectResponse(save.getId(), save.getName());

    }

    @Override
    public void deleteSubZonePolygon(ZoneHubDto dto) {
        hubRepository.deletezonehub(dto.getHubId(), dto.getProvince().getId());
    }

    @Override
    public List<SelectResponse> findByCityInHub(Long id) {

        Hub hub = hubRepository.findById(id).orElseThrow();
        List<Hub> allByCityAndIsActiveIsTrueAndIsDeletedIsFalse = hubRepository.findAllByCityAndIsActiveIsTrueAndIsDeletedIsFalse(hub.getCity());
        return allByCityAndIsActiveIsTrueAndIsDeletedIsFalse.stream().map(this::toSelect).collect(Collectors.toList());
    }

    @Override
    public List<SelectResponse> listOfParentHubList(List<Long> listOfHub) {
        List<Hub> byIdIn = hubRepository.findByIdIn(listOfHub);
        List<SelectResponse> selectResponses = new ArrayList<>();
        for (Hub hub : byIdIn) {
            if (hub.getParentHub() != null)
                selectResponses.add(new SelectResponse(hub.getParentHub().getId(), hub.getParentHub().getName()));
        }
        return selectResponses;
    }

    @Override
    public List<SelectResponse> findByRegionPositionInHubId(Long id) {

        return null;
    }

    private SelectResponse listOfParentSelectResponse(Hub hub) {
        if (hub.getParentHub() != null)
            return new SelectResponse(hub.getParentHub().getId(), hub.getParentHub().getName());
        else
            return null;

    }


    @Override
    public HubDto findHubById(Long id) {
        Hub hub = findById(id);
        return hubConverter.fromModelToDto(hub);
    }

    @Override
    public CreateHubCategoryResponse findHubCatById(Long id) {
        HubCategory hubCategory = findHubCategoryById(id);
        return hubCategoryConverter.fromModelToDto(hubCategory);
    }


    @Override
    public Page<SelectResponse> selectParentHub(String filter) { //done
        Pageable pageable = PageRequest.of(0, 100);
        return hubRepository.findAll((Specification<Hub>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // predicates.add(criteriaBuilder.isNull(root.get("parentHub")));
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.trim() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);

    }

    @Override
    public Page<SelectResponse> selectHubCategory(String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return hubCategoryRepository.findAll((Specification<HubCategory>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%")));
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelectHubCategory);
    } //done

    public SelectResponse toSelectHubCategory(HubCategory entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    } //done

    @Override
    public HubCategory fromSelectHubCategory(SelectResponse select) {
        if (select == null) return null;
        return findHubCategoryById(select.getId());
    }

    public HubCategory findHubCategoryById(Long id) {
        return hubCategoryRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.CountryDevision, "hub.category.not.found");
        });
    }
    //................................

    public Hub findById(Long id) {
        if (id == 0) return null;
        return hubRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.not.found");
        });
    }

    @Override
    public void deleteHubCategory(Long id) {
        hubCategoryRepository.logicalDelete(id);
    }

    @Override
    public void deleteHub(Long id) {
        hubRepository.logicalDelete(id);
    }

    @Override
    public HubDto updateHub(HubDto request) {
        Hub hub = findById(request.getId());
        hubConverter.updateFromDto(request, hub);
        if (hub.getParentHub() == null) hub.setParentHub(null);
        if (request.getSelectManager() != null)
            hub.setManagerId(request.getSelectManager().getId());
        return saveHubData(hub);
    }


    @Override
    public Hub fromSelect(SelectResponse select) {
        return findById(select.getId());
    }


    @Override
    public synchronized HubDto createHub(HubDto request) {
        request.setId(null);

        if (hubRepository.existsByLocLateAndLocLong(request.getLocLate(), request.getLocLong()))
            throw BusinessException.valueException(EntityType.Hub, "hub.lat.long.duplicate");

        if (request.getSelectParentHub() != null && request.getSelectParentHub().getId() != null) {  //valid parentHub
            if (!isHubExist(request.getSelectParentHub().getId())) {
                throw BusinessException.valueException(EntityType.Hub, "code.not.exist");
            }
        }

        Hub hub = hubConverter.fromDtoToModel(request);
        if (request.getSelectManager() != null)
            hub.setManagerId(request.getSelectManager().getId());

        String parentCode = null;
        if (hub.getParentHub() != null) {
            Hub ParentHub = hubRepository.findById(hub.getParentHub().getId()).orElseThrow();
//            parentCode = hub.getParentHub().getCode();
            parentCode = ParentHub.getCode();
        }
        hub.setCode(genCode(parentCode));

        hub.setIsDeleted(false);
        return saveHubData(hub);
    }


    private boolean isHubExist(Long id) {
        return hubRepository.existsById(id);
    }


    public synchronized String genCode(String parentCode) {
        long hubCount = hubRepository.count();
        if (hubCount == 0L) {
            int base = 100;
            return "A".concat("" + base);
        }
        Hub parent = hubRepository.findTopByCode(parentCode);
        int childCount = 0;
        if (parent.getHubs() != null) {
            childCount = parent.getHubs().size();
        }
        if (parentCode.contains("A")) {
            if (parentCode.contains("B")) {
                if (parentCode.contains("C")) {
                    if (parentCode.contains("D")) {
                        return parentCode.concat("E" + (childCount + 1));
                    }
                    return parentCode.concat("D" + (childCount + 1));
                }
                return parentCode.concat("C" + (childCount + 1));
            }
            return parentCode.concat("B" + (childCount + 1));
        }
        return parentCode;
    }

    private HubDto saveHubData(Hub hub) {
        Hub saved = hubRepository.save(hub);
        return hubConverter.fromModelToDto(saved);
    }

    private List<Long> findChild(List<HubPermissionDto> hubList, Long parentId) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hubList) {
            if (hubPermissionDto.getParent() == parentId) {
                list.add(hubPermissionDto.getId());
                if (hubPermissionDto.getChildren() != null)
                    list.addAll(findChild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));

            } else
                list.add(hubPermissionDto.getId());
        }
        return list;
    }

    private List<Long> findAllHubId(List<HubPermissionDto> hubList) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hubList) {
            list.add(hubPermissionDto.getId());
            if (hubPermissionDto.getChildren() != null)
                list.addAll(findChild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));

        }
        return list;
    }

    @Override
    public Page<HubDto> filter(FilterHub filter, Pageable pageable) {
        // hubRepository.findBy(nameLike())
        if (filter.getHublist() != null) {
            Page<Hub> res = hubRepository
                    .findAll((Specification<Hub>) (root, query, criteriaBuilder) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
                        if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode())) {
                            predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));
                        }
                        if (filter.getName() != null && StringUtils.isNotBlank(filter.getName())) {
                            predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));
                        }
                        if (filter.getStateId() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("state").get("id"), filter.getStateId()));
                        }
                        if (filter.getCityId() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("city").get("id"), filter.getCityId()));
                        }
                        if (filter.getRegionId() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("region").get("id"), filter.getRegionId()));
                        }
                        if (filter.getParentHubId() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("parentHub"), filter.getParentHubId()));
                        }
                        if (filter.getDropOffAllowed() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("dropOffAllowed"), filter.getDropOffAllowed()));
                        }

                        if (filter.getIsPossibleConsignmentStorage() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("isPossibleConsignmentStorage"), filter.getIsPossibleConsignmentStorage()));
                        }

                        if (filter.getIsDeliveryPossible() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("isDeliveryPossible"), filter.getIsDeliveryPossible()));
                        }

                        if (filter.getIsPossibleOrderRegistration() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("isPossibleOrderRegistration"), filter.getIsPossibleOrderRegistration()));
                        }

                        if (filter.getIsPickupPossible() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("isPickupPossible"), filter.getIsPickupPossible()));
                        }


                        if (filter.getLocationStartDate() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("locationStartDate"), filter.getLocationStartDate()));
                        }
                        if (filter.getIsActive() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));
                        }
                        if (filter.getHubCategoryId() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("hubCategory").get("id"), filter.getHubCategoryId()));
                        }
                        if (filter.getHubTypeId() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("type"), HubType.findByValue(filter.getHubTypeId())));
                        }
                        if (filter.getMandatoryArrivalScan() != null) {
                            predicates.add(criteriaBuilder.equal(root.get("mandatoryArrivalScan"), filter.getMandatoryArrivalScan()));
                        }
                        if (filter.getPinCode() != null && StringUtils.isNotBlank(filter.getPinCode())) {
                            predicates.add(criteriaBuilder.equal(root.get("pinCode"), filter.getPinCode()));
                        }
                        if (filter.getHublist() != null) {
                            List<Long> ids = findAllHubId(filter.getHublist());
                            predicates.add(criteriaBuilder.and(root.get("id").in(ids)));
                        }

                        query.distinct(true);
                        query.orderBy(criteriaBuilder.desc(root.get("id")));
                        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }, pageable);
            return res.map(hubConverter::fromModelToDto);
        } else
            throw BusinessException.entityNotFoundException(EntityType.Hub, "hub.list.not.found");
    }

    @Override
    public synchronized void createHubsByExcel(MultipartFile excel) throws IOException {

        List<CreateHubExcelRequest> hubs = ExcelToPojoUtils.toPojo(CreateHubExcelRequest.class, excel.getInputStream());
        List<HubDto> lists = hubs.stream().map(hubExcelConverter::excelToDto).collect(Collectors.toList());
        for (HubDto itr : lists) {
            createHub(itr);
        }


    }

    @Override
    public Page<SelectResponse> select(String filter, HubFilter hubFilter) {
        Pageable pageable = PageRequest.of(0, 100);

        return hubRepository.findAll((Specification<Hub>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
            Predicate isActive = criteriaBuilder.equal(root.get("isActive"), true);
            if (filter != null && !filter.isEmpty()) {
                Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                predicates.add(criteriaBuilder.or(name, code));
            }

            if (hubFilter != null)
                if (hubFilter.getHublist() != null) {
                    List<Long> ids = findAllHubId(hubFilter.getHublist());
                    predicates.add(criteriaBuilder.and(root.get("id").in(ids)));
                }
            predicates.add(criteriaBuilder.and(isDeleted));
            predicates.add(criteriaBuilder.and(isActive));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);

    }

    @Override
    public Page<SelectResponse> selectMainHub(String filter, HubFilter hubFilter) {
        Pageable pageable = PageRequest.of(0, 100);
//        if (hubFilter != null) {
        return hubRepository.findAll((Specification<Hub>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
            Predicate isActive = criteriaBuilder.equal(root.get("isActive"), true);
            if (filter != null && !filter.isEmpty()) {
                Predicate name = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%"));
                Predicate code = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("code")), "%" + filter.trim() + "%"));
                predicates.add(criteriaBuilder.or(name, code));
            }
            predicates.add(criteriaBuilder.equal(root.get("type"), HubType.MAIN_HUB));
            predicates.add(criteriaBuilder.and(isDeleted));
            predicates.add(criteriaBuilder.and(isActive));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }


    @Override
    public SelectResponse toSelect(Hub hub) {
        return new SelectResponse(hub.getId(), hub.selectToString());
    }


}
