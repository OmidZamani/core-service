package com.boxi.product.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductGroup;
import com.boxi.product.payload.dto.ProductDto;
import com.boxi.product.payload.dto.ProductExcelDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-05T14:04:32+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class ProductConverterImpl_ implements ProductConverter {

    @Override
    public Product fromDtoToModel(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        if ( dto.getId() != null ) {
            product.setId( dto.getId() );
        }
        if ( dto.getCode() != null ) {
            product.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            product.setName( dto.getName() );
        }
        if ( dto.getIsActive() != null ) {
            product.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            product.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getDescription() != null ) {
            product.setDescription( dto.getDescription() );
        }
        if ( dto.getProductGroup() != null ) {
            product.setProductGroup( selectResponseToProductGroup( dto.getProductGroup() ) );
        }

        after( dto );

        return product;
    }

    @Override
    public void updateFromDto(ProductDto dto, Product bag) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            bag.setId( dto.getId() );
        }
        else {
            bag.setId( null );
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
        if ( dto.getIsDeleted() != null ) {
            bag.setIsDeleted( dto.getIsDeleted() );
        }
        else {
            bag.setIsDeleted( null );
        }
        if ( dto.getProductGroup() != null ) {
            if ( bag.getProductGroup() == null ) {
                bag.setProductGroup( new ProductGroup() );
            }
            selectResponseToProductGroup1( dto.getProductGroup(), bag.getProductGroup() );
        }
        else {
            bag.setProductGroup( null );
        }
        if ( dto.getIsActive() != null ) {
            bag.setIsActive( dto.getIsActive() );
        }
        else {
            bag.setIsActive( null );
        }

        after( dto );
    }

    @Override
    public ProductDto fromModelToDto(Product Product) {
        if ( Product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( Product.getId() != null ) {
            productDto.setId( Product.getId() );
        }
        if ( Product.getCode() != null ) {
            productDto.setCode( Product.getCode() );
        }
        if ( Product.getName() != null ) {
            productDto.setName( Product.getName() );
        }
        if ( Product.getIsActive() != null ) {
            productDto.setIsActive( Product.getIsActive() );
        }
        if ( Product.getIsDeleted() != null ) {
            productDto.setIsDeleted( Product.getIsDeleted() );
        }
        if ( Product.getDescription() != null ) {
            productDto.setDescription( Product.getDescription() );
        }
        if ( Product.getProductGroup() != null ) {
            productDto.setProductGroup( productGroupToSelectResponse( Product.getProductGroup() ) );
        }

        validate( Product, productDto );

        return productDto;
    }

    @Override
    public SelectResponse productGroupToSelect(Product product) {
        if ( product == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( product.getId() != null ) {
            selectResponse.setId( product.getId() );
        }

        return selectResponse;
    }

    @Override
    public Product selectToproductGroup(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        Product product = new Product();

        if ( select.getId() != null ) {
            product.setId( select.getId() );
        }

        return product;
    }

    @Override
    public ProductDto fromExcelToDto(ProductExcelDto excel) {
        if ( excel == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        if ( excel.getCode() != null ) {
            productDto.setCode( excel.getCode() );
        }
        if ( excel.getName() != null ) {
            productDto.setName( excel.getName() );
        }
        if ( excel.getIsActive() != null ) {
            productDto.setIsActive( excel.getIsActive() );
        }

        return productDto;
    }

    protected ProductGroup selectResponseToProductGroup(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        ProductGroup productGroup = new ProductGroup();

        if ( selectResponse.getId() != null ) {
            productGroup.setId( selectResponse.getId() );
        }

        return productGroup;
    }

    protected void selectResponseToProductGroup1(SelectResponse selectResponse, ProductGroup mappingTarget) {
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

    protected SelectResponse productGroupToSelectResponse(ProductGroup productGroup) {
        if ( productGroup == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( productGroup.getId() != null ) {
            selectResponse.setId( productGroup.getId() );
        }

        return selectResponse;
    }
}
