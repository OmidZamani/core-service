package com.boxi.transport.payload.converter;


import com.boxi.core.response.SelectResponse;
import com.boxi.transport.enums.FuelType;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import static java.util.Optional.ofNullable;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FuelTypeConverter  {


    default FuelType fromValueToEnum(Long fuelTypeId) {
        return FuelType.findByValue(fuelTypeId);
    }

    default
     Long fromEnumToValue(FuelType fuelType) {
        return ofNullable(fuelType)
                .map(FuelType::getValue)
                .orElse(null);
    }

    default SelectResponse fromEnumToSelect(FuelType fuelType) {
       return new SelectResponse(fuelType.getValue(),fuelType.getFa());
    }


}
