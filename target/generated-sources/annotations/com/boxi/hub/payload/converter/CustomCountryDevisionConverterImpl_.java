package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import com.boxi.hub.payload.dto.CustomCountryDevisionExcelDto;
import com.boxi.hub.payload.dto.CustomCountryDevisionFilterDto;
import com.boxi.hub.payload.dto.CustomDevisionDetailDto;
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
public class CustomCountryDevisionConverterImpl_ implements CustomCountryDevisionConverter {

    @Override
    public CustomCountryDevision fromDtoToModel(CustomCountryDevisionDto Dto) {
        if ( Dto == null ) {
            return null;
        }

        CustomCountryDevision customCountryDevision = new CustomCountryDevision();

        if ( Dto.getId() != null ) {
            customCountryDevision.setId( Dto.getId() );
        }
        if ( Dto.getCode() != null ) {
            customCountryDevision.setCode( Dto.getCode() );
        }
        if ( Dto.getName() != null ) {
            customCountryDevision.setName( Dto.getName() );
        }
        if ( Dto.getIsActive() != null ) {
            customCountryDevision.setIsActive( Dto.getIsActive() );
        }
        if ( Dto.getIsDeleted() != null ) {
            customCountryDevision.setIsDeleted( Dto.getIsDeleted() );
        }
        List<CustomDevisionDetail> list = customDevisionDetailDtoListToCustomDevisionDetailList( Dto.getCustomDevisionDetails() );
        if ( list != null ) {
            customCountryDevision.setCustomDevisionDetails( list );
        }

        return customCountryDevision;
    }

    @Override
    public CustomCountryDevisionDto fromModelToDto(CustomCountryDevision customCountryDevision) {
        if ( customCountryDevision == null ) {
            return null;
        }

        CustomCountryDevisionDto customCountryDevisionDto = new CustomCountryDevisionDto();

        if ( customCountryDevision.getId() != null ) {
            customCountryDevisionDto.setId( customCountryDevision.getId() );
        }
        if ( customCountryDevision.getName() != null ) {
            customCountryDevisionDto.setName( customCountryDevision.getName() );
        }
        if ( customCountryDevision.getCode() != null ) {
            customCountryDevisionDto.setCode( customCountryDevision.getCode() );
        }
        if ( customCountryDevision.getIsDeleted() != null ) {
            customCountryDevisionDto.setIsDeleted( customCountryDevision.getIsDeleted() );
        }
        if ( customCountryDevision.getIsActive() != null ) {
            customCountryDevisionDto.setIsActive( customCountryDevision.getIsActive() );
        }
        List<CustomDevisionDetailDto> list = customDevisionDetailListToCustomDevisionDetailDtoList( customCountryDevision.getCustomDevisionDetails() );
        if ( list != null ) {
            customCountryDevisionDto.setCustomDevisionDetails( list );
        }

        return customCountryDevisionDto;
    }

    @Override
    public CustomCountryDevisionFilterDto fromModelToFilter(CustomCountryDevision countryDevision) {
        if ( countryDevision == null ) {
            return null;
        }

        CustomCountryDevisionFilterDto customCountryDevisionFilterDto = new CustomCountryDevisionFilterDto();

        if ( countryDevision.getId() != null ) {
            customCountryDevisionFilterDto.setId( countryDevision.getId() );
        }
        if ( countryDevision.getName() != null ) {
            customCountryDevisionFilterDto.setName( countryDevision.getName() );
        }
        if ( countryDevision.getCode() != null ) {
            customCountryDevisionFilterDto.setCode( countryDevision.getCode() );
        }
        if ( countryDevision.getIsDeleted() != null ) {
            customCountryDevisionFilterDto.setIsDeleted( countryDevision.getIsDeleted() );
        }
        if ( countryDevision.getIsActive() != null ) {
            customCountryDevisionFilterDto.setIsActive( countryDevision.getIsActive() );
        }

        return customCountryDevisionFilterDto;
    }

    @Override
    public CustomCountryDevisionDto fromExcelToDto(CustomCountryDevisionExcelDto customCountryDevisionDto) {
        if ( customCountryDevisionDto == null ) {
            return null;
        }

        CustomCountryDevisionDto customCountryDevisionDto1 = new CustomCountryDevisionDto();

        if ( customCountryDevisionDto.getName() != null ) {
            customCountryDevisionDto1.setName( customCountryDevisionDto.getName() );
        }
        if ( customCountryDevisionDto.getCode() != null ) {
            customCountryDevisionDto1.setCode( customCountryDevisionDto.getCode() );
        }
        if ( customCountryDevisionDto.getIsActive() != null ) {
            customCountryDevisionDto1.setIsActive( customCountryDevisionDto.getIsActive() );
        }

        afterExcelToDto( customCountryDevisionDto, customCountryDevisionDto1 );

        return customCountryDevisionDto1;
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

    protected CustomDevisionDetail customDevisionDetailDtoToCustomDevisionDetail(CustomDevisionDetailDto customDevisionDetailDto) {
        if ( customDevisionDetailDto == null ) {
            return null;
        }

        CustomDevisionDetail customDevisionDetail = new CustomDevisionDetail();

        if ( customDevisionDetailDto.getId() != null ) {
            customDevisionDetail.setId( customDevisionDetailDto.getId() );
        }
        if ( customDevisionDetailDto.getIsActive() != null ) {
            customDevisionDetail.setIsActive( customDevisionDetailDto.getIsActive() );
        }
        if ( customDevisionDetailDto.getIsDeleted() != null ) {
            customDevisionDetail.setIsDeleted( customDevisionDetailDto.getIsDeleted() );
        }
        if ( customDevisionDetailDto.getToCountryDevision() != null ) {
            customDevisionDetail.setToCountryDevision( selectResponseToCountryDevision( customDevisionDetailDto.getToCountryDevision() ) );
        }
        if ( customDevisionDetailDto.getFromCountryDevision() != null ) {
            customDevisionDetail.setFromCountryDevision( selectResponseToCountryDevision( customDevisionDetailDto.getFromCountryDevision() ) );
        }

        return customDevisionDetail;
    }

    protected List<CustomDevisionDetail> customDevisionDetailDtoListToCustomDevisionDetailList(List<CustomDevisionDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CustomDevisionDetail> list1 = new ArrayList<CustomDevisionDetail>( list.size() );
        for ( CustomDevisionDetailDto customDevisionDetailDto : list ) {
            list1.add( customDevisionDetailDtoToCustomDevisionDetail( customDevisionDetailDto ) );
        }

        return list1;
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

    protected CustomDevisionDetailDto customDevisionDetailToCustomDevisionDetailDto(CustomDevisionDetail customDevisionDetail) {
        if ( customDevisionDetail == null ) {
            return null;
        }

        CustomDevisionDetailDto customDevisionDetailDto = new CustomDevisionDetailDto();

        if ( customDevisionDetail.getId() != null ) {
            customDevisionDetailDto.setId( customDevisionDetail.getId() );
        }
        if ( customDevisionDetail.getIsActive() != null ) {
            customDevisionDetailDto.setIsActive( customDevisionDetail.getIsActive() );
        }
        if ( customDevisionDetail.getIsDeleted() != null ) {
            customDevisionDetailDto.setIsDeleted( customDevisionDetail.getIsDeleted() );
        }
        if ( customDevisionDetail.getToCountryDevision() != null ) {
            customDevisionDetailDto.setToCountryDevision( countryDevisionToSelectResponse( customDevisionDetail.getToCountryDevision() ) );
        }
        if ( customDevisionDetail.getFromCountryDevision() != null ) {
            customDevisionDetailDto.setFromCountryDevision( countryDevisionToSelectResponse( customDevisionDetail.getFromCountryDevision() ) );
        }

        return customDevisionDetailDto;
    }

    protected List<CustomDevisionDetailDto> customDevisionDetailListToCustomDevisionDetailDtoList(List<CustomDevisionDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<CustomDevisionDetailDto> list1 = new ArrayList<CustomDevisionDetailDto>( list.size() );
        for ( CustomDevisionDetail customDevisionDetail : list ) {
            list1.add( customDevisionDetailToCustomDevisionDetailDto( customDevisionDetail ) );
        }

        return list1;
    }
}
