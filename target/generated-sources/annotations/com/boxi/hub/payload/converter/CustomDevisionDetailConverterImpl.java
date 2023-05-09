package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import com.boxi.hub.payload.dto.CustomDevisionDetailDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class CustomDevisionDetailConverterImpl implements CustomDevisionDetailConverter {

    @Override
    public CustomDevisionDetail fromDtoToModel(CustomDevisionDetailDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        CustomDevisionDetail customDevisionDetail = new CustomDevisionDetail();

        if ( Dto.getId() != null ) {
            customDevisionDetail.setId( Dto.getId() );
        }
        if ( Dto.getIsActive() != null ) {
            customDevisionDetail.setIsActive( Dto.getIsActive() );
        }
        if ( Dto.getIsDeleted() != null ) {
            customDevisionDetail.setIsDeleted( Dto.getIsDeleted() );
        }
        if ( Dto.getToCountryDevision() != null ) {
            customDevisionDetail.setToCountryDevision( selectResponseToCountryDevision( Dto.getToCountryDevision() ) );
        }
        if ( Dto.getFromCountryDevision() != null ) {
            customDevisionDetail.setFromCountryDevision( selectResponseToCountryDevision( Dto.getFromCountryDevision() ) );
        }

        return customDevisionDetail;
    }

    @Override
    public CustomDevisionDetailDto fromModelToDto(CustomDevisionDetail customCountryDevision) {
        if ( customCountryDevision == null ) {
            return null;
        }

        CustomDevisionDetailDto customDevisionDetailDto = new CustomDevisionDetailDto();

        if ( customCountryDevision.getId() != null ) {
            customDevisionDetailDto.setId( customCountryDevision.getId() );
        }
        if ( customCountryDevision.getIsActive() != null ) {
            customDevisionDetailDto.setIsActive( customCountryDevision.getIsActive() );
        }
        if ( customCountryDevision.getIsDeleted() != null ) {
            customDevisionDetailDto.setIsDeleted( customCountryDevision.getIsDeleted() );
        }
        if ( customCountryDevision.getToCountryDevision() != null ) {
            customDevisionDetailDto.setToCountryDevision( countryDevisionToSelectResponse( customCountryDevision.getToCountryDevision() ) );
        }
        if ( customCountryDevision.getFromCountryDevision() != null ) {
            customDevisionDetailDto.setFromCountryDevision( countryDevisionToSelectResponse( customCountryDevision.getFromCountryDevision() ) );
        }

        return customDevisionDetailDto;
    }

    @Override
    public void updateFromDto(CustomCountryDevisionDto dto, CustomCountryDevision customCountryDevision) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            customCountryDevision.setId( dto.getId() );
        }
        else {
            customCountryDevision.setId( null );
        }
        if ( dto.getCode() != null ) {
            customCountryDevision.setCode( dto.getCode() );
        }
        else {
            customCountryDevision.setCode( null );
        }
        if ( dto.getName() != null ) {
            customCountryDevision.setName( dto.getName() );
        }
        else {
            customCountryDevision.setName( null );
        }
        if ( dto.getIsActive() != null ) {
            customCountryDevision.setIsActive( dto.getIsActive() );
        }
        else {
            customCountryDevision.setIsActive( null );
        }
        if ( dto.getIsDeleted() != null ) {
            customCountryDevision.setIsDeleted( dto.getIsDeleted() );
        }
        else {
            customCountryDevision.setIsDeleted( null );
        }
        if ( customCountryDevision.getCustomDevisionDetails() != null ) {
            List<CustomDevisionDetail> list = customDevisionDetailDtoListToCustomDevisionDetailList( dto.getCustomDevisionDetails() );
            if ( list != null ) {
                customCountryDevision.getCustomDevisionDetails().clear();
                customCountryDevision.getCustomDevisionDetails().addAll( list );
            }
        }
        else {
            List<CustomDevisionDetail> list = customDevisionDetailDtoListToCustomDevisionDetailList( dto.getCustomDevisionDetails() );
            if ( list != null ) {
                customCountryDevision.setCustomDevisionDetails( list );
            }
        }
    }

    protected CountryDevision selectResponseToCountryDevision(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        CountryDevision countryDevision = new CountryDevision();

        if ( selectResponse.getId() != null ) {
            countryDevision.setId( selectResponse.getId() );
        }

        return countryDevision;
    }

    protected SelectResponse countryDevisionToSelectResponse(CountryDevision countryDevision) {
        if ( countryDevision == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( countryDevision.getId() != null ) {
            selectResponse.setId( countryDevision.getId() );
        }

        return selectResponse;
    }

    protected List<CustomDevisionDetail> customDevisionDetailDtoListToCustomDevisionDetailList(List<CustomDevisionDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CustomDevisionDetail> list1 = new ArrayList<CustomDevisionDetail>( list.size() );
        for ( CustomDevisionDetailDto customDevisionDetailDto : list ) {
            list1.add( fromDtoToModel( customDevisionDetailDto ) );
        }

        return list1;
    }
}
