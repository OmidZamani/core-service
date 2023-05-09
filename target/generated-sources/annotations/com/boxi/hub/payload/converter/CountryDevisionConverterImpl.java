package com.boxi.hub.payload.converter;

import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.payload.dto.CountryDevisionPolygonDto;
import com.boxi.product.payload.dto.CountryDevisionDto;
import com.boxi.product.response.ContryDevistionSelect;
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
public class CountryDevisionConverterImpl extends CountryDevisionSelectConverter {

    @Autowired
    @Qualifier("delegate")
    private CountryDevisionConverter delegate;

    @Override
    public ContryDevistionSelect fromModelToType(CountryDevision c)  {
        return delegate.fromModelToType( c );
    }

    @Override
    public CountryDevision fromDtotoModel(CountryDevisionDto dto)  {
        return delegate.fromDtotoModel( dto );
    }

    @Override
    public CountryDevisionDto fromModeltoDto(CountryDevision countryDevision)  {
        return delegate.fromModeltoDto( countryDevision );
    }

    @Override
    public CountryDevisionPolygonDto fromModeltoPolygoneDto(CountryDevisionDto countryDevision)  {
        return delegate.fromModeltoPolygoneDto( countryDevision );
    }
}
