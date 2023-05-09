package com.boxi.transport.payload.converter;


import com.boxi.core.response.SelectResponse;
import com.boxi.transport.enums.BagType;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import static java.util.Optional.ofNullable;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BagTypeConverter {


    default BagType fromValueToEnum(Long bagTypeId) {
        return BagType.findByValue(bagTypeId);
    }

    default
     Long fromEnumToValue(BagType bagType) {
        return ofNullable(bagType)
                .map(BagType::getValue)
                .orElse(null);
    }

    default SelectResponse fromEnumToSelect(BagType bagType) {
       return new SelectResponse(bagType.getValue(),bagType.getFa());
    }


}
