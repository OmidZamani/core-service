package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.ServiceDelivery;
import com.boxi.PriceList.entity.ServiceDeliveryCustomers;
import com.boxi.PriceList.payload.dto.ServiceDeliveryCustomersDto;
import com.boxi.core.response.SelectResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class ServiceDeliveryCustomersConverterImpl implements ServiceDeliveryCustomersConverter {

    @Override
    public ServiceDeliveryCustomers fromDtoToModel(ServiceDeliveryCustomersDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        ServiceDeliveryCustomers serviceDeliveryCustomers = new ServiceDeliveryCustomers();

        if ( Dto.getServiceDelivery() != null ) {
            serviceDeliveryCustomers.setServiceDelivery( selectResponseToServiceDelivery( Dto.getServiceDelivery() ) );
        }
        if ( Dto.getId() != null ) {
            serviceDeliveryCustomers.setId( Dto.getId() );
        }
        if ( Dto.getCustomerId() != null ) {
            serviceDeliveryCustomers.setCustomerId( Dto.getCustomerId() );
        }

        return serviceDeliveryCustomers;
    }

    @Override
    public ServiceDeliveryCustomersDto fromModelToDto(ServiceDeliveryCustomers customCountryDevision) {
        if ( customCountryDevision == null ) {
            return null;
        }

        ServiceDeliveryCustomersDto serviceDeliveryCustomersDto = new ServiceDeliveryCustomersDto();

        if ( customCountryDevision.getServiceDelivery() != null ) {
            serviceDeliveryCustomersDto.setServiceDelivery( serviceDeliveryToSelectResponse( customCountryDevision.getServiceDelivery() ) );
        }
        if ( customCountryDevision.getId() != null ) {
            serviceDeliveryCustomersDto.setId( customCountryDevision.getId() );
        }
        if ( customCountryDevision.getCustomerId() != null ) {
            serviceDeliveryCustomersDto.setCustomerId( customCountryDevision.getCustomerId() );
        }

        return serviceDeliveryCustomersDto;
    }

    protected ServiceDelivery selectResponseToServiceDelivery(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        ServiceDelivery serviceDelivery = new ServiceDelivery();

        if ( selectResponse.getId() != null ) {
            serviceDelivery.setId( selectResponse.getId() );
        }

        return serviceDelivery;
    }

    protected SelectResponse serviceDeliveryToSelectResponse(ServiceDelivery serviceDelivery) {
        if ( serviceDelivery == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( serviceDelivery.getId() != null ) {
            selectResponse.setId( serviceDelivery.getId() );
        }
        if ( serviceDelivery.getName() != null ) {
            selectResponse.setText( serviceDelivery.getName() );
        }

        return selectResponse;
    }
}
