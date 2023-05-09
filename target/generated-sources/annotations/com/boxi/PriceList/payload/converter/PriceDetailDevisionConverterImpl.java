package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.payload.dto.PriceDetailDevisionDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.enums.CountryType;
import com.boxi.product.response.ContryDevistionSelect;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class PriceDetailDevisionConverterImpl implements PriceDetailDevisionConverter {

    @Override
    public PriceDetailDevision fromDtoToModel(PriceDetailDevisionDto dto) {
        if ( dto == null ) {
            return null;
        }

        PriceDetailDevision priceDetailDevision = new PriceDetailDevision();

        if ( dto.getId() != null ) {
            priceDetailDevision.setId( dto.getId() );
        }
        if ( dto.getPriceListDetail() != null ) {
            priceDetailDevision.setPriceListDetail( selectResponseToPriceListDetail( dto.getPriceListDetail() ) );
        }

        afterMapToModel( dto, priceDetailDevision );

        return priceDetailDevision;
    }

    @Override
    public void updateFromDto(PriceDetailDevisionDto dto, PriceDetailDevision priceDetailDevision) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            priceDetailDevision.setId( dto.getId() );
        }
        else {
            priceDetailDevision.setId( null );
        }
        if ( dto.getPriceListDetail() != null ) {
            if ( priceDetailDevision.getPriceListDetail() == null ) {
                priceDetailDevision.setPriceListDetail( new PriceListDetail() );
            }
            selectResponseToPriceListDetail1( dto.getPriceListDetail(), priceDetailDevision.getPriceListDetail() );
        }
        else {
            priceDetailDevision.setPriceListDetail( null );
        }

        afterMapToModel( dto, priceDetailDevision );
    }

    @Override
    public PriceDetailDevisionDto fromModelToDto(PriceDetailDevision priceDetailDevision) {
        if ( priceDetailDevision == null ) {
            return null;
        }

        PriceDetailDevisionDto priceDetailDevisionDto = new PriceDetailDevisionDto();

        if ( priceDetailDevision.getFromCountryDevision() != null ) {
            priceDetailDevisionDto.setFromCountryDevision( countryDevisionToContryDevistionSelect( priceDetailDevision.getFromCountryDevision() ) );
        }
        if ( priceDetailDevision.getToCountryDevision() != null ) {
            priceDetailDevisionDto.setToCountryDevision( countryDevisionToContryDevistionSelect1( priceDetailDevision.getToCountryDevision() ) );
        }
        if ( priceDetailDevision.getId() != null ) {
            priceDetailDevisionDto.setId( priceDetailDevision.getId() );
        }
        if ( priceDetailDevision.getPriceListDetail() != null ) {
            priceDetailDevisionDto.setPriceListDetail( priceListDetailToSelectResponse( priceDetailDevision.getPriceListDetail() ) );
        }

        afterMapToDto( priceDetailDevision, priceDetailDevisionDto );

        return priceDetailDevisionDto;
    }

    protected PriceListDetail selectResponseToPriceListDetail(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        PriceListDetail priceListDetail = new PriceListDetail();

        if ( selectResponse.getId() != null ) {
            priceListDetail.setId( selectResponse.getId() );
        }

        return priceListDetail;
    }

    protected void selectResponseToPriceListDetail1(SelectResponse selectResponse, PriceListDetail mappingTarget) {
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

    protected ContryDevistionSelect countryDevisionToContryDevistionSelect1(CountryDevision countryDevision) {
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

    protected SelectResponse priceListDetailToSelectResponse(PriceListDetail priceListDetail) {
        if ( priceListDetail == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( priceListDetail.getId() != null ) {
            selectResponse.setId( priceListDetail.getId() );
        }

        return selectResponse;
    }
}
