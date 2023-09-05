package com.boxi.hub.payload.converter;

import com.boxi.hub.entity.PudoStation;
import com.boxi.hub.payload.dto.PudoStationDto;
import com.boxi.utils.DateUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PudoStationConverter {

    @Mapping(ignore = true, target = "locationStartDate")
    PudoStationDto fromModelToDto(PudoStation PudoStation);

    @Mapping(ignore = true, target = "locationStartDate")
    PudoStation fromDtoToModel(PudoStationDto dto);

    @AfterMapping
    default void afterFromModelToDto(PudoStation PudoStation, @MappingTarget PudoStationDto dto) {
        if (PudoStation.getLocationStartDate() != null) {
            dto.setLocationStartDate(DateUtil.convertDateToJalaliDateDto(PudoStation.getLocationStartDate()));
        }
    }

    @AfterMapping
    default void afterFromDtoToModel(PudoStationDto dto, @MappingTarget PudoStation PudoStation) {
        if (dto.getLocationStartDate() != null) {
            PudoStation.setLocationStartDate(DateUtil.convertDateToJalaliDateDto(dto.getLocationStartDate()));
        }
    }

}
