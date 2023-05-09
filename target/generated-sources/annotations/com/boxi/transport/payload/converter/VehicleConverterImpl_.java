package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.payload.dto.VehicleDto;
import com.boxi.transport.payload.dto.VehicleExcelDto;
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
public class VehicleConverterImpl_ implements VehicleConverter {

    private final FleetTypeConverter fleetTypeConverter;
    private final HubConverter hubConverter;
    private final VehicleMakeConverter vehicleMakeConverter;
    private final VehicleCategoryConverter vehicleCategoryConverter;

    @Autowired
    public VehicleConverterImpl_(FleetTypeConverter fleetTypeConverter, HubConverter hubConverter, VehicleMakeConverter vehicleMakeConverter, VehicleCategoryConverter vehicleCategoryConverter) {

        this.fleetTypeConverter = fleetTypeConverter;
        this.hubConverter = hubConverter;
        this.vehicleMakeConverter = vehicleMakeConverter;
        this.vehicleCategoryConverter = vehicleCategoryConverter;
    }

    @Override
    public Vehicle fromDtoToModel(VehicleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        Long id = dtoFleetTypeSelectId( dto );
        if ( id != null ) {
            vehicle.setFleetType( fleetTypeConverter.fromValueToEnum( id ) );
        }
        if ( dto.getSelectHub() != null ) {
            vehicle.setHub( hubConverter.selectToHub( dto.getSelectHub() ) );
        }
        if ( dto.getVehicleMakeSelect() != null ) {
            vehicle.setVehicleMake( vehicleMakeConverter.selectToVehicleMake( dto.getVehicleMakeSelect() ) );
        }
        if ( dto.getVehicleCategorySelect() != null ) {
            vehicle.setVehicleCategory( vehicleCategoryConverter.fromSelectToModel( dto.getVehicleCategorySelect() ) );
        }
        if ( dto.getIsActive() != null ) {
            vehicle.setIsActive( dto.getIsActive() );
        }
        if ( dto.getId() != null ) {
            vehicle.setId( dto.getId() );
        }
        if ( dto.getVehicleNumber0() != null ) {
            vehicle.setVehicleNumber0( dto.getVehicleNumber0() );
        }
        if ( dto.getVehicleNumber1() != null ) {
            vehicle.setVehicleNumber1( dto.getVehicleNumber1() );
        }
        if ( dto.getVehicleNumber2() != null ) {
            vehicle.setVehicleNumber2( dto.getVehicleNumber2() );
        }
        if ( dto.getVehicleNumber3() != null ) {
            vehicle.setVehicleNumber3( dto.getVehicleNumber3() );
        }
        if ( dto.getAvailableForLocalTrips() != null ) {
            vehicle.setAvailableForLocalTrips( dto.getAvailableForLocalTrips() );
        }
        if ( dto.getAvailableForUpCountry() != null ) {
            vehicle.setAvailableForUpCountry( dto.getAvailableForUpCountry() );
        }
        if ( dto.getAvailableForMidMile() != null ) {
            vehicle.setAvailableForMidMile( dto.getAvailableForMidMile() );
        }
        if ( dto.getWeightCapacity() != null ) {
            vehicle.setWeightCapacity( dto.getWeightCapacity() );
        }
        if ( dto.getVolumeCapacity() != null ) {
            vehicle.setVolumeCapacity( dto.getVolumeCapacity() );
        }
        if ( dto.getIsOwnerShip() != null ) {
            vehicle.setIsOwnerShip( dto.getIsOwnerShip() );
        }
        if ( dto.getAllocatedWeight() != null ) {
            vehicle.setAllocatedWeight( dto.getAllocatedWeight() );
        }
        if ( dto.getAllocatedVolume() != null ) {
            vehicle.setAllocatedVolume( dto.getAllocatedVolume() );
        }

        fromAfterDtoToModel( dto, vehicle );

        return vehicle;
    }

    @Override
    public void updateFromDto(VehicleDto dto, Vehicle vehicle) {
        if ( dto == null ) {
            return;
        }

        Long id = dtoFleetTypeSelectId( dto );
        if ( id != null ) {
            vehicle.setFleetType( fleetTypeConverter.fromValueToEnum( id ) );
        }
        else {
            vehicle.setFleetType( null );
        }
        if ( dto.getSelectHub() != null ) {
            vehicle.setHub( hubConverter.selectToHub( dto.getSelectHub() ) );
        }
        else {
            vehicle.setHub( null );
        }
        if ( dto.getVehicleMakeSelect() != null ) {
            vehicle.setVehicleMake( vehicleMakeConverter.selectToVehicleMake( dto.getVehicleMakeSelect() ) );
        }
        else {
            vehicle.setVehicleMake( null );
        }
        if ( dto.getVehicleCategorySelect() != null ) {
            vehicle.setVehicleCategory( vehicleCategoryConverter.fromSelectToModel( dto.getVehicleCategorySelect() ) );
        }
        else {
            vehicle.setVehicleCategory( null );
        }
        if ( dto.getId() != null ) {
            vehicle.setId( dto.getId() );
        }
        else {
            vehicle.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            vehicle.setIsActive( dto.getIsActive() );
        }
        else {
            vehicle.setIsActive( null );
        }
        if ( dto.getVehicleNumber0() != null ) {
            vehicle.setVehicleNumber0( dto.getVehicleNumber0() );
        }
        else {
            vehicle.setVehicleNumber0( null );
        }
        if ( dto.getVehicleNumber1() != null ) {
            vehicle.setVehicleNumber1( dto.getVehicleNumber1() );
        }
        else {
            vehicle.setVehicleNumber1( null );
        }
        if ( dto.getVehicleNumber2() != null ) {
            vehicle.setVehicleNumber2( dto.getVehicleNumber2() );
        }
        else {
            vehicle.setVehicleNumber2( null );
        }
        if ( dto.getVehicleNumber3() != null ) {
            vehicle.setVehicleNumber3( dto.getVehicleNumber3() );
        }
        else {
            vehicle.setVehicleNumber3( null );
        }
        if ( dto.getAvailableForLocalTrips() != null ) {
            vehicle.setAvailableForLocalTrips( dto.getAvailableForLocalTrips() );
        }
        else {
            vehicle.setAvailableForLocalTrips( null );
        }
        if ( dto.getAvailableForUpCountry() != null ) {
            vehicle.setAvailableForUpCountry( dto.getAvailableForUpCountry() );
        }
        else {
            vehicle.setAvailableForUpCountry( null );
        }
        if ( dto.getAvailableForMidMile() != null ) {
            vehicle.setAvailableForMidMile( dto.getAvailableForMidMile() );
        }
        else {
            vehicle.setAvailableForMidMile( null );
        }
        if ( dto.getWeightCapacity() != null ) {
            vehicle.setWeightCapacity( dto.getWeightCapacity() );
        }
        else {
            vehicle.setWeightCapacity( null );
        }
        if ( dto.getVolumeCapacity() != null ) {
            vehicle.setVolumeCapacity( dto.getVolumeCapacity() );
        }
        else {
            vehicle.setVolumeCapacity( null );
        }
        if ( dto.getIsOwnerShip() != null ) {
            vehicle.setIsOwnerShip( dto.getIsOwnerShip() );
        }
        else {
            vehicle.setIsOwnerShip( null );
        }
        if ( dto.getAllocatedWeight() != null ) {
            vehicle.setAllocatedWeight( dto.getAllocatedWeight() );
        }
        else {
            vehicle.setAllocatedWeight( null );
        }
        if ( dto.getAllocatedVolume() != null ) {
            vehicle.setAllocatedVolume( dto.getAllocatedVolume() );
        }
        else {
            vehicle.setAllocatedVolume( null );
        }

        fromAfterDtoToModel( dto, vehicle );
    }

    @Override
    public VehicleDto fromModelToDto(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleDto vehicleDto = new VehicleDto();

        if ( vehicle.getFleetType() != null ) {
            vehicleDto.setFleetTypeSelect( fleetTypeConverter.fromEnumToSelect( vehicle.getFleetType() ) );
        }
        if ( vehicle.getHub() != null ) {
            vehicleDto.setSelectHub( hubConverter.hubToSelect( vehicle.getHub() ) );
        }
        if ( vehicle.getVehicleMake() != null ) {
            vehicleDto.setVehicleMakeSelect( vehicleMakeConverter.vehicleMakeToSelect( vehicle.getVehicleMake() ) );
        }
        if ( vehicle.getVehicleCategory() != null ) {
            vehicleDto.setVehicleCategorySelect( vehicleCategoryConverter.fromModelToSelect( vehicle.getVehicleCategory() ) );
        }
        if ( vehicle.getId() != null ) {
            vehicleDto.setId( vehicle.getId() );
        }
        if ( vehicle.getVehicleNumber0() != null ) {
            vehicleDto.setVehicleNumber0( vehicle.getVehicleNumber0() );
        }
        if ( vehicle.getVehicleNumber1() != null ) {
            vehicleDto.setVehicleNumber1( vehicle.getVehicleNumber1() );
        }
        if ( vehicle.getVehicleNumber2() != null ) {
            vehicleDto.setVehicleNumber2( vehicle.getVehicleNumber2() );
        }
        if ( vehicle.getVehicleNumber3() != null ) {
            vehicleDto.setVehicleNumber3( vehicle.getVehicleNumber3() );
        }
        if ( vehicle.getVolumeCapacity() != null ) {
            vehicleDto.setVolumeCapacity( vehicle.getVolumeCapacity() );
        }
        if ( vehicle.getWeightCapacity() != null ) {
            vehicleDto.setWeightCapacity( vehicle.getWeightCapacity() );
        }
        if ( vehicle.getAvailableForLocalTrips() != null ) {
            vehicleDto.setAvailableForLocalTrips( vehicle.getAvailableForLocalTrips() );
        }
        if ( vehicle.getAvailableForUpCountry() != null ) {
            vehicleDto.setAvailableForUpCountry( vehicle.getAvailableForUpCountry() );
        }
        if ( vehicle.getAvailableForMidMile() != null ) {
            vehicleDto.setAvailableForMidMile( vehicle.getAvailableForMidMile() );
        }
        if ( vehicle.getIsActive() != null ) {
            vehicleDto.setIsActive( vehicle.getIsActive() );
        }
        if ( vehicle.getIsOwnerShip() != null ) {
            vehicleDto.setIsOwnerShip( vehicle.getIsOwnerShip() );
        }
        if ( vehicle.getAllocatedWeight() != null ) {
            vehicleDto.setAllocatedWeight( vehicle.getAllocatedWeight() );
        }
        if ( vehicle.getAllocatedVolume() != null ) {
            vehicleDto.setAllocatedVolume( vehicle.getAllocatedVolume() );
        }

        fromAfterModelToDto( vehicle, vehicleDto );

        return vehicleDto;
    }

    @Override
    public VehicleDto fromExcelToDto(VehicleExcelDto vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleDto vehicleDto = new VehicleDto();

        if ( vehicle.getVehicleNumber0() != null ) {
            vehicleDto.setVehicleNumber0( vehicle.getVehicleNumber0() );
        }
        if ( vehicle.getVehicleNumber1() != null ) {
            vehicleDto.setVehicleNumber1( vehicle.getVehicleNumber1() );
        }
        if ( vehicle.getVehicleNumber2() != null ) {
            vehicleDto.setVehicleNumber2( vehicle.getVehicleNumber2() );
        }
        if ( vehicle.getVehicleNumber3() != null ) {
            vehicleDto.setVehicleNumber3( vehicle.getVehicleNumber3() );
        }
        if ( vehicle.getVolumeCapacity() != null ) {
            vehicleDto.setVolumeCapacity( vehicle.getVolumeCapacity() );
        }
        if ( vehicle.getWeightCapacity() != null ) {
            vehicleDto.setWeightCapacity( vehicle.getWeightCapacity() );
        }
        if ( vehicle.getAvailableForLocalTrips() != null ) {
            vehicleDto.setAvailableForLocalTrips( vehicle.getAvailableForLocalTrips() );
        }
        if ( vehicle.getAvailableForUpCountry() != null ) {
            vehicleDto.setAvailableForUpCountry( vehicle.getAvailableForUpCountry() );
        }
        if ( vehicle.getAvailableForMidMile() != null ) {
            vehicleDto.setAvailableForMidMile( vehicle.getAvailableForMidMile() );
        }
        if ( vehicle.getIsActive() != null ) {
            vehicleDto.setIsActive( vehicle.getIsActive() );
        }

        return vehicleDto;
    }

    @Override
    public SelectResponse fromModelToSelect(Vehicle v) {
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
    public Vehicle fromSelectToModel(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        if ( select.getId() != null ) {
            vehicle.setId( select.getId() );
        }

        return vehicle;
    }

    private Long dtoFleetTypeSelectId(VehicleDto vehicleDto) {
        if ( vehicleDto == null ) {
            return null;
        }
        SelectResponse fleetTypeSelect = vehicleDto.getFleetTypeSelect();
        if ( fleetTypeSelect == null ) {
            return null;
        }
        Long id = fleetTypeSelect.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
