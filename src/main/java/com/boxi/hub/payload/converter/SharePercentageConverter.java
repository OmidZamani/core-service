package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.SharePercentage;
import com.boxi.hub.payload.dto.SharePercentageDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SharePercentageConverter {

    SharePercentageDto fromModelToDto(SharePercentage sharePercentage);

    SharePercentage fromDtoToModel(SharePercentageDto dto);

    @AfterMapping
    default void afterFromModelToDto(SharePercentage sharePercentage, @MappingTarget SharePercentageDto dto) {
        if (sharePercentage.getBankAccount() != null)
            dto.setBankAccount(new SelectResponse(sharePercentage.getBankAccount().getId(), ""));


    }
}
