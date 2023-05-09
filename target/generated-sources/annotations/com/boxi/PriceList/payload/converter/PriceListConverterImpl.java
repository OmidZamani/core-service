package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.payload.dto.PriceListDto;
import com.boxi.PriceList.payload.dto.PriceListExcelDto;
import com.boxi.PriceList.payload.dto.PriceListFilterDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T14:38:18+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class PriceListConverterImpl extends PriceListConverters {

    @Autowired
    @Qualifier("delegate")
    private PriceListConverter delegate;

    @Override
    public PriceList fromDtoToModel(PriceListDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public void updateFromDto(PriceListDto dto, PriceList attribute)  {
        delegate.updateFromDto( dto, attribute );
    }

    @Override
    public PriceListDto fromModelToDto(PriceList productAttribute)  {
        return delegate.fromModelToDto( productAttribute );
    }

    @Override
    public PriceListFilterDto fromDtoSelectToModel(PriceListDto dto)  {
        return delegate.fromDtoSelectToModel( dto );
    }

    @Override
    public PriceListDto fromExcelToDto(PriceListExcelDto excel)  {
        return delegate.fromExcelToDto( excel );
    }

    @Override
    public PriceListFilterDto LoadfromFilterMap(PriceList dto)  {
        return delegate.LoadfromFilterMap( dto );
    }
}
