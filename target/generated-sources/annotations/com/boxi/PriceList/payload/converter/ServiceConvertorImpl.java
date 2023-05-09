package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import com.boxi.product.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:48+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class ServiceConvertorImpl implements ServiceConvertor {

    @Override
    public Services fromDtoToModel(ServiceDto dto) {
        if ( dto == null ) {
            return null;
        }

        Services services = new Services();

        Long id = dtoTypeId( dto );
        if ( id != null ) {
            services.setType( id );
        }
        if ( dto.getId() != null ) {
            services.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            services.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            services.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getCode() != null ) {
            services.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            services.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            services.setDescription( dto.getDescription() );
        }
        if ( dto.getMinimumOrderQuantity() != null ) {
            services.setMinimumOrderQuantity( dto.getMinimumOrderQuantity() );
        }
        if ( dto.getProduct() != null ) {
            services.setProduct( selectResponseToProduct( dto.getProduct() ) );
        }
        if ( dto.getPriceList() != null ) {
            services.setPriceList( selectResponseToPriceList( dto.getPriceList() ) );
        }

        afterDtoToModel( dto, services );
        afterMapToDto( dto, services );

        return services;
    }

    @Override
    public void updateFromDto(ServiceDto dto, Services services) {
        if ( dto == null ) {
            return;
        }

        Long id = dtoTypeId( dto );
        if ( id != null ) {
            services.setType( id );
        }
        else {
            services.setType( null );
        }
        if ( dto.getId() != null ) {
            services.setId( dto.getId() );
        }
        else {
            services.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            services.setIsActive( dto.getIsActive() );
        }
        else {
            services.setIsActive( null );
        }
        if ( dto.getIsDeleted() != null ) {
            services.setIsDeleted( dto.getIsDeleted() );
        }
        else {
            services.setIsDeleted( null );
        }
        if ( dto.getCode() != null ) {
            services.setCode( dto.getCode() );
        }
        else {
            services.setCode( null );
        }
        if ( dto.getName() != null ) {
            services.setName( dto.getName() );
        }
        else {
            services.setName( null );
        }
        if ( dto.getDescription() != null ) {
            services.setDescription( dto.getDescription() );
        }
        else {
            services.setDescription( null );
        }
        if ( dto.getMinimumOrderQuantity() != null ) {
            services.setMinimumOrderQuantity( dto.getMinimumOrderQuantity() );
        }
        else {
            services.setMinimumOrderQuantity( null );
        }
        if ( dto.getProduct() != null ) {
            if ( services.getProduct() == null ) {
                services.setProduct( new Product() );
            }
            selectResponseToProduct1( dto.getProduct(), services.getProduct() );
        }
        else {
            services.setProduct( null );
        }
        if ( dto.getPriceList() != null ) {
            if ( services.getPriceList() == null ) {
                services.setPriceList( new PriceList() );
            }
            selectResponseToPriceList1( dto.getPriceList(), services.getPriceList() );
        }
        else {
            services.setPriceList( null );
        }

        afterDtoToModel( dto, services );
        afterMapToDto( dto, services );
    }

    @Override
    public ServiceDto fromModelToDto(Services services) {
        if ( services == null ) {
            return null;
        }

        ServiceDto serviceDto = new ServiceDto();

        if ( services != null ) {
            serviceDto.setType( servicesToSelectResponse( services ) );
        }
        if ( services.getId() != null ) {
            serviceDto.setId( services.getId() );
        }
        if ( services.getIsActive() != null ) {
            serviceDto.setIsActive( services.getIsActive() );
        }
        if ( services.getIsDeleted() != null ) {
            serviceDto.setIsDeleted( services.getIsDeleted() );
        }
        if ( services.getCode() != null ) {
            serviceDto.setCode( services.getCode() );
        }
        if ( services.getName() != null ) {
            serviceDto.setName( services.getName() );
        }
        if ( services.getDescription() != null ) {
            serviceDto.setDescription( services.getDescription() );
        }
        if ( services.getMinimumOrderQuantity() != null ) {
            serviceDto.setMinimumOrderQuantity( services.getMinimumOrderQuantity() );
        }
        if ( services.getProduct() != null ) {
            serviceDto.setProduct( productToSelectResponse( services.getProduct() ) );
        }
        if ( services.getPriceList() != null ) {
            serviceDto.setPriceList( priceListToSelectResponse( services.getPriceList() ) );
        }

        afterModelToDto( services, serviceDto );

        return serviceDto;
    }

    @Override
    public ServiceDto fromExcelToDto(CreateServiceExcelRequest createServiceExcelRequest) {
        if ( createServiceExcelRequest == null ) {
            return null;
        }

        ServiceDto serviceDto = new ServiceDto();

        if ( createServiceExcelRequest.getIsActive() != null ) {
            serviceDto.setIsActive( createServiceExcelRequest.getIsActive() );
        }
        if ( createServiceExcelRequest.getCode() != null ) {
            serviceDto.setCode( createServiceExcelRequest.getCode() );
        }
        if ( createServiceExcelRequest.getName() != null ) {
            serviceDto.setName( createServiceExcelRequest.getName() );
        }
        if ( createServiceExcelRequest.getDescription() != null ) {
            serviceDto.setDescription( createServiceExcelRequest.getDescription() );
        }
        if ( createServiceExcelRequest.getMinimumOrderQuantity() != null ) {
            serviceDto.setMinimumOrderQuantity( createServiceExcelRequest.getMinimumOrderQuantity() );
        }

        afterExcelToDto( createServiceExcelRequest, serviceDto );

        return serviceDto;
    }

    private Long dtoTypeId(ServiceDto serviceDto) {
        if ( serviceDto == null ) {
            return null;
        }
        SelectResponse type = serviceDto.getType();
        if ( type == null ) {
            return null;
        }
        Long id = type.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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

    protected SelectResponse servicesToSelectResponse(Services services) {
        if ( services == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( services.getType() != null ) {
            selectResponse.setId( services.getType() );
        }

        return selectResponse;
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
