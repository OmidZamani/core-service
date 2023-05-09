package com.boxi.transport.payload.converter;

import com.boxi.transport.entity.VehicleMake;
import com.boxi.transport.payload.dto.VehicleMakeDto;
import com.boxi.transport.payload.dto.VehicleMakeExcelDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class VehicleMakeConverterImpl extends VehicleMakeSelectConverter {

    private final VehicleMakeConverter delegate;

    @Autowired
    public VehicleMakeConverterImpl(@Qualifier("delegate") VehicleMakeConverter delegate) {

        this.delegate = delegate;
    }

    @Override
    public VehicleMake fromDtoToModel(VehicleMakeDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public void updateFromDto(VehicleMakeDto dto, VehicleMake vehicleMake)  {
        delegate.updateFromDto( dto, vehicleMake );
    }

    @Override
    public VehicleMakeDto fromModelToDto(VehicleMake vehicleMake)  {
        return delegate.fromModelToDto( vehicleMake );
    }

    @Override
    public VehicleMakeDto fromExcelToDto(VehicleMakeExcelDto vehicleMake)  {
        return delegate.fromExcelToDto( vehicleMake );
    }
}
