package com.boxi.product.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.UsingProduct;
import com.boxi.product.payload.dto.UsingProductDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class UsingProductConverterImpl implements UsingProductConverter {

    @Override
    public UsingProduct fromDtoToModel(UsingProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        UsingProduct usingProduct = new UsingProduct();

        if ( dto.getId() != null ) {
            usingProduct.setId( dto.getId() );
        }
        if ( dto.getChild() != null ) {
            usingProduct.setChild( selectResponseToProduct( dto.getChild() ) );
        }
        if ( dto.getParent() != null ) {
            usingProduct.setParent( selectResponseToProduct( dto.getParent() ) );
        }
        if ( dto.getProductAttribute() != null ) {
            usingProduct.setProductAttribute( selectResponseToProductAttribute( dto.getProductAttribute() ) );
        }

        return usingProduct;
    }

    @Override
    public void updateFromDto(UsingProductDto dto, UsingProduct usingProduct) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            usingProduct.setId( dto.getId() );
        }
        else {
            usingProduct.setId( null );
        }
        if ( dto.getChild() != null ) {
            if ( usingProduct.getChild() == null ) {
                usingProduct.setChild( new Product() );
            }
            selectResponseToProduct1( dto.getChild(), usingProduct.getChild() );
        }
        else {
            usingProduct.setChild( null );
        }
        if ( dto.getParent() != null ) {
            if ( usingProduct.getParent() == null ) {
                usingProduct.setParent( new Product() );
            }
            selectResponseToProduct1( dto.getParent(), usingProduct.getParent() );
        }
        else {
            usingProduct.setParent( null );
        }
        if ( dto.getProductAttribute() != null ) {
            if ( usingProduct.getProductAttribute() == null ) {
                usingProduct.setProductAttribute( new ProductAttribute() );
            }
            selectResponseToProductAttribute1( dto.getProductAttribute(), usingProduct.getProductAttribute() );
        }
        else {
            usingProduct.setProductAttribute( null );
        }
    }

    @Override
    public UsingProductDto fromModelToDto(UsingProduct usingProduct) {
        if ( usingProduct == null ) {
            return null;
        }

        UsingProductDto usingProductDto = new UsingProductDto();

        if ( usingProduct.getId() != null ) {
            usingProductDto.setId( usingProduct.getId() );
        }
        if ( usingProduct.getParent() != null ) {
            usingProductDto.setParent( productToSelectResponse( usingProduct.getParent() ) );
        }
        if ( usingProduct.getChild() != null ) {
            usingProductDto.setChild( productToSelectResponse( usingProduct.getChild() ) );
        }
        if ( usingProduct.getProductAttribute() != null ) {
            usingProductDto.setProductAttribute( productAttributeToSelectResponse( usingProduct.getProductAttribute() ) );
        }

        return usingProductDto;
    }

    protected Product selectResponseToProduct(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        Product product = new Product();

        if ( selectResponse.getId() != null ) {
            product.setId( selectResponse.getId() );
        }

        return product;
    }

    protected ProductAttribute selectResponseToProductAttribute(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        ProductAttribute productAttribute = new ProductAttribute();

        if ( selectResponse.getId() != null ) {
            productAttribute.setId( selectResponse.getId() );
        }

        return productAttribute;
    }

    protected void selectResponseToProduct1(SelectResponse selectResponse, Product mappingTarget) {
        if ( selectResponse == null ) {
            return;
        }

        if ( selectResponse.getId() != null ) {
            mappingTarget.setId( selectResponse.getId() );
        }
        else {
            mappingTarget.setId( null );
        }
    }

    protected void selectResponseToProductAttribute1(SelectResponse selectResponse, ProductAttribute mappingTarget) {
        if ( selectResponse == null ) {
            return;
        }

        if ( selectResponse.getId() != null ) {
            mappingTarget.setId( selectResponse.getId() );
        }
        else {
            mappingTarget.setId( null );
        }
    }

    protected SelectResponse productToSelectResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( product.getId() != null ) {
            selectResponse.setId( product.getId() );
        }

        return selectResponse;
    }

    protected SelectResponse productAttributeToSelectResponse(ProductAttribute productAttribute) {
        if ( productAttribute == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( productAttribute.getId() != null ) {
            selectResponse.setId( productAttribute.getId() );
        }

        return selectResponse;
    }
}
