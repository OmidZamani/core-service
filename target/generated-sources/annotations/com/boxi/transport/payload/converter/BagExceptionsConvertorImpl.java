package com.boxi.transport.payload.converter;

import com.boxi.PriceList.entity.Exception;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Bag;
import com.boxi.transport.entity.BagExceptions;
import com.boxi.transport.payload.dto.BagExceptionsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class BagExceptionsConvertorImpl implements BagExceptionsConvertor {

    @Override
    public BagExceptions fromDtoToModel(BagExceptionsDto dto) {
        if ( dto == null ) {
            return null;
        }

        BagExceptions bagExceptions = new BagExceptions();

        if ( dto.getSelectException() != null ) {
            bagExceptions.setException( selectResponseToException( dto.getSelectException() ) );
        }
        if ( dto.getSelectBag() != null ) {
            bagExceptions.setBag( selectResponseToBag( dto.getSelectBag() ) );
        }
        if ( dto.getId() != null ) {
            bagExceptions.setId( dto.getId() );
        }
        if ( dto.getDescription() != null ) {
            bagExceptions.setDescription( dto.getDescription() );
        }

        afterfromDtoToModel( dto, bagExceptions );

        return bagExceptions;
    }

    @Override
    public BagExceptionsDto fromDtoToModel(BagExceptions bagExceptions) {
        if ( bagExceptions == null ) {
            return null;
        }

        BagExceptionsDto bagExceptionsDto = new BagExceptionsDto();

        if ( bagExceptions.getBag() != null ) {
            bagExceptionsDto.setSelectBag( bagToSelectResponse( bagExceptions.getBag() ) );
        }
        if ( bagExceptions.getId() != null ) {
            bagExceptionsDto.setId( bagExceptions.getId() );
        }
        if ( bagExceptions.getDescription() != null ) {
            bagExceptionsDto.setDescription( bagExceptions.getDescription() );
        }

        return bagExceptionsDto;
    }

    protected Exception selectResponseToException(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        Exception exception = new Exception();

        if ( selectResponse.getId() != null ) {
            exception.setId( selectResponse.getId() );
        }

        return exception;
    }

    protected Bag selectResponseToBag(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        Bag bag = new Bag();

        if ( selectResponse.getId() != null ) {
            bag.setId( selectResponse.getId() );
        }

        return bag;
    }

    protected SelectResponse bagToSelectResponse(Bag bag) {
        if ( bag == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( bag.getId() != null ) {
            selectResponse.setId( bag.getId() );
        }

        return selectResponse;
    }
}
