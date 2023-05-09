package com.boxi.hub.payload.converter;

import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import com.boxi.hub.payload.dto.CustomCountryDevisionExcelDto;
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
public class CustomCountryDevisionConverterImpl extends CustomCountryDevisionConverterabs {

    @Autowired
    @Qualifier("delegate")
    private CustomCountryDevisionConverter delegate;

    @Override
    public CustomCountryDevision fromDtoToModel(CustomCountryDevisionDto Dto)  {
        return delegate.fromDtoToModel( Dto );
    }

    @Override
    public CustomCountryDevisionDto fromModelToDto(CustomCountryDevision customCountryDevision)  {
        return delegate.fromModelToDto( customCountryDevision );
    }

    @Override
    public CustomCountryDevisionDto fromExcelToDto(CustomCountryDevisionExcelDto customCountryDevisionDto)  {
        return delegate.fromExcelToDto( customCountryDevisionDto );
    }

    @Override
    public void updateFromDto(CustomCountryDevisionDto dto, CustomCountryDevision customCountryDevision)  {
        delegate.updateFromDto( dto, customCountryDevision );
    }
}
