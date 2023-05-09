package com.boxi.product.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.Product;
import com.boxi.product.payload.dto.ProductDto;
import com.boxi.product.payload.dto.ProductExcelDto;
import com.boxi.product.repo.ProductRepository;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SelectConverter.class)
public interface ProductConverter {
    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.code", target = "code")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(source = "dto.isDeleted", target = "isDeleted")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "dto.productGroup", target = "productGroup")
    Product fromDtoToModel(ProductDto dto);

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.code", target = "code")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "dto.isDeleted", target = "isDeleted")
    @Mapping(source = "dto.productGroup", target = "productGroup")
    void updateFromDto(ProductDto dto, @MappingTarget Product bag);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "isActive", target = "isActive")
    @Mapping(source = "isDeleted", target = "isDeleted")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "productGroup", target = "productGroup")
    ProductDto fromModelToDto(Product Product);

    SelectResponse productGroupToSelect(Product product);

    Product selectToproductGroup(SelectResponse select);


    @Mapping(target = "productGroup", ignore = true)
    ProductDto fromExcelToDto(ProductExcelDto excel);

    @AfterMapping
    default void validate(Product product, @MappingTarget ProductDto dto) {
        if (product.getProductGroup() != null) {
            dto.setProductGroup(new SelectResponse(product.getProductGroup().getId(), product.getProductGroup().selectToString()));
        }
    }

    @AfterMapping
    default void after(ProductDto dto) {
        if (SelectUtil.NZ_CHECK(dto.getProductGroup())) {
            dto.setProductGroup(null);
        }
    }


}

abstract class SelectConverter implements ProductConverter {

    @Autowired
    private ProductRepository hubRepository;

    @Override
    public SelectResponse productGroupToSelect(Product p) {
        return new SelectResponse(p.getId(), p.selectToString());
    }

    @Override
    public Product selectToproductGroup(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return hubRepository.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.Product, ExceptionType.ENTITY_NOT_FOUND);
        });
    }


}
