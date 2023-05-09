package com.boxi.crm.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.SalesChannel;
import com.boxi.crm.payload.dto.SalesChannelDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class SalesChannelConverterImpl implements SalesChannelConverter {

    @Override
    public SalesChannel fromDtoToModel(SalesChannelDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        SalesChannel salesChannel = new SalesChannel();

        if ( Dto.getId() != null ) {
            salesChannel.setId( Dto.getId() );
        }
        if ( Dto.getIsActive() != null ) {
            salesChannel.setIsActive( Dto.getIsActive() );
        }
        if ( Dto.getIsDeleted() != null ) {
            salesChannel.setIsDeleted( Dto.getIsDeleted() );
        }
        if ( Dto.getCode() != null ) {
            salesChannel.setCode( Dto.getCode() );
        }
        if ( Dto.getName() != null ) {
            salesChannel.setName( Dto.getName() );
        }
        if ( Dto.getDescription() != null ) {
            salesChannel.setDescription( Dto.getDescription() );
        }

        return salesChannel;
    }

    @Override
    public SalesChannelDto fromModelToDto(SalesChannel segment) {
        if ( segment == null ) {
            return null;
        }

        SalesChannelDto salesChannelDto = new SalesChannelDto();

        if ( segment.getId() != null ) {
            salesChannelDto.setId( segment.getId() );
        }
        if ( segment.getIsDeleted() != null ) {
            salesChannelDto.setIsDeleted( segment.getIsDeleted() );
        }
        if ( segment.getIsActive() != null ) {
            salesChannelDto.setIsActive( segment.getIsActive() );
        }
        if ( segment.getCode() != null ) {
            salesChannelDto.setCode( segment.getCode() );
        }
        if ( segment.getName() != null ) {
            salesChannelDto.setName( segment.getName() );
        }
        if ( segment.getDescription() != null ) {
            salesChannelDto.setDescription( segment.getDescription() );
        }

        return salesChannelDto;
    }

    @Override
    public SelectResponse ModelToSelect(SalesChannelDto salesChannelDto) {
        if ( salesChannelDto == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( salesChannelDto.getId() != null ) {
            selectResponse.setId( salesChannelDto.getId() );
        }

        afterModelToSelect( salesChannelDto, selectResponse );

        return selectResponse;
    }
}
