package com.boxi.transport.payload.converter;

import com.boxi.transport.entity.VehicleCategory;
import com.boxi.transport.payload.dto.VehicleCategoryDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:48+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class VehicleCategoryConverterImpl extends VehicleCategorySelectConverter {

    private final VehicleCategoryConverter delegate;

    @Autowired
    public VehicleCategoryConverterImpl(@Qualifier("delegate") VehicleCategoryConverter delegate) {

        this.delegate = delegate;
    }

    @Override
    public VehicleCategory fromDtoToModel(VehicleCategoryDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public VehicleCategoryDto fromModelToDto(VehicleCategory model)  {
        return delegate.fromModelToDto( model );
    }
}
