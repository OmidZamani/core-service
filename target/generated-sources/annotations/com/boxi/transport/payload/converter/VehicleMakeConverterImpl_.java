package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleMake;
import com.boxi.transport.payload.dto.VehicleMakeDto;
import com.boxi.transport.payload.dto.VehicleMakeExcelDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class VehicleMakeConverterImpl_ implements VehicleMakeConverter {

    private final VendorConverter vendorConverter;
    private final FuelTypeConverter fuelTypeConverter;

    @Autowired
    public VehicleMakeConverterImpl_(VendorConverter vendorConverter, FuelTypeConverter fuelTypeConverter) {

        this.vendorConverter = vendorConverter;
        this.fuelTypeConverter = fuelTypeConverter;
    }

    @Override
    public VehicleMake fromDtoToModel(VehicleMakeDto dto) {
        if ( dto == null ) {
            return null;
        }

        VehicleMake vehicleMake = new VehicleMake();

        if ( dto.getVendorSelect() != null ) {
            vehicleMake.setVendor( vendorConverter.fromSelectToModel( dto.getVendorSelect() ) );
        }
        Long id = dtoFuelTypeSelectId( dto );
        if ( id != null ) {
            vehicleMake.setFuelType( fuelTypeConverter.fromValueToEnum( id ) );
        }
        if ( dto.getId() != null ) {
            vehicleMake.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            vehicleMake.setIsActive( dto.getIsActive() );
        }
        if ( dto.getName() != null ) {
            vehicleMake.setName( dto.getName() );
        }
        if ( dto.getCode() != null ) {
            vehicleMake.setCode( dto.getCode() );
        }
        if ( dto.getWeightCapacity() != null ) {
            vehicleMake.setWeightCapacity( dto.getWeightCapacity() );
        }
        if ( dto.getVolumeCapacity() != null ) {
            vehicleMake.setVolumeCapacity( dto.getVolumeCapacity() );
        }
        if ( dto.getConsignmentCapacity() != null ) {
            vehicleMake.setConsignmentCapacity( dto.getConsignmentCapacity() );
        }

        return vehicleMake;
    }

    @Override
    public void updateFromDto(VehicleMakeDto dto, VehicleMake vehicleMake) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getVendorSelect() != null ) {
            vehicleMake.setVendor( vendorConverter.fromSelectToModel( dto.getVendorSelect() ) );
        }
        else {
            vehicleMake.setVendor( null );
        }
        Long id = dtoFuelTypeSelectId( dto );
        if ( id != null ) {
            vehicleMake.setFuelType( fuelTypeConverter.fromValueToEnum( id ) );
        }
        else {
            vehicleMake.setFuelType( null );
        }
        if ( dto.getId() != null ) {
            vehicleMake.setId( dto.getId() );
        }
        else {
            vehicleMake.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            vehicleMake.setIsActive( dto.getIsActive() );
        }
        else {
            vehicleMake.setIsActive( null );
        }
        if ( dto.getName() != null ) {
            vehicleMake.setName( dto.getName() );
        }
        else {
            vehicleMake.setName( null );
        }
        if ( dto.getCode() != null ) {
            vehicleMake.setCode( dto.getCode() );
        }
        else {
            vehicleMake.setCode( null );
        }
        if ( dto.getWeightCapacity() != null ) {
            vehicleMake.setWeightCapacity( dto.getWeightCapacity() );
        }
        else {
            vehicleMake.setWeightCapacity( null );
        }
        if ( dto.getVolumeCapacity() != null ) {
            vehicleMake.setVolumeCapacity( dto.getVolumeCapacity() );
        }
        else {
            vehicleMake.setVolumeCapacity( null );
        }
        if ( dto.getConsignmentCapacity() != null ) {
            vehicleMake.setConsignmentCapacity( dto.getConsignmentCapacity() );
        }
        else {
            vehicleMake.setConsignmentCapacity( null );
        }
    }

    @Override
    public VehicleMakeDto fromModelToDto(VehicleMake vehicleMake) {
        if ( vehicleMake == null ) {
            return null;
        }

        VehicleMakeDto vehicleMakeDto = new VehicleMakeDto();

        if ( vehicleMake.getFuelType() != null ) {
            vehicleMakeDto.setFuelTypeSelect( fuelTypeConverter.fromEnumToSelect( vehicleMake.getFuelType() ) );
        }
        if ( vehicleMake.getVendor() != null ) {
            vehicleMakeDto.setVendorSelect( vendorConverter.fromModelToSelect( vehicleMake.getVendor() ) );
        }
        if ( vehicleMake.getId() != null ) {
            vehicleMakeDto.setId( vehicleMake.getId() );
        }
        if ( vehicleMake.getIsActive() != null ) {
            vehicleMakeDto.setIsActive( vehicleMake.getIsActive() );
        }
        if ( vehicleMake.getName() != null ) {
            vehicleMakeDto.setName( vehicleMake.getName() );
        }
        if ( vehicleMake.getCode() != null ) {
            vehicleMakeDto.setCode( vehicleMake.getCode() );
        }
        if ( vehicleMake.getWeightCapacity() != null ) {
            vehicleMakeDto.setWeightCapacity( vehicleMake.getWeightCapacity() );
        }
        if ( vehicleMake.getVolumeCapacity() != null ) {
            vehicleMakeDto.setVolumeCapacity( vehicleMake.getVolumeCapacity() );
        }
        if ( vehicleMake.getConsignmentCapacity() != null ) {
            vehicleMakeDto.setConsignmentCapacity( vehicleMake.getConsignmentCapacity() );
        }

        return vehicleMakeDto;
    }

    @Override
    public VehicleMakeDto fromExcelToDto(VehicleMakeExcelDto vehicleMake) {
        if ( vehicleMake == null ) {
            return null;
        }

        VehicleMakeDto vehicleMakeDto = new VehicleMakeDto();

        if ( vehicleMake.getIsActive() != null ) {
            vehicleMakeDto.setIsActive( vehicleMake.getIsActive() );
        }
        if ( vehicleMake.getName() != null ) {
            vehicleMakeDto.setName( vehicleMake.getName() );
        }
        if ( vehicleMake.getCode() != null ) {
            vehicleMakeDto.setCode( vehicleMake.getCode() );
        }
        if ( vehicleMake.getWeightCapacity() != null ) {
            vehicleMakeDto.setWeightCapacity( vehicleMake.getWeightCapacity() );
        }
        if ( vehicleMake.getVolumeCapacity() != null ) {
            vehicleMakeDto.setVolumeCapacity( vehicleMake.getVolumeCapacity() );
        }
        if ( vehicleMake.getConsignmentCapacity() != null ) {
            vehicleMakeDto.setConsignmentCapacity( vehicleMake.getConsignmentCapacity() );
        }

        return vehicleMakeDto;
    }

    @Override
    public SelectResponse vehicleMakeToSelect(VehicleMake v) {
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
    public VehicleMake selectToVehicleMake(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        VehicleMake vehicleMake = new VehicleMake();

        if ( select.getId() != null ) {
            vehicleMake.setId( select.getId() );
        }

        return vehicleMake;
    }

    private Long dtoFuelTypeSelectId(VehicleMakeDto vehicleMakeDto) {
        if ( vehicleMakeDto == null ) {
            return null;
        }
        SelectResponse fuelTypeSelect = vehicleMakeDto.getFuelTypeSelect();
        if ( fuelTypeSelect == null ) {
            return null;
        }
        Long id = fuelTypeSelect.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
