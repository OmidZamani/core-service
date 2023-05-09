package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.ServiceDelivery;
import com.boxi.PriceList.entity.ServiceDeliveryCustomers;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.ExcelSelectResponseDto;
import com.boxi.PriceList.payload.dto.ServiceDeliveryCustomersDto;
import com.boxi.PriceList.payload.dto.ServiceDeliveryDto;
import com.boxi.PriceList.payload.dto.ServiceDeliveryExcelDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SalesChannel;
import com.boxi.crm.payload.converter.SalesChannelConverter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class ServiceDeliveryConverterImpl_ implements ServiceDeliveryConverter {

    @Autowired
    private SalesChannelConverter salesChannelConverter;

    @Override
    public ServiceDelivery fromDtoToModel(ServiceDeliveryDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        ServiceDelivery serviceDelivery = new ServiceDelivery();

        if ( Dto.getService() != null ) {
            serviceDelivery.setService( selectResponseToServices( Dto.getService() ) );
        }
        if ( Dto.getId() != null ) {
            serviceDelivery.setId( Dto.getId() );
        }
        if ( Dto.getIsActive() != null ) {
            serviceDelivery.setIsActive( Dto.getIsActive() );
        }
        if ( Dto.getIsDeleted() != null ) {
            serviceDelivery.setIsDeleted( Dto.getIsDeleted() );
        }
        if ( Dto.getCode() != null ) {
            serviceDelivery.setCode( Dto.getCode() );
        }
        if ( Dto.getName() != null ) {
            serviceDelivery.setName( Dto.getName() );
        }
        if ( Dto.getDescription() != null ) {
            serviceDelivery.setDescription( Dto.getDescription() );
        }
        List<SalesChannel> list = selectResponseListToSalesChannelList( Dto.getSaleschannels() );
        if ( list != null ) {
            serviceDelivery.setSaleschannels( list );
        }
        List<CustomerSegment> list1 = selectResponseListToCustomerSegmentList( Dto.getCustomerSegments() );
        if ( list1 != null ) {
            serviceDelivery.setCustomerSegments( list1 );
        }
        List<ServiceDeliveryCustomers> list2 = serviceDeliveryCustomersDtoListToServiceDeliveryCustomersList( Dto.getServiceDeliveryCustomers() );
        if ( list2 != null ) {
            serviceDelivery.setServiceDeliveryCustomers( list2 );
        }
        if ( Dto.getDiscountPercent() != null ) {
            serviceDelivery.setDiscountPercent( Dto.getDiscountPercent() );
        }

        afterDtoToModel( Dto, serviceDelivery );

        return serviceDelivery;
    }

    @Override
    public ServiceDeliveryDto fromModelToDto(ServiceDelivery serviceDelivery) {
        if ( serviceDelivery == null ) {
            return null;
        }

        ServiceDeliveryDto serviceDeliveryDto = new ServiceDeliveryDto();

        if ( serviceDelivery.getService() != null ) {
            serviceDeliveryDto.setService( servicesToSelectResponse( serviceDelivery.getService() ) );
        }
        if ( serviceDelivery.getId() != null ) {
            serviceDeliveryDto.setId( serviceDelivery.getId() );
        }
        if ( serviceDelivery.getIsActive() != null ) {
            serviceDeliveryDto.setIsActive( serviceDelivery.getIsActive() );
        }
        if ( serviceDelivery.getIsDeleted() != null ) {
            serviceDeliveryDto.setIsDeleted( serviceDelivery.getIsDeleted() );
        }
        if ( serviceDelivery.getCode() != null ) {
            serviceDeliveryDto.setCode( serviceDelivery.getCode() );
        }
        if ( serviceDelivery.getName() != null ) {
            serviceDeliveryDto.setName( serviceDelivery.getName() );
        }
        if ( serviceDelivery.getDescription() != null ) {
            serviceDeliveryDto.setDescription( serviceDelivery.getDescription() );
        }
        List<SelectResponse> list = salesChannelListToSelectResponseList( serviceDelivery.getSaleschannels() );
        if ( list != null ) {
            serviceDeliveryDto.setSaleschannels( list );
        }
        List<SelectResponse> list1 = customerSegmentListToSelectResponseList( serviceDelivery.getCustomerSegments() );
        if ( list1 != null ) {
            serviceDeliveryDto.setCustomerSegments( list1 );
        }
        List<ServiceDeliveryCustomersDto> list2 = serviceDeliveryCustomersListToServiceDeliveryCustomersDtoList( serviceDelivery.getServiceDeliveryCustomers() );
        if ( list2 != null ) {
            serviceDeliveryDto.setServiceDeliveryCustomers( list2 );
        }
        if ( serviceDelivery.getDiscountPercent() != null ) {
            serviceDeliveryDto.setDiscountPercent( serviceDelivery.getDiscountPercent() );
        }

        afterModelToDto( serviceDelivery, serviceDeliveryDto );

        return serviceDeliveryDto;
    }

    @Override
    public ServiceDeliveryDto fromModelToDtoc(ServiceDelivery serviceDelivery) {
        if ( serviceDelivery == null ) {
            return null;
        }

        ServiceDeliveryDto serviceDeliveryDto = new ServiceDeliveryDto();

        if ( serviceDelivery.getService() != null ) {
            serviceDeliveryDto.setService( servicesToSelectResponse1( serviceDelivery.getService() ) );
        }
        if ( serviceDelivery.getId() != null ) {
            serviceDeliveryDto.setId( serviceDelivery.getId() );
        }
        if ( serviceDelivery.getIsActive() != null ) {
            serviceDeliveryDto.setIsActive( serviceDelivery.getIsActive() );
        }
        if ( serviceDelivery.getIsDeleted() != null ) {
            serviceDeliveryDto.setIsDeleted( serviceDelivery.getIsDeleted() );
        }
        if ( serviceDelivery.getCode() != null ) {
            serviceDeliveryDto.setCode( serviceDelivery.getCode() );
        }
        if ( serviceDelivery.getName() != null ) {
            serviceDeliveryDto.setName( serviceDelivery.getName() );
        }
        if ( serviceDelivery.getDescription() != null ) {
            serviceDeliveryDto.setDescription( serviceDelivery.getDescription() );
        }
        List<SelectResponse> list = salesChannelListToSelectResponseList( serviceDelivery.getSaleschannels() );
        if ( list != null ) {
            serviceDeliveryDto.setSaleschannels( list );
        }
        List<SelectResponse> list1 = customerSegmentListToSelectResponseList( serviceDelivery.getCustomerSegments() );
        if ( list1 != null ) {
            serviceDeliveryDto.setCustomerSegments( list1 );
        }
        List<ServiceDeliveryCustomersDto> list2 = serviceDeliveryCustomersListToServiceDeliveryCustomersDtoList( serviceDelivery.getServiceDeliveryCustomers() );
        if ( list2 != null ) {
            serviceDeliveryDto.setServiceDeliveryCustomers( list2 );
        }
        if ( serviceDelivery.getDiscountPercent() != null ) {
            serviceDeliveryDto.setDiscountPercent( serviceDelivery.getDiscountPercent() );
        }

        afterModelToDto( serviceDelivery, serviceDeliveryDto );

        return serviceDeliveryDto;
    }

    @Override
    public ServiceDeliveryDto fromExcelToDto(ServiceDeliveryExcelDto serviceDeliveryExcelDto) {
        if ( serviceDeliveryExcelDto == null ) {
            return null;
        }

        ServiceDeliveryDto serviceDeliveryDto = new ServiceDeliveryDto();

        if ( serviceDeliveryExcelDto.getIsActive() != null ) {
            serviceDeliveryDto.setIsActive( serviceDeliveryExcelDto.getIsActive() );
        }
        if ( serviceDeliveryExcelDto.getCode() != null ) {
            serviceDeliveryDto.setCode( serviceDeliveryExcelDto.getCode() );
        }
        if ( serviceDeliveryExcelDto.getName() != null ) {
            serviceDeliveryDto.setName( serviceDeliveryExcelDto.getName() );
        }
        List<SelectResponse> list = excelSelectResponseDtoListToSelectResponseList( serviceDeliveryExcelDto.getSaleschannels() );
        if ( list != null ) {
            serviceDeliveryDto.setSaleschannels( list );
        }
        List<SelectResponse> list1 = excelSelectResponseDtoListToSelectResponseList( serviceDeliveryExcelDto.getCustomerSegments() );
        if ( list1 != null ) {
            serviceDeliveryDto.setCustomerSegments( list1 );
        }
        List<ServiceDeliveryCustomersDto> list2 = serviceDeliveryExcelDto.getServiceDeliveryCustomers();
        if ( list2 != null ) {
            serviceDeliveryDto.setServiceDeliveryCustomers( new ArrayList<ServiceDeliveryCustomersDto>( list2 ) );
        }
        if ( serviceDeliveryExcelDto.getDiscountPercent() != null ) {
            serviceDeliveryDto.setDiscountPercent( BigDecimal.valueOf( serviceDeliveryExcelDto.getDiscountPercent() ) );
        }

        afterExcelToDto( serviceDeliveryExcelDto, serviceDeliveryDto );

        return serviceDeliveryDto;
    }

    protected Services selectResponseToServices(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        Services services = new Services();

        if ( selectResponse.getId() != null ) {
            services.setId( selectResponse.getId() );
        }

        return services;
    }

    protected SalesChannel selectResponseToSalesChannel(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        SalesChannel salesChannel = new SalesChannel();

        if ( selectResponse.getId() != null ) {
            salesChannel.setId( selectResponse.getId() );
        }

        return salesChannel;
    }

    protected List<SalesChannel> selectResponseListToSalesChannelList(List<SelectResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<SalesChannel> list1 = new ArrayList<SalesChannel>( list.size() );
        for ( SelectResponse selectResponse : list ) {
            list1.add( selectResponseToSalesChannel( selectResponse ) );
        }

        return list1;
    }

    protected CustomerSegment selectResponseToCustomerSegment(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        CustomerSegment customerSegment = new CustomerSegment();

        if ( selectResponse.getId() != null ) {
            customerSegment.setId( selectResponse.getId() );
        }

        return customerSegment;
    }

    protected List<CustomerSegment> selectResponseListToCustomerSegmentList(List<SelectResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<CustomerSegment> list1 = new ArrayList<CustomerSegment>( list.size() );
        for ( SelectResponse selectResponse : list ) {
            list1.add( selectResponseToCustomerSegment( selectResponse ) );
        }

        return list1;
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

    protected ServiceDeliveryCustomers serviceDeliveryCustomersDtoToServiceDeliveryCustomers(ServiceDeliveryCustomersDto serviceDeliveryCustomersDto) {
        if ( serviceDeliveryCustomersDto == null ) {
            return null;
        }

        ServiceDeliveryCustomers serviceDeliveryCustomers = new ServiceDeliveryCustomers();

        if ( serviceDeliveryCustomersDto.getId() != null ) {
            serviceDeliveryCustomers.setId( serviceDeliveryCustomersDto.getId() );
        }
        if ( serviceDeliveryCustomersDto.getCustomerId() != null ) {
            serviceDeliveryCustomers.setCustomerId( serviceDeliveryCustomersDto.getCustomerId() );
        }
        if ( serviceDeliveryCustomersDto.getServiceDelivery() != null ) {
            serviceDeliveryCustomers.setServiceDelivery( selectResponseToServiceDelivery( serviceDeliveryCustomersDto.getServiceDelivery() ) );
        }

        return serviceDeliveryCustomers;
    }

    protected List<ServiceDeliveryCustomers> serviceDeliveryCustomersDtoListToServiceDeliveryCustomersList(List<ServiceDeliveryCustomersDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ServiceDeliveryCustomers> list1 = new ArrayList<ServiceDeliveryCustomers>( list.size() );
        for ( ServiceDeliveryCustomersDto serviceDeliveryCustomersDto : list ) {
            list1.add( serviceDeliveryCustomersDtoToServiceDeliveryCustomers( serviceDeliveryCustomersDto ) );
        }

        return list1;
    }

    protected SelectResponse servicesToSelectResponse(Services services) {
        if ( services == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( services.getId() != null ) {
            selectResponse.setId( services.getId() );
        }

        return selectResponse;
    }

    protected List<SelectResponse> salesChannelListToSelectResponseList(List<SalesChannel> list) {
        if ( list == null ) {
            return null;
        }

        List<SelectResponse> list1 = new ArrayList<SelectResponse>( list.size() );
        for ( SalesChannel salesChannel : list ) {
            list1.add( salesChannelConverter.ModelToSelect( salesChannelConverter.fromModelToDto( salesChannel ) ) );
        }

        return list1;
    }

    protected SelectResponse customerSegmentToSelectResponse(CustomerSegment customerSegment) {
        if ( customerSegment == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( customerSegment.getId() != null ) {
            selectResponse.setId( customerSegment.getId() );
        }

        return selectResponse;
    }

    protected List<SelectResponse> customerSegmentListToSelectResponseList(List<CustomerSegment> list) {
        if ( list == null ) {
            return null;
        }

        List<SelectResponse> list1 = new ArrayList<SelectResponse>( list.size() );
        for ( CustomerSegment customerSegment : list ) {
            list1.add( customerSegmentToSelectResponse( customerSegment ) );
        }

        return list1;
    }

    protected SelectResponse serviceDeliveryToSelectResponse(ServiceDelivery serviceDelivery) {
        if ( serviceDelivery == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( serviceDelivery.getId() != null ) {
            selectResponse.setId( serviceDelivery.getId() );
        }

        return selectResponse;
    }

    protected ServiceDeliveryCustomersDto serviceDeliveryCustomersToServiceDeliveryCustomersDto(ServiceDeliveryCustomers serviceDeliveryCustomers) {
        if ( serviceDeliveryCustomers == null ) {
            return null;
        }

        ServiceDeliveryCustomersDto serviceDeliveryCustomersDto = new ServiceDeliveryCustomersDto();

        if ( serviceDeliveryCustomers.getId() != null ) {
            serviceDeliveryCustomersDto.setId( serviceDeliveryCustomers.getId() );
        }
        if ( serviceDeliveryCustomers.getCustomerId() != null ) {
            serviceDeliveryCustomersDto.setCustomerId( serviceDeliveryCustomers.getCustomerId() );
        }
        if ( serviceDeliveryCustomers.getServiceDelivery() != null ) {
            serviceDeliveryCustomersDto.setServiceDelivery( serviceDeliveryToSelectResponse( serviceDeliveryCustomers.getServiceDelivery() ) );
        }

        return serviceDeliveryCustomersDto;
    }

    protected List<ServiceDeliveryCustomersDto> serviceDeliveryCustomersListToServiceDeliveryCustomersDtoList(List<ServiceDeliveryCustomers> list) {
        if ( list == null ) {
            return null;
        }

        List<ServiceDeliveryCustomersDto> list1 = new ArrayList<ServiceDeliveryCustomersDto>( list.size() );
        for ( ServiceDeliveryCustomers serviceDeliveryCustomers : list ) {
            list1.add( serviceDeliveryCustomersToServiceDeliveryCustomersDto( serviceDeliveryCustomers ) );
        }

        return list1;
    }

    protected SelectResponse servicesToSelectResponse1(Services services) {
        if ( services == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( services.getId() != null ) {
            selectResponse.setId( services.getId() );
        }

        return selectResponse;
    }

    protected SelectResponse excelSelectResponseDtoToSelectResponse(ExcelSelectResponseDto excelSelectResponseDto) {
        if ( excelSelectResponseDto == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        return selectResponse;
    }

    protected List<SelectResponse> excelSelectResponseDtoListToSelectResponseList(List<ExcelSelectResponseDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SelectResponse> list1 = new ArrayList<SelectResponse>( list.size() );
        for ( ExcelSelectResponseDto excelSelectResponseDto : list ) {
            list1.add( excelSelectResponseDtoToSelectResponse( excelSelectResponseDto ) );
        }

        return list1;
    }
}
