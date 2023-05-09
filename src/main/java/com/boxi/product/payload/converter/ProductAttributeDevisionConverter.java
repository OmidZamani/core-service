package com.boxi.product.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.ProductAttributeDevision;
import com.boxi.product.payload.dto.ProductAttributeDevisionDto;
import com.boxi.product.repo.ProductAttributeDevisionRepository;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS

)
public interface ProductAttributeDevisionConverter {

    @Mapping(source = "dto.productAttribute", target = "productAttribute")
//    @Mapping(source = "dto.fromCountryDevision", target = "fromCountryDevision")
//    @Mapping(source = "dto.toCountryDevision", target = "toCountryDevision")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(source = "dto.isDeleted", target = "isDeleted")
    @Mapping(ignore = true,target = "fromCountryDevision")
    @Mapping(ignore = true,target = "toCountryDevision")
    ProductAttributeDevision fromDtoToModel(ProductAttributeDevisionDto dto);

    @Mapping(ignore = true,target = "fromCountryDevision")
    @Mapping(ignore = true,target = "toCountryDevision")
    void updateFromDto(ProductAttributeDevisionDto dto, @MappingTarget ProductAttributeDevision productAttributeDevision);


    @Mapping(source = "productAttribute.id", target = "productAttribute.id")
    ProductAttributeDevisionDto fromModelToDto(ProductAttributeDevision productAttributeDevision);

    List<ProductAttributeDevisionDto> fromModelToDtoList(List<ProductAttributeDevision> productAttributeDevision);

    public SelectResponse ProductAttributeDevisionToSelect(ProductAttributeDevision PA);

    public ProductAttributeDevision selectToProductAttributeDevision(SelectResponse select);



}

abstract class ProductAttributeDevisionSelectConverter implements ProductAttributeDevisionConverter {

    @Autowired
    private ProductAttributeDevisionRepository productAttributeDevisionRepository;

    @Override
    public SelectResponse ProductAttributeDevisionToSelect(ProductAttributeDevision PA) {
        return new SelectResponse(PA.getId(), PA.selectToString());
    }

    @Override
    public ProductAttributeDevision selectToProductAttributeDevision(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return productAttributeDevisionRepository.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.ProductAttributeDevision, ExceptionType.ENTITY_NOT_FOUND);
        });
    }


}