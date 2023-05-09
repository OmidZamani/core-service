package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.ServiceDelivery;
import com.boxi.PriceList.payload.dto.ServiceDeliveryDto;
import com.boxi.PriceList.payload.dto.ServiceDeliveryExcelDto;
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
public class ServiceDeliveryConverterImpl extends ServiceDeliveryConverterabs {

    @Autowired
    @Qualifier("delegate")
    private ServiceDeliveryConverter delegate;

    @Override
    public ServiceDelivery fromDtoToModel(ServiceDeliveryDto Dto)  {
        return delegate.fromDtoToModel( Dto );
    }

    @Override
    public ServiceDeliveryDto fromModelToDtoc(ServiceDelivery serviceDelivery)  {
        return delegate.fromModelToDtoc( serviceDelivery );
    }

    @Override
    public ServiceDeliveryDto fromExcelToDto(ServiceDeliveryExcelDto serviceDeliveryExcelDto)  {
        return delegate.fromExcelToDto( serviceDeliveryExcelDto );
    }
}
