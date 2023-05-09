package com.boxi.product.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.enums.CountryType;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.ProductAttributeDevision;
import com.boxi.product.payload.dto.ProductAttributeDevisionDto;
import com.boxi.product.response.ContryDevistionSelect;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class ProductAttributeDevisionConverterImpl implements ProductAttributeDevisionConverter {

    @Override
    public ProductAttributeDevision fromDtoToModel(ProductAttributeDevisionDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProductAttributeDevision productAttributeDevision = new ProductAttributeDevision();

        if ( dto.getProductAttribute() != null ) {
            productAttributeDevision.setProductAttribute( selectResponseToProductAttribute( dto.getProductAttribute() ) );
        }
        if ( dto.getIsActive() != null ) {
            productAttributeDevision.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            productAttributeDevision.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getId() != null ) {
            productAttributeDevision.setId( dto.getId() );
        }

        return productAttributeDevision;
    }

    @Override
    public void updateFromDto(ProductAttributeDevisionDto dto, ProductAttributeDevision productAttributeDevision) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            productAttributeDevision.setId( dto.getId() );
        }
        else {
            productAttributeDevision.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            productAttributeDevision.setIsActive( dto.getIsActive() );
        }
        else {
            productAttributeDevision.setIsActive( null );
        }
        if ( dto.getIsDeleted() != null ) {
            productAttributeDevision.setIsDeleted( dto.getIsDeleted() );
        }
        else {
            productAttributeDevision.setIsDeleted( null );
        }
        if ( dto.getProductAttribute() != null ) {
            if ( productAttributeDevision.getProductAttribute() == null ) {
                productAttributeDevision.setProductAttribute( new ProductAttribute() );
            }
            selectResponseToProductAttribute1( dto.getProductAttribute(), productAttributeDevision.getProductAttribute() );
        }
        else {
            productAttributeDevision.setProductAttribute( null );
        }
    }

    @Override
    public ProductAttributeDevisionDto fromModelToDto(ProductAttributeDevision productAttributeDevision) {
        if ( productAttributeDevision == null ) {
            return null;
        }

        ProductAttributeDevisionDto productAttributeDevisionDto = new ProductAttributeDevisionDto();

        if ( productAttributeDevision.getProductAttribute() != null ) {
            productAttributeDevisionDto.setProductAttribute( productAttributeToSelectResponse( productAttributeDevision.getProductAttribute() ) );
        }
        if ( productAttributeDevision.getId() != null ) {
            productAttributeDevisionDto.setId( productAttributeDevision.getId() );
        }
        if ( productAttributeDevision.getIsActive() != null ) {
            productAttributeDevisionDto.setIsActive( productAttributeDevision.getIsActive() );
        }
        if ( productAttributeDevision.getIsDeleted() != null ) {
            productAttributeDevisionDto.setIsDeleted( productAttributeDevision.getIsDeleted() );
        }
        if ( productAttributeDevision.getFromCountryDevision() != null ) {
            productAttributeDevisionDto.setFromCountryDevision( countryDevisionToContryDevistionSelect( productAttributeDevision.getFromCountryDevision() ) );
        }
        if ( productAttributeDevision.getToCountryDevision() != null ) {
            productAttributeDevisionDto.setToCountryDevision( countryDevisionToContryDevistionSelect( productAttributeDevision.getToCountryDevision() ) );
        }

        return productAttributeDevisionDto;
    }

    @Override
    public List<ProductAttributeDevisionDto> fromModelToDtoList(List<ProductAttributeDevision> productAttributeDevision) {
        if ( productAttributeDevision == null ) {
            return null;
        }

        List<ProductAttributeDevisionDto> list = new ArrayList<ProductAttributeDevisionDto>( productAttributeDevision.size() );
        for ( ProductAttributeDevision productAttributeDevision1 : productAttributeDevision ) {
            list.add( fromModelToDto( productAttributeDevision1 ) );
        }

        return list;
    }

    @Override
    public SelectResponse ProductAttributeDevisionToSelect(ProductAttributeDevision PA) {
        if ( PA == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( PA.getId() != null ) {
            selectResponse.setId( PA.getId() );
        }

        return selectResponse;
    }

    @Override
    public ProductAttributeDevision selectToProductAttributeDevision(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        ProductAttributeDevision productAttributeDevision = new ProductAttributeDevision();

        if ( select.getId() != null ) {
            productAttributeDevision.setId( select.getId() );
        }

        return productAttributeDevision;
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

    protected SelectResponse countryTypeToSelectResponse(CountryType countryType) {
        if ( countryType == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        return selectResponse;
    }

    protected ContryDevistionSelect countryDevisionToContryDevistionSelect(CountryDevision countryDevision) {
        if ( countryDevision == null ) {
            return null;
        }

        ContryDevistionSelect contryDevistionSelect = new ContryDevistionSelect();

        if ( countryDevision.getId() != null ) {
            contryDevistionSelect.setId( countryDevision.getId() );
        }
        if ( countryDevision.getCountryType() != null ) {
            contryDevistionSelect.setCountryType( countryTypeToSelectResponse( countryDevision.getCountryType() ) );
        }

        return contryDevistionSelect;
    }
}
