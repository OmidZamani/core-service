package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.TypesOfPackage;
import com.boxi.PriceList.payload.dto.TypesOfPackageDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TypesOfPackageConverter {
    TypesOfPackageDto fromModelToDto(TypesOfPackage typesOfPackage);

    TypesOfPackage fromDtoToModel(TypesOfPackageDto dto);
}
