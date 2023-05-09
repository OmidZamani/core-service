package com.boxi.product.payload.converter;

import com.boxi.product.entity.UsingProduct;
import com.boxi.product.payload.dto.UsingProductDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UsingProductConverter {

    @Mapping(source = "dto.id", target = "id")
//    @Mapping(source = "dto.parent.id", target = "parent")
//    @Mapping(source = "dto.child.id", target = "child")
    UsingProduct fromDtoToModel(UsingProductDto dto);

    @Mapping(source = "dto.id", target = "id")
//    @Mapping(source = "dto.parent.id", target = "parent")
//    @Mapping(source = "dto.child.id", target = "child")
    void updateFromDto(UsingProductDto dto, @MappingTarget UsingProduct usingProduct);

    @InheritInverseConfiguration(name = "fromDtoToModel")
    UsingProductDto fromModelToDto(UsingProduct usingProduct);
}
