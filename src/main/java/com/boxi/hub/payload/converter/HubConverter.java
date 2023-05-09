package com.boxi.hub.payload.converter;


import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubDto;
import com.boxi.hub.payload.dto.HubWithLocationDto;
import com.boxi.hub.payload.dto.ZoneHubDto;
import com.boxi.hub.repo.CountryDevisionRepository;
import com.boxi.hub.repo.HubRepository;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.product.payload.dto.CountryDevisionDto;
import com.boxi.utils.DateUtil;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {HubCategoryConverter.class, CountryDevisionConverter.class, HubTypeConverter.class})
@DecoratedWith(HubSelectConverter.class)
public interface HubConverter {


    //TODO manager
    @Mapping(source = "dto.selectHubType.id", target = "type")
    @Mapping(source = "dto.selectParentHub", target = "parentHub")
    @Mapping(source = "dto.selectHubCategory", target = "hubCategory")
    @Mapping(source = "dto.selectCity", target = "city")
    @Mapping(target = "locationStartDate", ignore = true)
    @Mapping(target = "code", ignore = true)
    Hub fromDtoToModel(HubDto dto);


    @Mapping(source = "id", target = "hubId")
    @Mapping(source = "code", target = "hubCode")
    ZoneHubDto fromHubToZone(Hub hub);

    //TODO manager
    @Mapping(source = "type", target = "selectHubType.id")
    @Mapping(source = "parentHub", target = "selectParentHub")
    @Mapping(source = "hubCategory", target = "selectHubCategory")
    @Mapping(source = "city", target = "selectCity")
    @Mapping(target = "locationStartDate", ignore = true)
    HubDto fromModelToDto(Hub hub);


    HubWithLocationDto fromModelToHubWithLocationDto(Hub hub);

    @Mapping(source = "dto.selectHubType.id", target = "type")
    @Mapping(source = "dto.selectParentHub", target = "parentHub")
    @Mapping(source = "dto.selectHubCategory", target = "hubCategory")
    @Mapping(source = "dto.selectCity", target = "city")
    @Mapping(target = "locationStartDate", ignore = true)
    @Mapping(target = "code", ignore = true)
    void updateFromDto(HubDto dto, @MappingTarget Hub hub);


    SelectResponse hubToSelect(Hub h);

    Hub selectToHub(SelectResponse select);


    @AfterMapping
    default void validatefromModelToHubWithLocationDto(Hub hub, @MappingTarget HubWithLocationDto dto) {

        if (hub.getLocLong() != null) dto.setLocLong(hub.getLocLong());
        if (hub.getLocLate() != null) dto.setLocLate(hub.getLocLate());
        if (hub.getCity() != null)
            dto.setSelectCity(new SelectResponse(hub.getCity().getId(), hub.getCity().getName()));
        if (hub.getIsPickupPossible() != null) dto.setIsPickupPossible(hub.getIsPickupPossible());
        if (hub.getIsArrivalScanPossible() != null) dto.setIsArrivalScanPossible(hub.getIsArrivalScanPossible());
        if (hub.getIsDeliveryPossible() != null) dto.setIsDeliveryPossible(hub.getIsDeliveryPossible());
    }

    @AfterMapping

    default void validate(Hub hub, @MappingTarget HubDto dto) {
        if (hub.getParentHub() != null) {
            dto.setSelectParentHub(new SelectResponse(hub.getParentHub().getId(), hub.getParentHub().selectToString()));
        }

        if (hub.getLocationStartDate() != null) {
            Date from = hub.getLocationStartDate();
            dto.setLocationStartDate(DateUtil.convertDateToJalaliDateDto(from));
        }

        if (hub.getManagerId() != null)
            dto.setSelectManager(new SelectResponse(hub.getManagerId(), hub.getManagerId().toString()));

    }

    @AfterMapping
    default void after(HubDto hubDto, @MappingTarget Hub hub) {
        if (SelectUtil.NZ_CHECK(hubDto.getSelectParentHub())) {
            hubDto.setSelectParentHub(null);
        }

        if (hubDto.getLocationStartDate() != null) {
            DateDto from = hubDto.getLocationStartDate();
            hub.setLocationStartDate(DateUtil.convertJalaliDayTimeToTimeStamp(from));
        }
    }
}

abstract class HubSelectConverter implements HubConverter {

    @Autowired
    private HubRepository hubRepository;

    @Autowired
    private CountryDevisionRepository countryDevisionRepository;

    @Autowired
    private CountryDevisionConverter countryDevisionConverter;

    @Autowired
    private HubConverter hubConverter;

    @Override
    public HubDto fromModelToDto(Hub hub) {

        HubDto hubDto = hubConverter.fromModelToDto(hub);
        if (hub.getCity() != null) {
            CountryDevisionDto countryDevisionDto = countryDevisionConverter.fromModeltoDto(countryDevisionRepository.findById(hub.getCity().getId()).orElseThrow());
            if (countryDevisionDto.getParent() != null)
                hubDto.setSelectRegion(new SelectResponse(countryDevisionDto.getParent().getId(), countryDevisionDto.getParent().getName()));
        }

        if (hub.getType() != null)
            hubDto.setSelectHubType(new SelectResponse(hub.getType().getValue(), hub.getType().getFa()));

        return hubDto;
    }

    @Override
    public SelectResponse hubToSelect(Hub h) {
        return new SelectResponse(h.getId(), h.selectToString());
    }

    @Override
    public Hub selectToHub(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return hubRepository.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.Hub, ExceptionType.ENTITY_NOT_FOUND);
        });
    }


}
