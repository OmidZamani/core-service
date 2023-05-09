package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Vendor;
import com.boxi.transport.payload.dto.VendorDto;
import com.boxi.transport.payload.dto.VendorExcelDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class VendorConverterImpl_ implements VendorConverter {

    @Override
    public Vendor fromDtoToModel(VendorDto dto) {
        if ( dto == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        if ( dto.getId() != null ) {
            vendor.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            vendor.setIsActive( dto.getIsActive() );
        }
        if ( dto.getName() != null ) {
            vendor.setName( dto.getName() );
        }
        if ( dto.getCode() != null ) {
            vendor.setCode( dto.getCode() );
        }
        if ( dto.getContactNumber() != null ) {
            vendor.setContactNumber( dto.getContactNumber() );
        }
        if ( dto.getNationalCode() != null ) {
            vendor.setNationalCode( dto.getNationalCode() );
        }

        return vendor;
    }

    @Override
    public VendorDto fromModelToDto(Vendor model) {
        if ( model == null ) {
            return null;
        }

        VendorDto vendorDto = new VendorDto();

        if ( model.getId() != null ) {
            vendorDto.setId( model.getId() );
        }
        if ( model.getName() != null ) {
            vendorDto.setName( model.getName() );
        }
        if ( model.getIsActive() != null ) {
            vendorDto.setIsActive( model.getIsActive() );
        }
        if ( model.getCode() != null ) {
            vendorDto.setCode( model.getCode() );
        }
        if ( model.getContactNumber() != null ) {
            vendorDto.setContactNumber( model.getContactNumber() );
        }
        if ( model.getNationalCode() != null ) {
            vendorDto.setNationalCode( model.getNationalCode() );
        }

        return vendorDto;
    }

    @Override
    public VendorDto fromExcelDto(VendorExcelDto model) {
        if ( model == null ) {
            return null;
        }

        VendorDto vendorDto = new VendorDto();

        if ( model.getName() != null ) {
            vendorDto.setName( model.getName() );
        }
        if ( model.getIsActive() != null ) {
            vendorDto.setIsActive( model.getIsActive() );
        }
        if ( model.getCode() != null ) {
            vendorDto.setCode( model.getCode() );
        }
        if ( model.getContactNumber() != null ) {
            vendorDto.setContactNumber( model.getContactNumber() );
        }
        if ( model.getNationalCode() != null ) {
            vendorDto.setNationalCode( model.getNationalCode() );
        }

        return vendorDto;
    }

    @Override
    public SelectResponse fromModelToSelect(Vendor vendor) {
        if ( vendor == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( vendor.getId() != null ) {
            selectResponse.setId( vendor.getId() );
        }

        return selectResponse;
    }

    @Override
    public Vendor fromSelectToModel(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        Vendor vendor = new Vendor();

        if ( select.getId() != null ) {
            vendor.setId( select.getId() );
        }

        return vendor;
    }
}
