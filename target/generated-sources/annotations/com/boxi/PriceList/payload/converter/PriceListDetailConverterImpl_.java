package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.payload.dto.PriceDetailDevisionDto;
import com.boxi.PriceList.payload.dto.PriceListDetailDto;
import com.boxi.PriceList.payload.dto.PriceListDetailExcelDto;
import com.boxi.PriceList.payload.dto.PriceListDetailFilterDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import com.boxi.hub.payload.dto.CustomDevisionDetailDto;
import com.boxi.product.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class PriceListDetailConverterImpl_ implements PriceListDetailConverter {

    @Autowired
    private PriceDetailDevisionConverter priceDetailDevisionConverter;

    @Override
    public PriceListDetail fromDtoToModel(PriceListDetailDto dto) {
        if ( dto == null ) {
            return null;
        }

        PriceListDetail priceListDetail = new PriceListDetail();

        if ( dto.getCustomDevision() != null ) {
            priceListDetail.setCustomCountryDevision( customCountryDevisionDtoToCustomCountryDevision( dto.getCustomDevision() ) );
        }
        Long id = dtoConsignmentTypeId( dto );
        if ( id != null ) {
            priceListDetail.setConsignmentType( id );
        }
        if ( dto.getId() != null ) {
            priceListDetail.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            priceListDetail.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            priceListDetail.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getPrice() != null ) {
            priceListDetail.setPrice( dto.getPrice() );
        }
        if ( dto.getPriceFormule() != null ) {
            priceListDetail.setPriceFormule( dto.getPriceFormule() );
        }
        if ( dto.getFromWeight() != null ) {
            priceListDetail.setFromWeight( dto.getFromWeight() );
        }
        if ( dto.getToWeight() != null ) {
            priceListDetail.setToWeight( dto.getToWeight() );
        }
        if ( dto.getFromDim() != null ) {
            priceListDetail.setFromDim( dto.getFromDim() );
        }
        if ( dto.getToDimension() != null ) {
            priceListDetail.setToDimension( dto.getToDimension() );
        }
        if ( dto.getFromValue() != null ) {
            priceListDetail.setFromValue( dto.getFromValue() );
        }
        if ( dto.getToValue() != null ) {
            priceListDetail.setToValue( dto.getToValue() );
        }
        if ( dto.getFromNumber() != null ) {
            priceListDetail.setFromNumber( dto.getFromNumber() );
        }
        if ( dto.getToNumber() != null ) {
            priceListDetail.setToNumber( dto.getToNumber() );
        }
        if ( dto.getIsParametric() != null ) {
            priceListDetail.setIsParametric( dto.getIsParametric() );
        }
        List<PriceDetailDevision> list = priceDetailDevisionDtoListToPriceDetailDevisionList( dto.getPriceDetailDevisions() );
        if ( list != null ) {
            priceListDetail.setPriceDetailDevisions( list );
        }
        if ( dto.getProduct() != null ) {
            priceListDetail.setProduct( selectResponseToProduct( dto.getProduct() ) );
        }
        if ( dto.getPriceList() != null ) {
            priceListDetail.setPriceList( selectResponseToPriceList( dto.getPriceList() ) );
        }

        afterfromDtoToModel( dto, priceListDetail );

        return priceListDetail;
    }

    @Override
    public void updateFromDto(PriceListDetailDto dto, PriceListDetail attribute) {
        if ( dto == null ) {
            return;
        }

        Long id = dtoConsignmentTypeId( dto );
        if ( id != null ) {
            attribute.setConsignmentType( id );
        }
        else {
            attribute.setConsignmentType( null );
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
        if ( dto.getPrice() != null ) {
            attribute.setPrice( dto.getPrice() );
        }
        else {
            attribute.setPrice( null );
        }
        if ( dto.getPriceFormule() != null ) {
            attribute.setPriceFormule( dto.getPriceFormule() );
        }
        else {
            attribute.setPriceFormule( null );
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
            attribute.setFromValue( dto.getFromValue() );
        }
        else {
            attribute.setFromValue( null );
        }
        if ( dto.getToValue() != null ) {
            attribute.setToValue( dto.getToValue() );
        }
        else {
            attribute.setToValue( null );
        }
        if ( dto.getFromNumber() != null ) {
            attribute.setFromNumber( dto.getFromNumber() );
        }
        else {
            attribute.setFromNumber( null );
        }
        if ( dto.getToNumber() != null ) {
            attribute.setToNumber( dto.getToNumber() );
        }
        else {
            attribute.setToNumber( null );
        }
        if ( dto.getIsParametric() != null ) {
            attribute.setIsParametric( dto.getIsParametric() );
        }
        else {
            attribute.setIsParametric( null );
        }
        if ( attribute.getPriceDetailDevisions() != null ) {
            List<PriceDetailDevision> list = priceDetailDevisionDtoListToPriceDetailDevisionList( dto.getPriceDetailDevisions() );
            if ( list != null ) {
                attribute.getPriceDetailDevisions().clear();
                attribute.getPriceDetailDevisions().addAll( list );
            }
        }
        else {
            List<PriceDetailDevision> list = priceDetailDevisionDtoListToPriceDetailDevisionList( dto.getPriceDetailDevisions() );
            if ( list != null ) {
                attribute.setPriceDetailDevisions( list );
            }
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
        if ( dto.getPriceList() != null ) {
            if ( attribute.getPriceList() == null ) {
                attribute.setPriceList( new PriceList() );
            }
            selectResponseToPriceList1( dto.getPriceList(), attribute.getPriceList() );
        }
        else {
            attribute.setPriceList( null );
        }

        afterfromDtoToModel( dto, attribute );
    }

    @Override
    public PriceListDetailDto fromModelToDto(PriceListDetail priceListDetail) {
        if ( priceListDetail == null ) {
            return null;
        }

        PriceListDetailDto priceListDetailDto = new PriceListDetailDto();

        if ( priceListDetail != null ) {
            priceListDetailDto.setConsignmentType( priceListDetailToSelectResponse( priceListDetail ) );
        }
        if ( priceListDetail.getCustomCountryDevision() != null ) {
            priceListDetailDto.setCustomDevision( customCountryDevisionToCustomCountryDevisionDto( priceListDetail.getCustomCountryDevision() ) );
        }
        if ( priceListDetail.getId() != null ) {
            priceListDetailDto.setId( priceListDetail.getId() );
        }
        if ( priceListDetail.getIsActive() != null ) {
            priceListDetailDto.setIsActive( priceListDetail.getIsActive() );
        }
        if ( priceListDetail.getIsParametric() != null ) {
            priceListDetailDto.setIsParametric( priceListDetail.getIsParametric() );
        }
        if ( priceListDetail.getIsDeleted() != null ) {
            priceListDetailDto.setIsDeleted( priceListDetail.getIsDeleted() );
        }
        if ( priceListDetail.getPrice() != null ) {
            priceListDetailDto.setPrice( priceListDetail.getPrice() );
        }
        if ( priceListDetail.getPriceFormule() != null ) {
            priceListDetailDto.setPriceFormule( priceListDetail.getPriceFormule() );
        }
        if ( priceListDetail.getFromWeight() != null ) {
            priceListDetailDto.setFromWeight( priceListDetail.getFromWeight() );
        }
        if ( priceListDetail.getToWeight() != null ) {
            priceListDetailDto.setToWeight( priceListDetail.getToWeight() );
        }
        if ( priceListDetail.getFromDim() != null ) {
            priceListDetailDto.setFromDim( priceListDetail.getFromDim() );
        }
        if ( priceListDetail.getToDimension() != null ) {
            priceListDetailDto.setToDimension( priceListDetail.getToDimension() );
        }
        if ( priceListDetail.getFromValue() != null ) {
            priceListDetailDto.setFromValue( priceListDetail.getFromValue() );
        }
        if ( priceListDetail.getToValue() != null ) {
            priceListDetailDto.setToValue( priceListDetail.getToValue() );
        }
        if ( priceListDetail.getFromNumber() != null ) {
            priceListDetailDto.setFromNumber( priceListDetail.getFromNumber() );
        }
        if ( priceListDetail.getToNumber() != null ) {
            priceListDetailDto.setToNumber( priceListDetail.getToNumber() );
        }
        List<PriceDetailDevisionDto> list = priceDetailDevisionListToPriceDetailDevisionDtoList( priceListDetail.getPriceDetailDevisions() );
        if ( list != null ) {
            priceListDetailDto.setPriceDetailDevisions( list );
        }
        if ( priceListDetail.getProduct() != null ) {
            priceListDetailDto.setProduct( productToSelectResponse( priceListDetail.getProduct() ) );
        }
        if ( priceListDetail.getPriceList() != null ) {
            priceListDetailDto.setPriceList( priceListToSelectResponse( priceListDetail.getPriceList() ) );
        }

        validate( priceListDetail, priceListDetailDto );

        return priceListDetailDto;
    }

    @Override
    public PriceListDetailDto fromExcelToDto(PriceListDetailExcelDto priceListDetail) {
        if ( priceListDetail == null ) {
            return null;
        }

        PriceListDetailDto priceListDetailDto = new PriceListDetailDto();

        if ( priceListDetail.getIsActive() != null ) {
            priceListDetailDto.setIsActive( priceListDetail.getIsActive() );
        }
        if ( priceListDetail.getIsParametric() != null ) {
            priceListDetailDto.setIsParametric( priceListDetail.getIsParametric() );
        }
        if ( priceListDetail.getPrice() != null ) {
            priceListDetailDto.setPrice( priceListDetail.getPrice() );
        }
        if ( priceListDetail.getPriceFormule() != null ) {
            priceListDetailDto.setPriceFormule( String.valueOf( priceListDetail.getPriceFormule() ) );
        }
        if ( priceListDetail.getFromWeight() != null ) {
            priceListDetailDto.setFromWeight( priceListDetail.getFromWeight() );
        }
        if ( priceListDetail.getToWeight() != null ) {
            priceListDetailDto.setToWeight( priceListDetail.getToWeight() );
        }
        if ( priceListDetail.getFromDim() != null ) {
            priceListDetailDto.setFromDim( priceListDetail.getFromDim() );
        }
        if ( priceListDetail.getToDimension() != null ) {
            priceListDetailDto.setToDimension( priceListDetail.getToDimension() );
        }
        if ( priceListDetail.getFromValue() != null ) {
            priceListDetailDto.setFromValue( priceListDetail.getFromValue() );
        }
        if ( priceListDetail.getToValue() != null ) {
            priceListDetailDto.setToValue( priceListDetail.getToValue() );
        }

        return priceListDetailDto;
    }

    @Override
    public PriceListDetailFilterDto fromDtoToFilter(PriceListDetailDto priceListDetail) {
        if ( priceListDetail == null ) {
            return null;
        }

        PriceListDetailFilterDto priceListDetailFilterDto = new PriceListDetailFilterDto();

        if ( priceListDetail.getId() != null ) {
            priceListDetailFilterDto.setId( priceListDetail.getId() );
        }
        if ( priceListDetail.getIsActive() != null ) {
            priceListDetailFilterDto.setIsActive( priceListDetail.getIsActive() );
        }
        if ( priceListDetail.getIsParametric() != null ) {
            priceListDetailFilterDto.setIsParametric( priceListDetail.getIsParametric() );
        }
        if ( priceListDetail.getIsDeleted() != null ) {
            priceListDetailFilterDto.setIsDeleted( priceListDetail.getIsDeleted() );
        }
        if ( priceListDetail.getPrice() != null ) {
            priceListDetailFilterDto.setPrice( priceListDetail.getPrice() );
        }
        if ( priceListDetail.getPriceFormule() != null ) {
            priceListDetailFilterDto.setPriceFormule( priceListDetail.getPriceFormule() );
        }
        if ( priceListDetail.getFromWeight() != null ) {
            priceListDetailFilterDto.setFromWeight( priceListDetail.getFromWeight() );
        }
        if ( priceListDetail.getToWeight() != null ) {
            priceListDetailFilterDto.setToWeight( priceListDetail.getToWeight() );
        }
        if ( priceListDetail.getFromDim() != null ) {
            priceListDetailFilterDto.setFromDim( priceListDetail.getFromDim() );
        }
        if ( priceListDetail.getToDimension() != null ) {
            priceListDetailFilterDto.setToDimension( priceListDetail.getToDimension() );
        }
        if ( priceListDetail.getFromValue() != null ) {
            priceListDetailFilterDto.setFromValue( priceListDetail.getFromValue() );
        }
        if ( priceListDetail.getToValue() != null ) {
            priceListDetailFilterDto.setToValue( priceListDetail.getToValue() );
        }
        if ( priceListDetail.getFromNumber() != null ) {
            priceListDetailFilterDto.setFromNumber( priceListDetail.getFromNumber() );
        }
        if ( priceListDetail.getToNumber() != null ) {
            priceListDetailFilterDto.setToNumber( priceListDetail.getToNumber() );
        }
        if ( priceListDetail.getCustomDevision() != null ) {
            priceListDetailFilterDto.setCustomDevision( priceListDetail.getCustomDevision() );
        }
        if ( priceListDetail.getProduct() != null ) {
            priceListDetailFilterDto.setProduct( priceListDetail.getProduct() );
        }
        if ( priceListDetail.getPriceList() != null ) {
            priceListDetailFilterDto.setPriceList( priceListDetail.getPriceList() );
        }
        if ( priceListDetail.getConsignmentType() != null ) {
            priceListDetailFilterDto.setConsignmentType( priceListDetail.getConsignmentType() );
        }
        if ( priceListDetail.getCategoryType() != null ) {
            priceListDetailFilterDto.setCategoryType( priceListDetail.getCategoryType() );
        }

        return priceListDetailFilterDto;
    }

    @Override
    public PriceListDetailFilterDto fromModelToFilter(PriceListDetail priceListDetail) {
        if ( priceListDetail == null ) {
            return null;
        }

        PriceListDetailFilterDto priceListDetailFilterDto = new PriceListDetailFilterDto();

        if ( priceListDetail.getId() != null ) {
            priceListDetailFilterDto.setId( priceListDetail.getId() );
        }
        if ( priceListDetail.getIsActive() != null ) {
            priceListDetailFilterDto.setIsActive( priceListDetail.getIsActive() );
        }
        if ( priceListDetail.getIsParametric() != null ) {
            priceListDetailFilterDto.setIsParametric( priceListDetail.getIsParametric() );
        }
        if ( priceListDetail.getIsDeleted() != null ) {
            priceListDetailFilterDto.setIsDeleted( priceListDetail.getIsDeleted() );
        }
        if ( priceListDetail.getPrice() != null ) {
            priceListDetailFilterDto.setPrice( priceListDetail.getPrice() );
        }
        if ( priceListDetail.getPriceFormule() != null ) {
            priceListDetailFilterDto.setPriceFormule( priceListDetail.getPriceFormule() );
        }
        if ( priceListDetail.getFromWeight() != null ) {
            priceListDetailFilterDto.setFromWeight( priceListDetail.getFromWeight() );
        }
        if ( priceListDetail.getToWeight() != null ) {
            priceListDetailFilterDto.setToWeight( priceListDetail.getToWeight() );
        }
        if ( priceListDetail.getFromDim() != null ) {
            priceListDetailFilterDto.setFromDim( priceListDetail.getFromDim() );
        }
        if ( priceListDetail.getToDimension() != null ) {
            priceListDetailFilterDto.setToDimension( priceListDetail.getToDimension() );
        }
        if ( priceListDetail.getFromValue() != null ) {
            priceListDetailFilterDto.setFromValue( priceListDetail.getFromValue() );
        }
        if ( priceListDetail.getToValue() != null ) {
            priceListDetailFilterDto.setToValue( priceListDetail.getToValue() );
        }
        if ( priceListDetail.getFromNumber() != null ) {
            priceListDetailFilterDto.setFromNumber( priceListDetail.getFromNumber() );
        }
        if ( priceListDetail.getToNumber() != null ) {
            priceListDetailFilterDto.setToNumber( priceListDetail.getToNumber() );
        }
        if ( priceListDetail.getProduct() != null ) {
            priceListDetailFilterDto.setProduct( productToSelectResponse( priceListDetail.getProduct() ) );
        }
        if ( priceListDetail.getPriceList() != null ) {
            priceListDetailFilterDto.setPriceList( priceListToSelectResponse( priceListDetail.getPriceList() ) );
        }

        return priceListDetailFilterDto;
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

    protected CustomCountryDevision customCountryDevisionDtoToCustomCountryDevision(CustomCountryDevisionDto customCountryDevisionDto) {
        if ( customCountryDevisionDto == null ) {
            return null;
        }

        CustomCountryDevision customCountryDevision = new CustomCountryDevision();

        if ( customCountryDevisionDto.getId() != null ) {
            customCountryDevision.setId( customCountryDevisionDto.getId() );
        }
        if ( customCountryDevisionDto.getCode() != null ) {
            customCountryDevision.setCode( customCountryDevisionDto.getCode() );
        }
        if ( customCountryDevisionDto.getName() != null ) {
            customCountryDevision.setName( customCountryDevisionDto.getName() );
        }
        if ( customCountryDevisionDto.getIsActive() != null ) {
            customCountryDevision.setIsActive( customCountryDevisionDto.getIsActive() );
        }
        if ( customCountryDevisionDto.getIsDeleted() != null ) {
            customCountryDevision.setIsDeleted( customCountryDevisionDto.getIsDeleted() );
        }
        List<CustomDevisionDetail> list = customDevisionDetailDtoListToCustomDevisionDetailList( customCountryDevisionDto.getCustomDevisionDetails() );
        if ( list != null ) {
            customCountryDevision.setCustomDevisionDetails( list );
        }

        return customCountryDevision;
    }

    private Long dtoConsignmentTypeId(PriceListDetailDto priceListDetailDto) {
        if ( priceListDetailDto == null ) {
            return null;
        }
        SelectResponse consignmentType = priceListDetailDto.getConsignmentType();
        if ( consignmentType == null ) {
            return null;
        }
        Long id = consignmentType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<PriceDetailDevision> priceDetailDevisionDtoListToPriceDetailDevisionList(List<PriceDetailDevisionDto> list) {
        if ( list == null ) {
            return null;
        }

        List<PriceDetailDevision> list1 = new ArrayList<PriceDetailDevision>( list.size() );
        for ( PriceDetailDevisionDto priceDetailDevisionDto : list ) {
            list1.add( priceDetailDevisionConverter.fromDtoToModel( priceDetailDevisionDto ) );
        }

        return list1;
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

    protected PriceList selectResponseToPriceList(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        PriceList priceList = new PriceList();

        if ( selectResponse.getId() != null ) {
            priceList.setId( selectResponse.getId() );
        }

        return priceList;
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

    protected void selectResponseToPriceList1(SelectResponse selectResponse, PriceList mappingTarget) {
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

    protected SelectResponse priceListDetailToSelectResponse(PriceListDetail priceListDetail) {
        if ( priceListDetail == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( priceListDetail.getConsignmentType() != null ) {
            selectResponse.setId( priceListDetail.getConsignmentType() );
        }

        return selectResponse;
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

    protected CustomCountryDevisionDto customCountryDevisionToCustomCountryDevisionDto(CustomCountryDevision customCountryDevision) {
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

    protected List<PriceDetailDevisionDto> priceDetailDevisionListToPriceDetailDevisionDtoList(List<PriceDetailDevision> list) {
        if ( list == null ) {
            return null;
        }

        List<PriceDetailDevisionDto> list1 = new ArrayList<PriceDetailDevisionDto>( list.size() );
        for ( PriceDetailDevision priceDetailDevision : list ) {
            list1.add( priceDetailDevisionConverter.fromModelToDto( priceDetailDevision ) );
        }

        return list1;
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

    protected SelectResponse priceListToSelectResponse(PriceList priceList) {
        if ( priceList == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( priceList.getId() != null ) {
            selectResponse.setId( priceList.getId() );
        }

        return selectResponse;
    }
}
