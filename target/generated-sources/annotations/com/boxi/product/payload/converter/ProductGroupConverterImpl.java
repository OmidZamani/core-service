package com.boxi.product.payload.converter;

import com.boxi.product.entity.ProductGroup;
import com.boxi.product.payload.dto.ProductGroupDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class ProductGroupConverterImpl implements ProductGroupConverter {

    @Override
    public ProductGroup fromDtoToModel(ProductGroupDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductGroup productGroup = new ProductGroup();

        if ( dto.getId() != null ) {
            productGroup.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            productGroup.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            productGroup.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getCode() != null ) {
            productGroup.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            productGroup.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            productGroup.setDescription( dto.getDescription() );
        }

        return productGroup;
    }

    @Override
    public void updateFromDto(ProductGroupDto dto, ProductGroup bag) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            bag.setId( dto.getId() );
        }
        else {
            bag.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            bag.setIsActive( dto.getIsActive() );
        }
        else {
            bag.setIsActive( null );
        }
        if ( dto.getIsDeleted() != null ) {
            bag.setIsDeleted( dto.getIsDeleted() );
        }
        else {
            bag.setIsDeleted( null );
        }
        if ( dto.getCode() != null ) {
            bag.setCode( dto.getCode() );
        }
        else {
            bag.setCode( null );
        }
        if ( dto.getName() != null ) {
            bag.setName( dto.getName() );
        }
        else {
            bag.setName( null );
        }
        if ( dto.getDescription() != null ) {
            bag.setDescription( dto.getDescription() );
        }
        else {
            bag.setDescription( null );
        }
    }

    @Override
    public ProductGroupDto fromModelToDto(ProductGroup Product) {
        if ( Product == null ) {
            return null;
        }

        ProductGroupDto productGroupDto = new ProductGroupDto();

        if ( Product.getId() != null ) {
            productGroupDto.setId( Product.getId() );
        }
        if ( Product.getCode() != null ) {
            productGroupDto.setCode( Product.getCode() );
        }
        if ( Product.getName() != null ) {
            productGroupDto.setName( Product.getName() );
        }
        if ( Product.getDescription() != null ) {
            productGroupDto.setDescription( Product.getDescription() );
        }
        if ( Product.getIsActive() != null ) {
            productGroupDto.setIsActive( Product.getIsActive() );
        }
        if ( Product.getIsDeleted() != null ) {
            productGroupDto.setIsDeleted( Product.getIsDeleted() );
        }

        return productGroupDto;
    }
}
