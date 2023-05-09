package com.boxi.product.payload.converter;

import com.boxi.product.entity.Product;
import com.boxi.product.payload.dto.ProductDto;
import com.boxi.product.payload.dto.ProductExcelDto;
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
public class ProductConverterImpl extends SelectConverter {

    @Autowired
    @Qualifier("delegate")
    private ProductConverter delegate;

    @Override
    public Product fromDtoToModel(ProductDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public void updateFromDto(ProductDto dto, Product bag)  {
        delegate.updateFromDto( dto, bag );
    }

    @Override
    public ProductDto fromModelToDto(Product Product)  {
        return delegate.fromModelToDto( Product );
    }

    @Override
    public ProductDto fromExcelToDto(ProductExcelDto excel)  {
        return delegate.fromExcelToDto( excel );
    }
}
