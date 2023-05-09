package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.enums.HubType;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import static java.util.Optional.ofNullable;

    @Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    public interface HubTypeConverter {


        default HubType fromValueToEnum(Long hubTypeId) {
            return HubType.findByValue(hubTypeId);
        }

        default
        Long fromEnumToValue(HubType hubType) {
            return ofNullable(hubType)
                    .map(HubType::getValue)
                    .orElse(null);
        }

        default SelectResponse fromEnumToSelect(HubType hubType) {
            return new SelectResponse(hubType.getValue(),hubType.getFa());
        }
}
