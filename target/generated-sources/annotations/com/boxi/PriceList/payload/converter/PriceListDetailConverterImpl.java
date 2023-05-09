package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.payload.dto.PriceListDetailDto;
import com.boxi.PriceList.payload.dto.PriceListDetailExcelDto;
import com.boxi.PriceList.payload.dto.PriceListDetailFilterDto;
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
public class PriceListDetailConverterImpl extends PriceListDetailConverters {

    @Autowired
    @Qualifier("delegate")
    private PriceListDetailConverter delegate;

    @Override
    public PriceListDetail fromDtoToModel(PriceListDetailDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public void updateFromDto(PriceListDetailDto dto, PriceListDetail attribute)  {
        delegate.updateFromDto( dto, attribute );
    }

    @Override
    public PriceListDetailDto fromModelToDto(PriceListDetail priceListDetail)  {
        return delegate.fromModelToDto( priceListDetail );
    }

    @Override
    public PriceListDetailDto fromExcelToDto(PriceListDetailExcelDto priceListDetail)  {
        return delegate.fromExcelToDto( priceListDetail );
    }

    @Override
    public PriceListDetailFilterDto fromModelToFilter(PriceListDetail priceListDetail)  {
        return delegate.fromModelToFilter( priceListDetail );
    }
}
