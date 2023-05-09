package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.BagExceptions;
import com.boxi.transport.payload.dto.BagExceptionsDto;
import org.mapstruct.*;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface BagExceptionsConvertor {

    @Mapping(source = "dto.selectException.id", target = "exception.id")
    @Mapping(source = "dto.selectBag.id", target = "bag.id")
    BagExceptions fromDtoToModel(BagExceptionsDto dto);

    @Mapping(target = "selectException", ignore = true)
    @Mapping(target = "selectBag.id", source = "bag.id")
    BagExceptionsDto fromDtoToModel(BagExceptions bagExceptions);


    @AfterMapping
    default void afterfromDtoToModel(BagExceptionsDto dto, @MappingTarget BagExceptions bagExceptions) {
        if (bagExceptions.getException() != null)
            dto.setSelectException(new SelectResponse(bagExceptions.getException().getId(), bagExceptions.getException().getDescription()));
    }
}
