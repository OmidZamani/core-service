package com.boxi.product.payload.converter;

import com.boxi.product.entity.DiscountCodesArrangement;
import com.boxi.product.payload.dto.DiscountCodesArrangementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DiscountCodesArrangementConverter {


    @Mapping(ignore = true, target = "discountCode")
    DiscountCodesArrangement fromDtoToModel(DiscountCodesArrangementDto dto);
    @Mapping(ignore = true, target = "discountCode")
    DiscountCodesArrangementDto fromModelToDto(DiscountCodesArrangement discountCodesArrangement);
}
