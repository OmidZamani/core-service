package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.Enum.DeliveryDiscountType;

import com.boxi.PriceList.entity.DeliveryDiscount;

import com.boxi.PriceList.payload.dto.DeliveryDiscountDto;

import com.boxi.core.response.SelectResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DeliveryDiscountConverter {

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "serviceDelivery", ignore = true)
    DeliveryDiscount fromDtoToModel(DeliveryDiscountDto dto);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "serviceDelivery", ignore = true)
    DeliveryDiscountDto fromModelToDto(DeliveryDiscount deliveryDiscount);


    @AfterMapping
    default void validate(DeliveryDiscountDto dto, @MappingTarget DeliveryDiscount deliveryDiscount) {
        if (dto.getType() != null)
            deliveryDiscount.setType(dto.getType().getId());
    }

    @AfterMapping
    default void validateDto(DeliveryDiscount exception, @MappingTarget DeliveryDiscountDto dto) {
        if (exception.getType() != null) {
            DeliveryDiscountType byValue = DeliveryDiscountType.findByValue(exception.getType());

            dto.setType(new SelectResponse(byValue.getValue(),byValue.getType()));
        }
    }
}
