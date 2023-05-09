package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.payload.dto.PriceListDetailDto;
import com.boxi.PriceList.payload.dto.PriceListDetailFilterDto;
import com.boxi.PriceList.payload.dto.PriceListDto;
import com.boxi.PriceList.payload.dto.PriceListExcelDto;
import com.boxi.PriceList.payload.dto.PriceListFilterDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T14:38:18+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class PriceListConverterImpl_ implements PriceListConverter {

    @Autowired
    private PriceListDetailConverter priceListDetailConverter;

    @Override
    public PriceList fromDtoToModel(PriceListDto dto) {
        if ( dto == null ) {
            return null;
        }

        PriceList priceList = new PriceList();

        if ( dto.getId() != null ) {
            priceList.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            priceList.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            priceList.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getPriceListCode() != null ) {
            priceList.setPriceListCode( dto.getPriceListCode() );
        }
        if ( dto.getPriceListName() != null ) {
            priceList.setPriceListName( dto.getPriceListName() );
        }
        List<PriceListDetail> list = priceListDetailDtoListToPriceListDetailList( dto.getPriceListDetails() );
        if ( list != null ) {
            priceList.setPriceListDetails( list );
        }

        afterMapToModel( dto, priceList );
        afterDtoToModel( dto, priceList );

        return priceList;
    }

    @Override
    public void updateFromDto(PriceListDto dto, PriceList attribute) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            attribute.setId( dto.getId() );
        }
        else {
            attribute.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            attribute.setIsActive( dto.getIsActive() );
        }
        else {
            attribute.setIsActive( null );
        }
        if ( dto.getIsDeleted() != null ) {
            attribute.setIsDeleted( dto.getIsDeleted() );
        }
        else {
            attribute.setIsDeleted( null );
        }
        if ( dto.getPriceListCode() != null ) {
            attribute.setPriceListCode( dto.getPriceListCode() );
        }
        else {
            attribute.setPriceListCode( null );
        }
        if ( dto.getPriceListName() != null ) {
            attribute.setPriceListName( dto.getPriceListName() );
        }
        else {
            attribute.setPriceListName( null );
        }
        if ( attribute.getPriceListDetails() != null ) {
            List<PriceListDetail> list = priceListDetailDtoListToPriceListDetailList( dto.getPriceListDetails() );
            if ( list != null ) {
                attribute.getPriceListDetails().clear();
                attribute.getPriceListDetails().addAll( list );
            }
        }
        else {
            List<PriceListDetail> list = priceListDetailDtoListToPriceListDetailList( dto.getPriceListDetails() );
            if ( list != null ) {
                attribute.setPriceListDetails( list );
            }
        }

        afterMapToModel( dto, attribute );
        afterDtoToModel( dto, attribute );
    }

    @Override
    public PriceListDto fromModelToDto(PriceList productAttribute) {
        if ( productAttribute == null ) {
            return null;
        }

        PriceListDto priceListDto = new PriceListDto();

        if ( productAttribute.getId() != null ) {
            priceListDto.setId( productAttribute.getId() );
        }
        if ( productAttribute.getIsActive() != null ) {
            priceListDto.setIsActive( productAttribute.getIsActive() );
        }
        if ( productAttribute.getIsDeleted() != null ) {
            priceListDto.setIsDeleted( productAttribute.getIsDeleted() );
        }
        if ( productAttribute.getPriceListCode() != null ) {
            priceListDto.setPriceListCode( productAttribute.getPriceListCode() );
        }
        if ( productAttribute.getPriceListName() != null ) {
            priceListDto.setPriceListName( productAttribute.getPriceListName() );
        }
        List<PriceListDetailDto> list = priceListDetailListToPriceListDetailDtoList( productAttribute.getPriceListDetails() );
        if ( list != null ) {
            priceListDto.setPriceListDetails( list );
        }

        afterMapToDto( productAttribute, priceListDto );

        return priceListDto;
    }

    @Override
    public PriceListFilterDto fromDtoSelectToModel(PriceListDto dto) {
        if ( dto == null ) {
            return null;
        }

        PriceListFilterDto priceListFilterDto = new PriceListFilterDto();

        if ( dto.getId() != null ) {
            priceListFilterDto.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            priceListFilterDto.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            priceListFilterDto.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getPriceListCode() != null ) {
            priceListFilterDto.setPriceListCode( dto.getPriceListCode() );
        }
        if ( dto.getPriceListName() != null ) {
            priceListFilterDto.setPriceListName( dto.getPriceListName() );
        }

        afterDtoSelectTo( dto, priceListFilterDto );

        return priceListFilterDto;
    }

    @Override
    public PriceListDto fromExcelToDto(PriceListExcelDto excel) {
        if ( excel == null ) {
            return null;
        }

        PriceListDto priceListDto = new PriceListDto();

        if ( excel.getCode() != null ) {
            priceListDto.setPriceListCode( excel.getCode() );
        }
        if ( excel.getIsActive() != null ) {
            priceListDto.setIsActive( excel.getIsActive() );
        }

        afterExcelToDto( excel, priceListDto );

        return priceListDto;
    }

    @Override
    public PriceListFilterDto fromFilterMap(PriceList dto) {
        if ( dto == null ) {
            return null;
        }

        PriceListFilterDto priceListFilterDto = new PriceListFilterDto();

        if ( dto.getId() != null ) {
            priceListFilterDto.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            priceListFilterDto.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            priceListFilterDto.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getPriceListCode() != null ) {
            priceListFilterDto.setPriceListCode( dto.getPriceListCode() );
        }
        if ( dto.getPriceListName() != null ) {
            priceListFilterDto.setPriceListName( dto.getPriceListName() );
        }
        List<PriceListDetailFilterDto> list = priceListDetailListToPriceListDetailFilterDtoList( dto.getPriceListDetails() );
        if ( list != null ) {
            priceListFilterDto.setPriceListDetails( list );
        }

        afterFilterMap( dto, priceListFilterDto );

        return priceListFilterDto;
    }

    @Override
    public PriceListFilterDto LoadfromFilterMap(PriceList dto) {
        if ( dto == null ) {
            return null;
        }

        PriceListFilterDto priceListFilterDto = new PriceListFilterDto();

        if ( dto.getId() != null ) {
            priceListFilterDto.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            priceListFilterDto.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            priceListFilterDto.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getPriceListCode() != null ) {
            priceListFilterDto.setPriceListCode( dto.getPriceListCode() );
        }
        if ( dto.getPriceListName() != null ) {
            priceListFilterDto.setPriceListName( dto.getPriceListName() );
        }
        List<PriceListDetailFilterDto> list = priceListDetailListToPriceListDetailFilterDtoList( dto.getPriceListDetails() );
        if ( list != null ) {
            priceListFilterDto.setPriceListDetails( list );
        }

        afterFilterMap( dto, priceListFilterDto );

        return priceListFilterDto;
    }

    protected List<PriceListDetail> priceListDetailDtoListToPriceListDetailList(List<PriceListDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        List<PriceListDetail> list1 = new ArrayList<PriceListDetail>( list.size() );
        for ( PriceListDetailDto priceListDetailDto : list ) {
            list1.add( priceListDetailConverter.fromDtoToModel( priceListDetailDto ) );
        }

        return list1;
    }

    protected List<PriceListDetailDto> priceListDetailListToPriceListDetailDtoList(List<PriceListDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<PriceListDetailDto> list1 = new ArrayList<PriceListDetailDto>( list.size() );
        for ( PriceListDetail priceListDetail : list ) {
            list1.add( priceListDetailConverter.fromModelToDto( priceListDetail ) );
        }

        return list1;
    }

    protected List<PriceListDetailFilterDto> priceListDetailListToPriceListDetailFilterDtoList(List<PriceListDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<PriceListDetailFilterDto> list1 = new ArrayList<PriceListDetailFilterDto>( list.size() );
        for ( PriceListDetail priceListDetail : list ) {
            list1.add( priceListDetailConverter.fromModelToFilter( priceListDetail ) );
        }

        return list1;
    }
}
