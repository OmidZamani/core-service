package com.boxi.PriceList.payload.converter;


import com.boxi.PriceList.Enum.ExceptionType;
import com.boxi.PriceList.entity.Exception;
import com.boxi.PriceList.payload.dto.ExceptionDto;

import com.boxi.PriceList.payload.dto.ExceptionExcelDto;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import com.boxi.product.entity.ProductAttributeDevision;
import com.boxi.product.payload.dto.ProductAttributeDevisionDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ExceptionConverter {

    @Mapping(target = "type", ignore = true)
    Exception fromDtoToModel(ExceptionDto dto);

    @Mapping(target = "type", ignore = true)
    ExceptionDto fromModelToDto(Exception exception);

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    ExceptionDto fromExcelToDto(ExceptionExcelDto exceptionExcelDto);

    @AfterMapping
    default void afterExcelToDto(ExceptionExcelDto exception, @MappingTarget ExceptionDto dto) {
        if (exception.getType() != null)
            dto.setType(ExceptionType.findByFa(exception.getType()));
        if (exception.getIsActive() != null) {
            dto.setIsActive(exception.getIsActive());

        }
    }
    @AfterMapping
    default void validate(ExceptionDto dto, @MappingTarget Exception exception) {
        if (dto.getType() != null)
            exception.setType(dto.getType().getValue());
    }

    @AfterMapping
    default void validateDto(Exception exception, @MappingTarget ExceptionDto dto) {
        if (exception.getType() != null)
            dto.setType(ExceptionType.findByValue(exception.getType()));
    }

    @AfterMapping
    default void validateExcelToDto(ExceptionExcelDto exception, @MappingTarget ExceptionDto dto) {
        if (exception.getType() != null)
            dto.setType(ExceptionType.findByFa(exception.getType()));
        if (exception.getIsActive() != null) {
            dto.setIsActive(exception.getIsActive());

        }
    }


}
