package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.payload.dto.AdmVehicleDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-05T14:04:32+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class AdmVehicleConverterImpl_ implements AdmVehicleConverter {

    private final HubConverter hubConverter;
    private final VehicleMakeConverter vehicleMakeConverter;
    private final RouteConverter routeConverter;

    @Autowired
    public AdmVehicleConverterImpl_(HubConverter hubConverter, VehicleMakeConverter vehicleMakeConverter, RouteConverter routeConverter) {

        this.hubConverter = hubConverter;
        this.vehicleMakeConverter = vehicleMakeConverter;
        this.routeConverter = routeConverter;
    }

    @Override
    public Vehicle fromDtoToModel(AdmVehicleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        if ( dto.getSelectHub() != null ) {
            vehicle.setHub( hubConverter.selectToHub( dto.getSelectHub() ) );
        }
        if ( dto.getVehicleMakeSelect() != null ) {
            vehicle.setVehicleMake( vehicleMakeConverter.selectToVehicleMake( dto.getVehicleMakeSelect() ) );
        }
        if ( dto.getSelectRoute() != null ) {
            vehicle.setRoute( routeConverter.fromSelectToModel( dto.getSelectRoute() ) );
        }
        if ( dto.getId() != null ) {
            vehicle.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            vehicle.setIsActive( dto.getIsActive() );
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
        if ( dto.getWeightCapacity() != null ) {
            vehicle.setWeightCapacity( dto.getWeightCapacity() );
        }
        if ( dto.getVolumeCapacity() != null ) {
            vehicle.setVolumeCapacity( dto.getVolumeCapacity() );
        }

        afterMapToModel( dto, vehicle );

        return vehicle;
    }

    @Override
    public void updateFromDto(AdmVehicleDto dto, Vehicle vehicle) {
        if ( dto == null ) {
            return;
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
        if ( dto.getSelectRoute() != null ) {
            vehicle.setRoute( routeConverter.fromSelectToModel( dto.getSelectRoute() ) );
        }
        else {
            vehicle.setRoute( null );
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

        afterMapToModel( dto, vehicle );
    }

    @Override
    public AdmVehicleDto fromModelToDto(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        AdmVehicleDto admVehicleDto = new AdmVehicleDto();

        if ( vehicle.getHub() != null ) {
            admVehicleDto.setSelectHub( hubConverter.hubToSelect( vehicle.getHub() ) );
        }
        if ( vehicle.getVehicleMake() != null ) {
            admVehicleDto.setVehicleMakeSelect( vehicleMakeConverter.vehicleMakeToSelect( vehicle.getVehicleMake() ) );
        }
        if ( vehicle.getRoute() != null ) {
            admVehicleDto.setSelectRoute( routeConverter.fromModelToSelect( vehicle.getRoute() ) );
        }
        if ( vehicle.getId() != null ) {
            admVehicleDto.setId( vehicle.getId() );
        }
        if ( vehicle.getVehicleNumber0() != null ) {
            admVehicleDto.setVehicleNumber0( vehicle.getVehicleNumber0() );
        }
        if ( vehicle.getVehicleNumber1() != null ) {
            admVehicleDto.setVehicleNumber1( vehicle.getVehicleNumber1() );
        }
        if ( vehicle.getVehicleNumber2() != null ) {
            admVehicleDto.setVehicleNumber2( vehicle.getVehicleNumber2() );
        }
        if ( vehicle.getVehicleNumber3() != null ) {
            admVehicleDto.setVehicleNumber3( vehicle.getVehicleNumber3() );
        }
        if ( vehicle.getVolumeCapacity() != null ) {
            admVehicleDto.setVolumeCapacity( vehicle.getVolumeCapacity() );
        }
        if ( vehicle.getWeightCapacity() != null ) {
            admVehicleDto.setWeightCapacity( vehicle.getWeightCapacity() );
        }
        if ( vehicle.getIsActive() != null ) {
            admVehicleDto.setIsActive( vehicle.getIsActive() );
        }

        afterMapToDto( vehicle, admVehicleDto );

        return admVehicleDto;
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
}
