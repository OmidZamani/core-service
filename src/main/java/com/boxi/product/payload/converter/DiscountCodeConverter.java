package com.boxi.product.payload.converter;

import com.boxi.product.entity.DiscountCode;
import com.boxi.product.payload.dto.DiscountCodeDto;
import com.boxi.utils.DateUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DiscountCodeConverter {

    @Mapping(ignore = true, target = "validDateFrom")
    @Mapping(ignore = true, target = "validDateTo")
//    @Mapping(ignore = true, target = "arrangements")
    DiscountCode fromDtoToModel(DiscountCodeDto dto);

    @Mapping(ignore = true, target = "validDateFrom")
    @Mapping(ignore = true, target = "validDateTo")
//    @Mapping(ignore = true, target = "arrangements")
    DiscountCodeDto fromModelToDto(DiscountCode discountCode);

    @AfterMapping
    default void afterDtoToModel(DiscountCodeDto dto, @MappingTarget DiscountCode discountCode) {
        if (dto.getValidDateFrom() != null)
            discountCode.setValidDateFrom(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getValidDateFrom()));

        if (dto.getValidDateTo() != null)
            discountCode.setValidDateTo(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getValidDateTo()));
    }

    @AfterMapping
    default void afterModelToDto(DiscountCode discountCode, @MappingTarget DiscountCodeDto dto) {
        if (discountCode.getValidDateFrom() != null)
            dto.setValidDateFrom(DateUtil.convertTimeStampStringToJalaliObject(discountCode.getValidDateFrom()));

        if (discountCode.getValidDateTo() != null)
            dto.setValidDateTo(DateUtil.convertTimeStampStringToJalaliObject(discountCode.getValidDateTo()));
    }
}
