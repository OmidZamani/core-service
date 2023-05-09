package com.boxi.crm.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SegmentCustomers;
import com.boxi.crm.payload.dto.SegmentCustomersDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class SegmentCustomersConverterImpl implements SegmentCustomersConverter {

    @Override
    public SegmentCustomers fromDtoToModel(SegmentCustomersDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        SegmentCustomers segmentCustomers = new SegmentCustomers();

        if ( Dto.getId() != null ) {
            segmentCustomers.setId( Dto.getId() );
        }
        if ( Dto.getSegment() != null ) {
            segmentCustomers.setSegment( selectResponseToCustomerSegment( Dto.getSegment() ) );
        }

        return segmentCustomers;
    }

    @Override
    public SegmentCustomersDto fromModelToDto(SegmentCustomers segment) {
        if ( segment == null ) {
            return null;
        }

        SegmentCustomersDto segmentCustomersDto = new SegmentCustomersDto();

        if ( segment.getId() != null ) {
            segmentCustomersDto.setId( segment.getId() );
        }
        if ( segment.getSegment() != null ) {
            segmentCustomersDto.setSegment( customerSegmentToSelectResponse( segment.getSegment() ) );
        }

        AfterfromModelToDto( segment, segmentCustomersDto );

        return segmentCustomersDto;
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
}
