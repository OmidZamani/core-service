package com.boxi.transport.payload.converter;

import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.payload.dto.AdmVehicleDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-05T14:04:32+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class AdmVehicleConverterImpl extends AdmVehicleSelectConverter {

    private final AdmVehicleConverter delegate;

    @Autowired
    public AdmVehicleConverterImpl(@Qualifier("delegate") AdmVehicleConverter delegate) {

        this.delegate = delegate;
    }

    @Override
    public Vehicle fromDtoToModel(AdmVehicleDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public void updateFromDto(AdmVehicleDto dto, Vehicle vehicle)  {
        delegate.updateFromDto( dto, vehicle );
    }

    @Override
    public AdmVehicleDto fromModelToDto(Vehicle vehicle)  {
        return delegate.fromModelToDto( vehicle );
    }
}
