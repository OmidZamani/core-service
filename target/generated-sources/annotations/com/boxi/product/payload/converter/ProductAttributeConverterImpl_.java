package com.boxi.product.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.TimeCommitment;
import com.boxi.product.payload.dto.ProductAttributeDto;
import com.boxi.product.payload.dto.UsingProductDto;
import com.boxi.product.response.ProductAttributeSelectDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class ProductAttributeConverterImpl_ implements ProductAttributeConverter {

    @Override
    public ProductAttribute fromDtoToModel(ProductAttributeDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductAttribute productAttribute = new ProductAttribute();

        if ( dto.getTimeCommitment() != null ) {
            productAttribute.setTimeCommitment( selectResponseToTimeCommitment( dto.getTimeCommitment() ) );
        }
        if ( dto.getId() != null ) {
            productAttribute.setId( dto.getId() );
        }
        if ( dto.getFromWeight() != null ) {
            productAttribute.setFromWeight( dto.getFromWeight() );
        }
        if ( dto.getToWeight() != null ) {
            productAttribute.setToWeight( dto.getToWeight() );
        }
        if ( dto.getFromDim() != null ) {
            productAttribute.setFromDim( dto.getFromDim() );
        }
        if ( dto.getToDimension() != null ) {
            productAttribute.setToDimension( dto.getToDimension() );
        }
        if ( dto.getFromValue() != null ) {
            productAttribute.setFromValue( BigDecimal.valueOf( dto.getFromValue() ) );
        }
        if ( dto.getToValue() != null ) {
            productAttribute.setToValue( BigDecimal.valueOf( dto.getToValue() ) );
        }
        if ( dto.getProduct() != null ) {
            productAttribute.setProduct( selectResponseToProduct( dto.getProduct() ) );
        }

        after( dto );

        return productAttribute;
    }

    @Override
    public void updateFromDto(ProductAttributeDto dto, ProductAttribute attribute) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            attribute.setId( dto.getId() );
        }
        else {
            attribute.setId( null );
        }
        if ( dto.getFromWeight() != null ) {
            attribute.setFromWeight( dto.getFromWeight() );
        }
        else {
            attribute.setFromWeight( null );
        }
        if ( dto.getToWeight() != null ) {
            attribute.setToWeight( dto.getToWeight() );
        }
        else {
            attribute.setToWeight( null );
        }
        if ( dto.getFromDim() != null ) {
            attribute.setFromDim( dto.getFromDim() );
        }
        else {
            attribute.setFromDim( null );
        }
        if ( dto.getToDimension() != null ) {
            attribute.setToDimension( dto.getToDimension() );
        }
        else {
            attribute.setToDimension( null );
        }
        if ( dto.getFromValue() != null ) {
            attribute.setFromValue( BigDecimal.valueOf( dto.getFromValue() ) );
        }
        else {
            attribute.setFromValue( null );
        }
        if ( dto.getToValue() != null ) {
            attribute.setToValue( BigDecimal.valueOf( dto.getToValue() ) );
        }
        else {
            attribute.setToValue( null );
        }
        if ( dto.getProduct() != null ) {
            if ( attribute.getProduct() == null ) {
                attribute.setProduct( new Product() );
            }
            selectResponseToProduct1( dto.getProduct(), attribute.getProduct() );
        }
        else {
            attribute.setProduct( null );
        }
        if ( dto.getTimeCommitment() != null ) {
            if ( attribute.getTimeCommitment() == null ) {
                attribute.setTimeCommitment( new TimeCommitment() );
            }
            selectResponseToTimeCommitment1( dto.getTimeCommitment(), attribute.getTimeCommitment() );
        }
        else {
            attribute.setTimeCommitment( null );
        }

        after( dto );
    }

    @Override
    public ProductAttributeSelectDto fromModelToDtoSelect(ProductAttribute productAttribute) {
        if ( productAttribute == null ) {
            return null;
        }

        ProductAttributeSelectDto productAttributeSelectDto = new ProductAttributeSelectDto();

        if ( productAttribute.getProduct() != null ) {
            productAttributeSelectDto.setProduct( productToSelectResponse( productAttribute.getProduct() ) );
        }
        if ( productAttribute.getId() != null ) {
            productAttributeSelectDto.setId( productAttribute.getId() );
        }
        if ( productAttribute.getFromWeight() != null ) {
            productAttributeSelectDto.setFromWeight( productAttribute.getFromWeight() );
        }
        if ( productAttribute.getToWeight() != null ) {
            productAttributeSelectDto.setToWeight( productAttribute.getToWeight() );
        }
        if ( productAttribute.getFromDim() != null ) {
            productAttributeSelectDto.setFromDim( productAttribute.getFromDim() );
        }
        if ( productAttribute.getToDimension() != null ) {
            productAttributeSelectDto.setToDimension( productAttribute.getToDimension() );
        }
        if ( productAttribute.getFromValue() != null ) {
            productAttributeSelectDto.setFromValue( productAttribute.getFromValue().longValue() );
        }
        if ( productAttribute.getToValue() != null ) {
            productAttributeSelectDto.setToValue( productAttribute.getToValue().longValue() );
        }
        if ( productAttribute.getTimeCommitment() != null ) {
            productAttributeSelectDto.setTimeCommitment( timeCommitmentToSelectResponse( productAttribute.getTimeCommitment() ) );
        }

        validateSelect( productAttribute, productAttributeSelectDto );

        return productAttributeSelectDto;
    }

    @Override
    public ProductAttributeDto fromModelToDto(ProductAttribute productAttribute) {
        if ( productAttribute == null ) {
            return null;
        }

        ProductAttributeDto productAttributeDto = new ProductAttributeDto();

        if ( productAttribute.getProduct() != null ) {
            productAttributeDto.setProduct( productToSelectResponse1( productAttribute.getProduct() ) );
        }
        if ( productAttribute.getId() != null ) {
            productAttributeDto.setId( productAttribute.getId() );
        }
        if ( productAttribute.getFromWeight() != null ) {
            productAttributeDto.setFromWeight( productAttribute.getFromWeight() );
        }
        if ( productAttribute.getToWeight() != null ) {
            productAttributeDto.setToWeight( productAttribute.getToWeight() );
        }
        if ( productAttribute.getFromDim() != null ) {
            productAttributeDto.setFromDim( productAttribute.getFromDim() );
        }
        if ( productAttribute.getToDimension() != null ) {
            productAttributeDto.setToDimension( productAttribute.getToDimension() );
        }
        if ( productAttribute.getFromValue() != null ) {
            productAttributeDto.setFromValue( productAttribute.getFromValue().longValue() );
        }
        if ( productAttribute.getToValue() != null ) {
            productAttributeDto.setToValue( productAttribute.getToValue().longValue() );
        }
        if ( productAttribute.getTimeCommitment() != null ) {
            productAttributeDto.setTimeCommitment( timeCommitmentToSelectResponse( productAttribute.getTimeCommitment() ) );
        }

        validate( productAttribute, productAttributeDto );

        return productAttributeDto;
    }

    @Override
    public ProductAttributeSelectDto fromDtoSelect(ProductAttribute productAttribute) {
        if ( productAttribute == null ) {
            return null;
        }

        ProductAttributeSelectDto productAttributeSelectDto = new ProductAttributeSelectDto();

        if ( productAttribute.getId() != null ) {
            productAttributeSelectDto.setId( productAttribute.getId() );
        }
        if ( productAttribute.getFromWeight() != null ) {
            productAttributeSelectDto.setFromWeight( productAttribute.getFromWeight() );
        }
        if ( productAttribute.getToWeight() != null ) {
            productAttributeSelectDto.setToWeight( productAttribute.getToWeight() );
        }
        if ( productAttribute.getFromDim() != null ) {
            productAttributeSelectDto.setFromDim( productAttribute.getFromDim() );
        }
        if ( productAttribute.getToDimension() != null ) {
            productAttributeSelectDto.setToDimension( productAttribute.getToDimension() );
        }
        if ( productAttribute.getFromValue() != null ) {
            productAttributeSelectDto.setFromValue( productAttribute.getFromValue().longValue() );
        }
        if ( productAttribute.getToValue() != null ) {
            productAttributeSelectDto.setToValue( productAttribute.getToValue().longValue() );
        }
        if ( productAttribute.getProduct() != null ) {
            productAttributeSelectDto.setProduct( productToSelectResponse2( productAttribute.getProduct() ) );
        }
        if ( productAttribute.getTimeCommitment() != null ) {
            productAttributeSelectDto.setTimeCommitment( timeCommitmentToSelectResponse( productAttribute.getTimeCommitment() ) );
        }

        validateSelect( productAttribute, productAttributeSelectDto );

        return productAttributeSelectDto;
    }

    @Override
    public ProductAttributeSelectDto fromDtoToSelect(ProductAttributeDto productAttribute) {
        if ( productAttribute == null ) {
            return null;
        }

        ProductAttributeSelectDto productAttributeSelectDto = new ProductAttributeSelectDto();

        if ( productAttribute.getId() != null ) {
            productAttributeSelectDto.setId( productAttribute.getId() );
        }
        if ( productAttribute.getFromWeight() != null ) {
            productAttributeSelectDto.setFromWeight( productAttribute.getFromWeight() );
        }
        if ( productAttribute.getToWeight() != null ) {
            productAttributeSelectDto.setToWeight( productAttribute.getToWeight() );
        }
        if ( productAttribute.getFromDim() != null ) {
            productAttributeSelectDto.setFromDim( productAttribute.getFromDim() );
        }
        if ( productAttribute.getToDimension() != null ) {
            productAttributeSelectDto.setToDimension( productAttribute.getToDimension() );
        }
        if ( productAttribute.getFromValue() != null ) {
            productAttributeSelectDto.setFromValue( productAttribute.getFromValue() );
        }
        if ( productAttribute.getToValue() != null ) {
            productAttributeSelectDto.setToValue( productAttribute.getToValue() );
        }
        if ( productAttribute.getProduct() != null ) {
            productAttributeSelectDto.setProduct( productAttribute.getProduct() );
        }
        List<SelectResponse> list = usingProductDtoListToSelectResponseList( productAttribute.getUsingProduct() );
        if ( list != null ) {
            productAttributeSelectDto.setUsingProduct( list );
        }
        if ( productAttribute.getTimeCommitment() != null ) {
            productAttributeSelectDto.setTimeCommitment( productAttribute.getTimeCommitment() );
        }

        after( productAttribute );

        return productAttributeSelectDto;
    }

    @Override
    public ProductAttribute selectToProductAttribute(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        ProductAttribute productAttribute = new ProductAttribute();

        if ( select.getId() != null ) {
            productAttribute.setId( select.getId() );
        }

        return productAttribute;
    }

    @Override
    public SelectResponse ProductAttributeToSelect(ProductAttribute p) {
        if ( p == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( p.getId() != null ) {
            selectResponse.setId( p.getId() );
        }

        return selectResponse;
    }

    protected TimeCommitment selectResponseToTimeCommitment(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        TimeCommitment timeCommitment = new TimeCommitment();

        if ( selectResponse.getId() != null ) {
            timeCommitment.setId( selectResponse.getId() );
        }

        return timeCommitment;
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

    protected void selectResponseToTimeCommitment1(SelectResponse selectResponse, TimeCommitment mappingTarget) {
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

        if ( product.getName() != null ) {
            selectResponse.setText( product.getName() );
        }
        if ( product.getId() != null ) {
            selectResponse.setId( product.getId() );
        }

        return selectResponse;
    }

    protected SelectResponse timeCommitmentToSelectResponse(TimeCommitment timeCommitment) {
        if ( timeCommitment == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( timeCommitment.getId() != null ) {
            selectResponse.setId( timeCommitment.getId() );
        }

        return selectResponse;
    }

    protected SelectResponse productToSelectResponse1(Product product) {
        if ( product == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( product.getName() != null ) {
            selectResponse.setText( product.getName() );
        }
        if ( product.getId() != null ) {
            selectResponse.setId( product.getId() );
        }

        return selectResponse;
    }

    protected SelectResponse productToSelectResponse2(Product product) {
        if ( product == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( product.getId() != null ) {
            selectResponse.setId( product.getId() );
        }

        return selectResponse;
    }

    protected SelectResponse usingProductDtoToSelectResponse(UsingProductDto usingProductDto) {
        if ( usingProductDto == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( usingProductDto.getId() != null ) {
            selectResponse.setId( usingProductDto.getId() );
        }

        return selectResponse;
    }

    protected List<SelectResponse> usingProductDtoListToSelectResponseList(List<UsingProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SelectResponse> list1 = new ArrayList<SelectResponse>( list.size() );
        for ( UsingProductDto usingProductDto : list ) {
            list1.add( usingProductDtoToSelectResponse( usingProductDto ) );
        }

        return list1;
    }
}
