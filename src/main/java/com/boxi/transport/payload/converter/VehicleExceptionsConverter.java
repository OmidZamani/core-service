package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleExceptions;
import com.boxi.transport.payload.dto.VehicleExceptionsDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface VehicleExceptionsConverter {

    @Mapping(source = "dto.selectException.id", target = "exception.id")
    @Mapping(source = "dto.selectVehicle.id", target = "vehicle.id")
    VehicleExceptions fromDtoToModel(VehicleExceptionsDto dto);

    @Mapping(target = "selectException", ignore = true)
    @Mapping(target = "selectVehicle.id", source = "vehicle.id")
    VehicleExceptionsDto fromModelToDto(VehicleExceptions vehicleExceptions);

    @AfterMapping
    default void afterfromDtoToModel(VehicleExceptions vehicleExceptions, @MappingTarget VehicleExceptionsDto dto) {
        if (vehicleExceptions.getException() != null)
            dto.setSelectException(new SelectResponse(vehicleExceptions.getId(), vehicleExceptions.getDescription()));
    }

}
