package com.boxi.transport.payload.converter;

import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.payload.dto.VehicleDto;
import com.boxi.transport.payload.dto.VehicleExcelDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class VehicleConverterImpl extends VehicleSelectConverter {

    private final VehicleConverter delegate;

    @Autowired
    public VehicleConverterImpl(@Qualifier("delegate") VehicleConverter delegate) {

        this.delegate = delegate;
    }

    @Override
    public Vehicle fromDtoToModel(VehicleDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public void updateFromDto(VehicleDto dto, Vehicle vehicle)  {
        delegate.updateFromDto( dto, vehicle );
    }

    @Override
    public VehicleDto fromModelToDto(Vehicle vehicle)  {
        return delegate.fromModelToDto( vehicle );
    }

    @Override
    public VehicleDto fromExcelToDto(VehicleExcelDto vehicle)  {
        return delegate.fromExcelToDto( vehicle );
    }
}
