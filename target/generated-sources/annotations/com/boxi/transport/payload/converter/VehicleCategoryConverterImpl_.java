package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleCategory;
import com.boxi.transport.payload.dto.VehicleCategoryDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:48+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class VehicleCategoryConverterImpl_ implements VehicleCategoryConverter {

    @Override
    public VehicleCategory fromDtoToModel(VehicleCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        VehicleCategory vehicleCategory = new VehicleCategory();

        if ( dto.getId() != null ) {
            vehicleCategory.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            vehicleCategory.setIsActive( dto.getIsActive() );
        }
        if ( dto.getName() != null ) {
            vehicleCategory.setName( dto.getName() );
        }
        if ( dto.getCode() != null ) {
            vehicleCategory.setCode( dto.getCode() );
        }

        return vehicleCategory;
    }

    @Override
    public VehicleCategoryDto fromModelToDto(VehicleCategory model) {
        if ( model == null ) {
            return null;
        }

        VehicleCategoryDto vehicleCategoryDto = new VehicleCategoryDto();

        if ( model.getId() != null ) {
            vehicleCategoryDto.setId( model.getId() );
        }
        if ( model.getIsActive() != null ) {
            vehicleCategoryDto.setIsActive( model.getIsActive() );
        }
        if ( model.getName() != null ) {
            vehicleCategoryDto.setName( model.getName() );
        }
        if ( model.getCode() != null ) {
            vehicleCategoryDto.setCode( model.getCode() );
        }

        return vehicleCategoryDto;
    }

    @Override
    public SelectResponse fromModelToSelect(VehicleCategory v) {
        if ( v == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( v.getId() != null ) {
            selectResponse.setId( v.getId() );
        }

        return selectResponse;
    }

    @Override
    public VehicleCategory fromSelectToModel(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        VehicleCategory vehicleCategory = new VehicleCategory();

        if ( select.getId() != null ) {
            vehicleCategory.setId( select.getId() );
        }

        return vehicleCategory;
    }
}
