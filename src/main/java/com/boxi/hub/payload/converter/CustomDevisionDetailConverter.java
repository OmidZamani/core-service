package com.boxi.hub.payload.converter;

import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import com.boxi.hub.payload.dto.CustomDevisionDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomDevisionDetailConverter {

    CustomDevisionDetail fromDtoToModel(CustomDevisionDetailDto Dto);

    CustomDevisionDetailDto fromModelToDto(CustomDevisionDetail customCountryDevision);

    void updateFromDto(CustomCountryDevisionDto dto, @MappingTarget CustomCountryDevision customCountryDevision);

}
