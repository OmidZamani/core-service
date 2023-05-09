package com.boxi.product.payload.converter;

import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.payload.dto.ProductAttributeDto;
import com.boxi.product.response.ProductAttributeSelectDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-05T14:04:32+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class ProductAttributeConverterImpl extends SelectConverterProductAttribute {

    @Autowired
    @Qualifier("delegate")
    private ProductAttributeConverter delegate;

    @Override
    public ProductAttribute fromDtoToModel(ProductAttributeDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public void updateFromDto(ProductAttributeDto dto, ProductAttribute attribute)  {
        delegate.updateFromDto( dto, attribute );
    }

    @Override
    public ProductAttributeSelectDto fromModelToDtoSelect(ProductAttribute productAttribute)  {
        return delegate.fromModelToDtoSelect( productAttribute );
    }

    @Override
    public ProductAttributeSelectDto fromDtoSelect(ProductAttribute productAttribute)  {
        return delegate.fromDtoSelect( productAttribute );
    }

    @Override
    public ProductAttributeSelectDto fromDtoToSelect(ProductAttributeDto productAttribute)  {
        return delegate.fromDtoToSelect( productAttribute );
    }
}
