package com.boxi.crm.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.SalesChannel;
import com.boxi.crm.payload.dto.SalesChannelDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SalesChannelConverter {

    SalesChannel fromDtoToModel(SalesChannelDto Dto);

    SalesChannelDto fromModelToDto(SalesChannel segment);

    SelectResponse ModelToSelect(SalesChannelDto salesChannelDto);


    @AfterMapping
    default void afterModelToSelect(SalesChannelDto Dto, @MappingTarget SelectResponse selectResponse) {

        selectResponse.setId(Dto.getId());
        selectResponse.setText(Dto.getName());


    }


}
