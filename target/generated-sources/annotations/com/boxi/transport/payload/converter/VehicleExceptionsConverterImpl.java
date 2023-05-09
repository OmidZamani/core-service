package com.boxi.transport.payload.converter;

import com.boxi.PriceList.entity.Exception;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.entity.VehicleExceptions;
import com.boxi.transport.payload.dto.VehicleExceptionsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T15:35:12+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class VehicleExceptionsConverterImpl implements VehicleExceptionsConverter {

    @Override
    public VehicleExceptions fromDtoToModel(VehicleExceptionsDto dto) {
        if ( dto == null ) {
            return null;
        }

        VehicleExceptions vehicleExceptions = new VehicleExceptions();

        if ( dto.getSelectException() != null ) {
            vehicleExceptions.setException( selectResponseToException( dto.getSelectException() ) );
        }
        if ( dto.getSelectVehicle() != null ) {
            vehicleExceptions.setVehicle( selectResponseToVehicle( dto.getSelectVehicle() ) );
        }
        if ( dto.getId() != null ) {
            vehicleExceptions.setId( dto.getId() );
        }
        if ( dto.getDescription() != null ) {
            vehicleExceptions.setDescription( dto.getDescription() );
        }

        return vehicleExceptions;
    }

    @Override
    public VehicleExceptionsDto fromModelToDto(VehicleExceptions vehicleExceptions) {
        if ( vehicleExceptions == null ) {
            return null;
        }

        VehicleExceptionsDto vehicleExceptionsDto = new VehicleExceptionsDto();

        if ( vehicleExceptions.getVehicle() != null ) {
            vehicleExceptionsDto.setSelectVehicle( vehicleToSelectResponse( vehicleExceptions.getVehicle() ) );
        }
        if ( vehicleExceptions.getId() != null ) {
            vehicleExceptionsDto.setId( vehicleExceptions.getId() );
        }
        if ( vehicleExceptions.getDescription() != null ) {
            vehicleExceptionsDto.setDescription( vehicleExceptions.getDescription() );
        }

        afterfromDtoToModel( vehicleExceptions, vehicleExceptionsDto );

        return vehicleExceptionsDto;
    }

    protected Exception selectResponseToException(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        Exception exception = new Exception();

        if ( selectResponse.getId() != null ) {
            exception.setId( selectResponse.getId() );
        }

        return exception;
    }

    protected Vehicle selectResponseToVehicle(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        if ( selectResponse.getId() != null ) {
            vehicle.setId( selectResponse.getId() );
        }

        return vehicle;
    }

    protected SelectResponse vehicleToSelectResponse(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( vehicle.getId() != null ) {
            selectResponse.setId( vehicle.getId() );
        }

        return selectResponse;
    }
}
