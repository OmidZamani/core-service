package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.Exception;
import com.boxi.PriceList.payload.dto.ExceptionDto;
import com.boxi.PriceList.payload.dto.ExceptionExcelDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-05T14:04:32+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class ExceptionConverterImpl implements ExceptionConverter {

    @Override
    public Exception fromDtoToModel(ExceptionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Exception exception = new Exception();

        if ( dto.getId() != null ) {
            exception.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            exception.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            exception.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getCode() != null ) {
            exception.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            exception.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            exception.setDescription( dto.getDescription() );
        }

        validate( dto, exception );

        return exception;
    }

    @Override
    public ExceptionDto fromModelToDto(Exception exception) {
        if ( exception == null ) {
            return null;
        }

        ExceptionDto exceptionDto = new ExceptionDto();

        if ( exception.getId() != null ) {
            exceptionDto.setId( exception.getId() );
        }
        if ( exception.getIsActive() != null ) {
            exceptionDto.setIsActive( exception.getIsActive() );
        }
        if ( exception.getIsDeleted() != null ) {
            exceptionDto.setIsDeleted( exception.getIsDeleted() );
        }
        if ( exception.getCode() != null ) {
            exceptionDto.setCode( exception.getCode() );
        }
        if ( exception.getName() != null ) {
            exceptionDto.setName( exception.getName() );
        }
        if ( exception.getDescription() != null ) {
            exceptionDto.setDescription( exception.getDescription() );
        }

        validateDto( exception, exceptionDto );

        return exceptionDto;
    }

    @Override
    public ExceptionDto fromExcelToDto(ExceptionExcelDto exceptionExcelDto) {
        if ( exceptionExcelDto == null ) {
            return null;
        }

        ExceptionDto exceptionDto = new ExceptionDto();

        if ( exceptionExcelDto.getCode() != null ) {
            exceptionDto.setCode( exceptionExcelDto.getCode() );
        }
        if ( exceptionExcelDto.getName() != null ) {
            exceptionDto.setName( exceptionExcelDto.getName() );
        }
        if ( exceptionExcelDto.getDescription() != null ) {
            exceptionDto.setDescription( exceptionExcelDto.getDescription() );
        }

        afterExcelToDto( exceptionExcelDto, exceptionDto );
        validateExcelToDto( exceptionExcelDto, exceptionDto );

        return exceptionDto;
    }
}
