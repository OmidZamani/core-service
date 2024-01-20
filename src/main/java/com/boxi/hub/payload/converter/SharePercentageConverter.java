package com.boxi.hub.payload.converter;

import com.boxi.hub.entity.SharePercentage;
import com.boxi.hub.payload.dto.SharePercentageDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SharePercentageConverter {

    SharePercentageDto fromModelToDto(SharePercentage sharePercentage);

    SharePercentage fromDtoToModel(SharePercentageDto dto);
}
