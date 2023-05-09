package com.boxi.hub.service.impl;


import com.boxi.PriceList.payload.dto.CountryDevisionInsDto;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.enums.CountryType;
import com.boxi.hub.payload.converter.CountryDevisionConverter;
import com.boxi.hub.payload.dto.CountryDevisionPolygonDto;
import com.boxi.hub.payload.dto.LocationDto;
import com.boxi.hub.payload.dto.ZonehubInterfaceDto;
import com.boxi.hub.repo.CountryDevisionRepository;
import com.boxi.hub.repo.HubRepository;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.product.payload.dto.ContryDevistionCodeDto;
import com.boxi.product.payload.dto.CountryDevisionDto;
import com.boxi.product.response.ContryDevistionSelect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class CountryDevisionServiceImpl implements CountryDevisionService {

    private final CountryDevisionRepository countryDevisionRepository;
    private final CountryDevisionConverter countryDevisionConverter;

    private final HubRepository hubRepository;
    private final HubServiceImpl hubService;


    @Autowired
    public CountryDevisionServiceImpl(CountryDevisionRepository countryDevisionRepository,
                                      CountryDevisionConverter countryDevisionConverter,
                                      HubRepository hubRepository, HubServiceImpl hubService) {
        this.countryDevisionRepository = countryDevisionRepository;
        this.countryDevisionConverter = countryDevisionConverter;
        this.hubRepository = hubRepository;

        this.hubService = hubService;
    }

    @Override
    public Page<SelectResponse> selectProvince(String filter) {
        Pageable pageable = PageRequest.of(0, 100);

        return countryDevisionRepository.findAll((Specification<CountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("countryType"), CountryType.PROVINCE)));
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }


    @Override
    public Page<SelectResponse> selectCity(Long parentId, String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return getCountryDevisions(parentId, filter, pageable, CountryType.CITY);
    }

    private Page<SelectResponse> getCountryDevisions(Long parentId, String filter, Pageable pageable, CountryType type) {
        return countryDevisionRepository.findAll((Specification<CountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("countryType"), type)));
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("parent"), parentId)));
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toSelect);
    }

    @Override
    public Page<SelectResponse> selectLoc(Long cityId, String filter) {
        Pageable pageable = PageRequest.of(0, 100);
        return getCountryDevisions(cityId, filter, pageable, CountryType.PROVINCE);
    }

    @Override
    public CountryDevision fromSelect(SelectResponse select) {
        if (select == null) return null;
        return findById(select.getId());
    }

    private CountryDevision findById(Long id) {
        if (id == 0) return null;
        return countryDevisionRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.CountryDevision, "countrydevision.not.found");
        });
    }

    @Override
    public SelectResponse toSelect(CountryDevision entity) {
        return new SelectResponse(entity.getId(), entity.selectToString());
    }

    public SelectResponse toSelectpriviance(CountryDevision entity) {
        if (entity.getParent() != null)
            return new SelectResponse(entity.getId(), entity.getParent().getName() + " - " + entity.getName());
        else
            return new SelectResponse(entity.getId(), entity.getName() + " - " + entity.getCode());
    }

    @Override
    public List<ContryDevistionSelect> SelectTreeToParent(Long Id) {
        List<CountryDevisionInsDto> treeToParent = countryDevisionRepository.findTreeToParent(Id);
        List<ContryDevistionSelect> contryDevistionSelects = new ArrayList<>();
        for (CountryDevisionInsDto countryDevisionInsDto : treeToParent) {
            ContryDevistionSelect contryDevistionSelect = new ContryDevistionSelect();
            contryDevistionSelect.setId(countryDevisionInsDto.getid());
            contryDevistionSelect.setText(countryDevisionInsDto.getname());
            CountryType byValue = CountryType.findByValue(countryDevisionInsDto.getcountryType());
            contryDevistionSelect.setCountryType(new SelectResponse(byValue.getValue(), byValue.getFa()));
            contryDevistionSelects.add(contryDevistionSelect);
        }
        return contryDevistionSelects;

    }

    @Override
    public boolean existsCountry(String CountryDevision) {
        return countryDevisionRepository.existsByCode(CountryDevision);
    }

    @Override
    public SelectResponse findByidInCode(SelectResponse toCountryDevision) {
        CountryDevision byCode = countryDevisionRepository.findByCode(toCountryDevision.getText());
        if (byCode == null)
            throw BusinessException.entityNotFoundException(EntityType.CountryDevision, "countrydevision.text.not.found");
        return new SelectResponse(byCode.getId(), byCode.getName());

    }

    @Override
    public CountryDevisionDto findRegioninCtiy(Long city) {
        return countryDevisionConverter.fromModeltoDto(countryDevisionRepository.findById(city).orElseThrow());
    }

    @Override
    public ContryDevistionSelect findByidInCodefeign(SelectResponse toCountryDevision) {
        CountryDevision byCode = countryDevisionRepository.findByCode(toCountryDevision.getText());
        if (byCode == null)
            throw BusinessException.entityNotFoundException(EntityType.CountryDevision, "countrydevision.not.found");

        ContryDevistionSelect contryDevistionSelect = new ContryDevistionSelect();
        contryDevistionSelect.setId(byCode.getId());
        contryDevistionSelect.setText(byCode.getName());
        contryDevistionSelect.setCountryType(new SelectResponse(byCode.getCountryType().getValue(), byCode.getCountryType().getFa()));
        return contryDevistionSelect;

    }

    @Override
    public List<SelectResponse> selectCityofprovinceBylist(List<SelectResponse> provincelist) {
        List<CountryDevision> All = countryDevisionRepository.findAll((Specification<CountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            List<Long> ids = provincelist.stream().map(SelectResponse::getId).collect(Collectors.toList());
            predicates.add(criteriaBuilder.and(root.get("parent").in(ids)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        });

        return All.stream().map(this::toSelect).collect(Collectors.toList());


    }

    @Override
    public SelectResponse selectById(Long id) {
        CountryDevision loc = findById(id);
        return new SelectResponse(loc.getId(), loc.getName());
    }

    @Override
    public List<ContryDevistionCodeDto> provincewithCode(String filter) {

        Pageable pageable = PageRequest.of(0, 100);

        Page<ContryDevistionCodeDto> map = countryDevisionRepository.findAll((Specification<CountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("countryType"), CountryType.PROVINCE)));
            if (filter != null && !filter.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + filter.trim() + "%")));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(this::toselectwithcode);
        return map.stream().collect(Collectors.toList());

    }

    @Override
    public CountryDevisionDto create(CountryDevisionDto dto) {
        CountryDevision countryDevision = countryDevisionConverter.fromDtotoModel(dto);
        CountryDevision save = countryDevisionRepository.save(countryDevision);
        return countryDevisionConverter.fromModeltoDto(save);
    }

    @Override
    public CountryDevisionDto edit(CountryDevisionDto dto) {
        countryDevisionRepository.findById(dto.getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.CountryDevision, "countrydevision.not.found");
        });

        CountryDevision countryDevision = countryDevisionConverter.fromDtotoModel(dto);
        CountryDevision save = countryDevisionRepository.save(countryDevision);
        return countryDevisionConverter.fromModeltoDto(save);
    }

    @Override
    public List<SelectResponse> getCountryType() {

        return CountryType.select();
    }

    @Override
    public List<CountryDevisionDto> findByhubId(Long id) {
        List<CountryDevision> byHubId = countryDevisionRepository.findByHubId(id);
        return byHubId.stream().map(countryDevisionConverter::fromModeltoDto).collect(Collectors.toList());

    }

    @Override
    public List<CountryDevisionPolygonDto> findByhubCode(String code, String hubid) {
        Hub byCode = hubRepository.findByCodeAndParentHubIsNotNull(code);
        if (byCode != null) {
            List<CountryType> list = new ArrayList<>();
            list.add(CountryType.pickupRegion);
            list.add(CountryType.deliveryRegion);
            list.add(CountryType.pickupDeliveryRegion);
            List<CountryDevision> byHubId = countryDevisionRepository.findSubZone(list, byCode.getId());
            List<CountryDevisionDto> collect = byHubId.stream().map(countryDevisionConverter::fromModeltoDto).collect(Collectors.toList());
            List<CountryDevisionPolygonDto> countryDevisionDtos = new ArrayList<>();
            for (CountryDevisionDto countryDevisionDto : collect) {
                CountryDevisionPolygonDto countryDevisionPolygonDto = countryDevisionConverter.fromModeltoPolygoneDto(countryDevisionDto);
                ZonehubInterfaceDto bysubZoneid = hubRepository.findBysubZoneidAndHubID(countryDevisionDto.getId(), Long.valueOf(hubid));

                if (bysubZoneid != null) {
                    List<LocationDto> locationDtos = hubService.convertclobtoList(bysubZoneid.getpolygon());
                    countryDevisionPolygonDto.setPolygone(locationDtos);
                }
                countryDevisionDtos.add(countryDevisionPolygonDto);

            }

            return countryDevisionDtos;
        } else {
            throw BusinessException.valueException(EntityType.CountryDevision, "hub.not.found");
        }

    }

    @Override
    public List<SelectResponse> findByTypes(Long typeId) {
        return countryDevisionRepository.findAll((Specification<CountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("countryType"), CountryType.findByValue(typeId))));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }).stream().map(this::toSelect).collect(Collectors.toList());


    }

    @Override
    public List<SelectResponse> selectByTypes(String filter) {
        return countryDevisionRepository.findAll((Specification<CountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(filter))
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + filter + "%")));

            List<Long> ids = new ArrayList<>();
            ids.add(3L);
            ids.add(4L);
            ids.add(5L);
            predicates.add(criteriaBuilder.and(root.get("countryType").in(ids)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }).stream().map(this::toSelect).collect(Collectors.toList());


    }

    @Override
    public List<SelectResponse> findByCity(String filter) {
        return countryDevisionRepository.findAll((Specification<CountryDevision>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + filter + "%")));

            List<Long> ids = new ArrayList<>();
            ids.add(0L);
            ids.add(1L);
            predicates.add(criteriaBuilder.and(root.get("countryType").in(ids)));


            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }).stream().map(this::toSelectpriviance).collect(Collectors.toList());


    }

    @Override
    public CountryDevisionPolygonDto findbyBaseHub(String hubid) {
        Hub byCode = hubRepository.findById(Long.valueOf(hubid)).orElseThrow();
        CountryDevisionPolygonDto countryDevisionPolygonDto = new CountryDevisionPolygonDto();
        if (byCode != null) {
            CountryDevision byHubId = countryDevisionRepository.findByHubIdAndCountryType(byCode.getId(), CountryType.hubRegion);
            ZonehubInterfaceDto bysubZoneid = hubRepository.findBysubZoneidAndHubID(byHubId.getId(), Long.valueOf(hubid));
            if (bysubZoneid != null) {
                List<LocationDto> locationDtos = hubService.convertclobtoList(bysubZoneid.getpolygon());
                countryDevisionPolygonDto.setPolygone(locationDtos);
            }
            return countryDevisionPolygonDto;
        } else {
            throw BusinessException.valueException(EntityType.CountryDevision, "hub.not.found");
        }


    }

    @Override
    public CountryDevisionDto selectByIdfindById(Long id) {

        CountryDevision loc = findById(id);
        CountryDevisionDto countryDevisionDto = countryDevisionConverter.fromModeltoDto(loc);
        countryDevisionDto.setChilds(null);
        if (countryDevisionDto.getParent() != null) {
            countryDevisionDto.getParent().setChilds(null);
            CountryDevisionDto countryDevisionDto1 = new CountryDevisionDto();
            countryDevisionDto1.setId(countryDevisionDto.getParent().getId());
            countryDevisionDto1.setName(countryDevisionDto.getParent().getName());
            countryDevisionDto1.setCode(countryDevisionDto.getParent().getCode());
            if (countryDevisionDto.getLatitude() != null)
                countryDevisionDto1.setLatitude(countryDevisionDto.getParent().getLatitude());
            if (countryDevisionDto.getLongtitude() != null)
                countryDevisionDto1.setLongtitude(countryDevisionDto.getParent().getLongtitude());

            if (countryDevisionDto.getCountryType() != null)
                countryDevisionDto1.setCountryType(countryDevisionDto.getParent().getCountryType());
            countryDevisionDto.setParent(countryDevisionDto1);
        }
        return countryDevisionDto;
    }

    @Override
    public List<SelectResponse> findByCityPickup(Long cityId) {

        List<Hub> all = hubRepository.findAll((Specification<Hub>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            Join<Object, Object> objectObjectJoin = root.join("city");
            List<Long> ids = new ArrayList<>();
            ids.add(3L);
            ids.add(5L);
            predicates.add(criteriaBuilder.and(objectObjectJoin.get("countryType").in(ids)));

            predicates.add(criteriaBuilder.equal(objectObjectJoin.get("city").get("id"), cityId));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });


        return all.stream().map(this::toSelectHub).collect(Collectors.toList());
    }

    @Override
    public List<SelectResponse> findByCityDelivery(Long cityId) {

        List<CountryType> ids = new ArrayList<>();
        ids.add(CountryType.deliveryRegion);
        ids.add(CountryType.pickupDeliveryRegion);
        List<Hub> all = countryDevisionRepository.findByCityDelivery(ids, cityId);
        return all.stream().map(this::toSelectHub).collect(Collectors.toList());
    }

    public SelectResponse toSelectHub(Hub hub) {
        return new SelectResponse(hub.getId(), hub.selectToString());
    }

    public ContryDevistionCodeDto toselectwithcode(CountryDevision countryDevision) {
        ContryDevistionCodeDto contryDevistionCodeDto = new ContryDevistionCodeDto();
        contryDevistionCodeDto.setCode(countryDevision.getCode());
        contryDevistionCodeDto.setText(countryDevision.getName());
        contryDevistionCodeDto.setId(countryDevision.getId());
        return contryDevistionCodeDto;
    }
}
