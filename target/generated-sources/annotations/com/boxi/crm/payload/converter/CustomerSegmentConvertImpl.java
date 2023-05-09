package com.boxi.crm.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SegmentCustomers;
import com.boxi.crm.payload.dto.CustomerSegmentDto;
import com.boxi.crm.payload.dto.SegmentCustomersDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class CustomerSegmentConvertImpl implements CustomerSegmentConvert {

    @Override
    public CustomerSegment fromDtoToModel(CustomerSegmentDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        CustomerSegment customerSegment = new CustomerSegment();

        if ( Dto.getId() != null ) {
            customerSegment.setId( Dto.getId() );
        }
        if ( Dto.getIsActive() != null ) {
            customerSegment.setIsActive( Dto.getIsActive() );
        }
        if ( Dto.getIsDeleted() != null ) {
            customerSegment.setIsDeleted( Dto.getIsDeleted() );
        }
        if ( Dto.getCode() != null ) {
            customerSegment.setCode( Dto.getCode() );
        }
        if ( Dto.getName() != null ) {
            customerSegment.setName( Dto.getName() );
        }
        if ( Dto.getDescription() != null ) {
            customerSegment.setDescription( Dto.getDescription() );
        }
        List<SegmentCustomers> list = segmentCustomersDtoListToSegmentCustomersList( Dto.getSegmentCustomers() );
        if ( list != null ) {
            customerSegment.setSegmentCustomers( list );
        }

        return customerSegment;
    }

    @Override
    public CustomerSegmentDto fromModelToDto(CustomerSegment segment) {
        if ( segment == null ) {
            return null;
        }

        CustomerSegmentDto customerSegmentDto = new CustomerSegmentDto();

        if ( segment.getId() != null ) {
            customerSegmentDto.setId( segment.getId() );
        }
        if ( segment.getIsActive() != null ) {
            customerSegmentDto.setIsActive( segment.getIsActive() );
        }
        if ( segment.getIsDeleted() != null ) {
            customerSegmentDto.setIsDeleted( segment.getIsDeleted() );
        }
        if ( segment.getCode() != null ) {
            customerSegmentDto.setCode( segment.getCode() );
        }
        if ( segment.getName() != null ) {
            customerSegmentDto.setName( segment.getName() );
        }
        if ( segment.getDescription() != null ) {
            customerSegmentDto.setDescription( segment.getDescription() );
        }

        AfterfromModelToDto( segment, customerSegmentDto );

        return customerSegmentDto;
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

    protected SegmentCustomers segmentCustomersDtoToSegmentCustomers(SegmentCustomersDto segmentCustomersDto) {
        if ( segmentCustomersDto == null ) {
            return null;
        }

        SegmentCustomers segmentCustomers = new SegmentCustomers();

        if ( segmentCustomersDto.getId() != null ) {
            segmentCustomers.setId( segmentCustomersDto.getId() );
        }
        if ( segmentCustomersDto.getSegment() != null ) {
            segmentCustomers.setSegment( selectResponseToCustomerSegment( segmentCustomersDto.getSegment() ) );
        }

        return segmentCustomers;
    }

    protected List<SegmentCustomers> segmentCustomersDtoListToSegmentCustomersList(List<SegmentCustomersDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SegmentCustomers> list1 = new ArrayList<SegmentCustomers>( list.size() );
        for ( SegmentCustomersDto segmentCustomersDto : list ) {
            list1.add( segmentCustomersDtoToSegmentCustomers( segmentCustomersDto ) );
        }

        return list1;
    }
}
