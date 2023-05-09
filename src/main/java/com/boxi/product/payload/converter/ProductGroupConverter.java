package com.boxi.product.payload.converter;

import com.boxi.product.entity.ProductGroup;
import com.boxi.product.payload.dto.ProductGroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductGroupConverter {

    ProductGroup fromDtoToModel(ProductGroupDto dto);


    void updateFromDto(ProductGroupDto dto, @MappingTarget ProductGroup bag);


    ProductGroupDto fromModelToDto(ProductGroup Product);

}

