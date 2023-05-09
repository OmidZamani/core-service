package com.boxi.transport.payload.converter;

import com.boxi.transport.entity.Route;
import com.boxi.transport.payload.dto.RouteDto;
import com.boxi.transport.payload.dto.RouteExcelDto;
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
public class RouteConverterImpl extends RouteSelectConverter {

    private final RouteConverter delegate;

    @Autowired
    public RouteConverterImpl(@Qualifier("delegate") RouteConverter delegate) {

        this.delegate = delegate;
    }

    @Override
    public Route fromDtoToModel(RouteDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public RouteDto fromModelToDto(Route saved)  {
        return delegate.fromModelToDto( saved );
    }

    @Override
    public RouteDto fromExcelToDto(RouteExcelDto saved)  {
        return delegate.fromExcelToDto( saved );
    }

    @Override
    public void updateFromDto(RouteDto dto, Route route)  {
        delegate.updateFromDto( dto, route );
    }
}
