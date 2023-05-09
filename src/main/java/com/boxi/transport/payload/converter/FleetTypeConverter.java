package com.boxi.transport.payload.converter;


import com.boxi.core.response.SelectResponse;
import com.boxi.transport.enums.FleetType;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import static java.util.Optional.ofNullable;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FleetTypeConverter {


    default FleetType fromValueToEnum(Long fleetTypeId) {
        return FleetType.findByValue(fleetTypeId);
    }

    default
     Long fromEnumToValue(FleetType fleetType) {
        return ofNullable(fleetType)
                .map(FleetType::getValue)
                .orElse(null);
    }

    default SelectResponse fromEnumToSelect(FleetType fleetType) {
       return new SelectResponse(fleetType.getValue(),fleetType.getFa());
    }


}
