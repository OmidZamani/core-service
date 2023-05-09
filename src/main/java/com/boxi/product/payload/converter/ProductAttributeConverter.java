package com.boxi.product.payload.converter;


import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.payload.dto.ProductAttributeDto;
import com.boxi.product.payload.dto.UsingProductDto;
import com.boxi.product.repo.ProductAttributeDevisionRepository;
import com.boxi.product.repo.ProductAttributeRepository;
import com.boxi.product.repo.ProductRepository;
import com.boxi.product.repo.UsingProductRepository;
import com.boxi.product.response.ProductAttributeSelectDto;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ProductAttributeDevisionConverter.class})
@DecoratedWith(SelectConverterProductAttribute.class)
public interface ProductAttributeConverter {


    @Mapping(source = "dto.timeCommitment", target = "timeCommitment")
//    @Mapping(source = "dto.attributeDivition",target = "productAttributeDevisions")
    ProductAttribute fromDtoToModel(ProductAttributeDto dto);


    //    @Mapping(source = "dto.attributeDivition",target = "productAttributeDevisions")
    void updateFromDto(ProductAttributeDto dto, @MappingTarget ProductAttribute attribute);


    @Mapping(source = "product.name", target = "product.text")

//    @Mapping(source = "timeCommitment",target = "timeCommitment")
    ProductAttributeSelectDto fromModelToDtoSelect(ProductAttribute productAttribute);


    @Mapping(source = "product.name", target = "product.text")
    ProductAttributeDto fromModelToDto(ProductAttribute productAttribute);

    ProductAttributeSelectDto fromDtoSelect(ProductAttribute productAttribute);


    @Mapping(target = "attributeDivition", ignore = true)
    ProductAttributeSelectDto fromDtoToSelect(ProductAttributeDto productAttribute);


    ProductAttribute selectToProductAttribute(SelectResponse select);

    SelectResponse ProductAttributeToSelect(ProductAttribute p);

    @AfterMapping
    default void validate(ProductAttribute productAttribute, @MappingTarget ProductAttributeDto dto) {
        if (productAttribute.getTimeCommitment() != null) {
            dto.setTimeCommitment(new SelectResponse(productAttribute.getTimeCommitment().getId(), productAttribute.getTimeCommitment().getName()));
        }
        if (productAttribute.getUsingProducts() != null) {
            System.out.println("");
        }
    }

    @AfterMapping
    default void validateSelect(ProductAttribute productAttribute, @MappingTarget ProductAttributeSelectDto dto) {
        if (productAttribute.getTimeCommitment().getName() != null) {
            dto.setTimeCommitment(new SelectResponse(productAttribute.getTimeCommitment().getId(), productAttribute.getTimeCommitment().getName()));
        }
    }

    @AfterMapping
    default void after(ProductAttributeDto dto) {
        if (SelectUtil.NZ_CHECK(dto.getTimeCommitment())) {
            dto.setTimeCommitment(null);
        }
    }


}

abstract class SelectConverterProductAttribute implements ProductAttributeConverter {
    @Autowired
    private ProductAttributeRepository productAttributeRepository;


    @Autowired
    private ProductAttributeConverter productAttributeConverter;

    @Autowired
    private UsingProductConverter usingProductConverter;

    @Autowired
    private UsingProductRepository usingProductRepository;
    @Autowired
    private ProductAttributeDevisionRepository productAttributeDevisionRepository;
    @Autowired
    private ProductAttributeDevisionConverter productAttributeDevisionConverter;

    @Autowired
    private CountryDevisionService countryDevisionService;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductAttribute selectToProductAttribute(SelectResponse select) {

        if (SelectUtil.NZ_CHECK(select)) return null;
        return productAttributeRepository.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.Product, ExceptionType.ENTITY_NOT_FOUND);
        });
    }

    @Override
    public SelectResponse ProductAttributeToSelect(ProductAttribute p) {
        return new SelectResponse(p.getId(), p.selectToString());
    }

    @Override
    public ProductAttributeDto fromModelToDto(ProductAttribute productAttribute) {
        ProductAttributeDto productAttributeDto = productAttributeConverter.fromModelToDto(productAttribute);
        if (productAttribute.getUsingProducts() != null) {
            List<UsingProductDto> usingProductDtos = new ArrayList<>();
            for (UsingProduct usingProductDto : productAttribute.getUsingProducts()) {
                UsingProductDto usingProductDto1 = usingProductConverter.fromModelToDto(usingProductRepository.findById(usingProductDto.getId()).orElseThrow());
                usingProductDtos.add(usingProductDto1);
            }
            productAttributeDto.setUsingProduct(usingProductDtos);
        }

        return productAttributeDto;
    }
}
