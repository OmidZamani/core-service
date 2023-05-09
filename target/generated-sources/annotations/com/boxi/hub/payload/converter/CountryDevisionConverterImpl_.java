package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.enums.CountryType;
import com.boxi.hub.payload.dto.CountryDevisionPolygonDto;
import com.boxi.hub.payload.dto.LocationDto;
import com.boxi.product.payload.dto.CountryDevisionDto;
import com.boxi.product.response.ContryDevistionSelect;
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
public class CountryDevisionConverterImpl_ implements CountryDevisionConverter {

    @Override
    public SelectResponse fromModelToSelect(CountryDevision c) {
        if ( c == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( c.getId() != null ) {
            selectResponse.setId( c.getId() );
        }

        return selectResponse;
    }

    @Override
    public ContryDevistionSelect fromModelToType(CountryDevision c) {
        if ( c == null ) {
            return null;
        }

        ContryDevistionSelect contryDevistionSelect = new ContryDevistionSelect();

        if ( c.getId() != null ) {
            contryDevistionSelect.setId( c.getId() );
        }
        if ( c.getCountryType() != null ) {
            contryDevistionSelect.setCountryType( countryTypeToSelectResponse( c.getCountryType() ) );
        }

        return contryDevistionSelect;
    }

    @Override
    public CountryDevision fromSelectToModel(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        CountryDevision countryDevision = new CountryDevision();

        if ( select.getId() != null ) {
            countryDevision.setId( select.getId() );
        }

        return countryDevision;
    }

    @Override
    public CountryDevision fromDtotoModel(CountryDevisionDto dto) {
        if ( dto == null ) {
            return null;
        }

        CountryDevision countryDevision = new CountryDevision();

        if ( dto.getId() != null ) {
            countryDevision.setId( dto.getId() );
        }
        if ( dto.getCode() != null ) {
            countryDevision.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            countryDevision.setName( dto.getName() );
        }
        if ( dto.getLongtitude() != null ) {
            countryDevision.setLongtitude( dto.getLongtitude() );
        }
        if ( dto.getLatitude() != null ) {
            countryDevision.setLatitude( dto.getLatitude() );
        }
        List<CountryDevision> list = dto.getChilds();
        if ( list != null ) {
            countryDevision.setChilds( new ArrayList<CountryDevision>( list ) );
        }
        if ( dto.getParent() != null ) {
            countryDevision.setParent( fromDtotoModel( dto.getParent() ) );
        }

        afterfromDtoToModel( dto, countryDevision );

        return countryDevision;
    }

    @Override
    public CountryDevisionDto fromModeltoDto(CountryDevision countryDevision) {
        if ( countryDevision == null ) {
            return null;
        }

        CountryDevisionDto countryDevisionDto = new CountryDevisionDto();

        if ( countryDevision.getId() != null ) {
            countryDevisionDto.setId( countryDevision.getId() );
        }
        if ( countryDevision.getCode() != null ) {
            countryDevisionDto.setCode( countryDevision.getCode() );
        }
        if ( countryDevision.getName() != null ) {
            countryDevisionDto.setName( countryDevision.getName() );
        }
        if ( countryDevision.getCountryType() != null ) {
            countryDevisionDto.setCountryType( countryTypeToSelectResponse( countryDevision.getCountryType() ) );
        }
        if ( countryDevision.getLongtitude() != null ) {
            countryDevisionDto.setLongtitude( countryDevision.getLongtitude() );
        }
        if ( countryDevision.getLatitude() != null ) {
            countryDevisionDto.setLatitude( countryDevision.getLatitude() );
        }
        List<CountryDevision> list = countryDevision.getChilds();
        if ( list != null ) {
            countryDevisionDto.setChilds( new ArrayList<CountryDevision>( list ) );
        }
        if ( countryDevision.getParent() != null ) {
            countryDevisionDto.setParent( fromModeltoDto( countryDevision.getParent() ) );
        }

        afterfromModelToDto( countryDevision, countryDevisionDto );

        return countryDevisionDto;
    }

    @Override
    public CountryDevisionPolygonDto fromModeltoPolygoneDto(CountryDevisionDto countryDevision) {
        if ( countryDevision == null ) {
            return null;
        }

        CountryDevisionPolygonDto countryDevisionPolygonDto = new CountryDevisionPolygonDto();

        if ( countryDevision.getId() != null ) {
            countryDevisionPolygonDto.setId( countryDevision.getId() );
        }
        if ( countryDevision.getCode() != null ) {
            countryDevisionPolygonDto.setCode( countryDevision.getCode() );
        }
        if ( countryDevision.getName() != null ) {
            countryDevisionPolygonDto.setName( countryDevision.getName() );
        }
        if ( countryDevision.getCountryType() != null ) {
            countryDevisionPolygonDto.setCountryType( countryDevision.getCountryType() );
        }
        if ( countryDevision.getLongtitude() != null ) {
            countryDevisionPolygonDto.setLongtitude( countryDevision.getLongtitude() );
        }
        if ( countryDevision.getLatitude() != null ) {
            countryDevisionPolygonDto.setLatitude( countryDevision.getLatitude() );
        }
        if ( countryDevision.getSelectResponsiblePersonel() != null ) {
            countryDevisionPolygonDto.setSelectResponsiblePersonel( countryDevision.getSelectResponsiblePersonel() );
        }
        if ( countryDevision.getSelectHub() != null ) {
            countryDevisionPolygonDto.setSelectHub( countryDevision.getSelectHub() );
        }
        List<LocationDto> list = countryDevision.getPolygone();
        if ( list != null ) {
            countryDevisionPolygonDto.setPolygone( new ArrayList<LocationDto>( list ) );
        }

        return countryDevisionPolygonDto;
    }

    protected SelectResponse countryTypeToSelectResponse(CountryType countryType) {
        if ( countryType == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        return selectResponse;
    }
}
