package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Bag;
import com.boxi.transport.enums.BagStatus;
import com.boxi.transport.payload.dto.BagDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class BagConverterImpl implements BagConverter {

    private final HubConverter hubConverter;
    private final BagTypeConverter bagTypeConverter;
    private final VehicleConverter vehicleConverter;

    @Autowired
    public BagConverterImpl(HubConverter hubConverter, BagTypeConverter bagTypeConverter, VehicleConverter vehicleConverter) {

        this.hubConverter = hubConverter;
        this.bagTypeConverter = bagTypeConverter;
        this.vehicleConverter = vehicleConverter;
    }

    @Override
    public Bag fromDtoToModel(BagDto dto) {
        if ( dto == null ) {
            return null;
        }

        Bag bag = new Bag();

        if ( dto.getSelectSourceHub() != null ) {
            bag.setSourceHub( hubConverter.selectToHub( dto.getSelectSourceHub() ) );
        }
        if ( dto.getSelectConsignmentsDestinationHub() != null ) {
            bag.setConsignmentsDestinationHub( hubConverter.selectToHub( dto.getSelectConsignmentsDestinationHub() ) );
        }
        if ( dto.getSelectDestinationHub() != null ) {
            bag.setDestinationHub( hubConverter.selectToHub( dto.getSelectDestinationHub() ) );
        }
        if ( dto.getSelectOwnerHub() != null ) {
            bag.setOwnerHub( hubConverter.selectToHub( dto.getSelectOwnerHub() ) );
        }
        Long id = dtoSelectBagTypeId( dto );
        if ( id != null ) {
            bag.setBagType( bagTypeConverter.fromValueToEnum( id ) );
        }
        if ( dto.getSelectCarrier() != null ) {
            bag.setCarrier( vehicleConverter.fromSelectToModel( dto.getSelectCarrier() ) );
        }
        if ( dto.getId() != null ) {
            bag.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            bag.setIsActive( dto.getIsActive() );
        }
        if ( dto.getBagNumber() != null ) {
            bag.setBagNumber( dto.getBagNumber() );
        }
        if ( dto.getWeightCapacity() != null ) {
            bag.setWeightCapacity( dto.getWeightCapacity() );
        }
        if ( dto.getWeight() != null ) {
            bag.setWeight( dto.getWeight() );
        }

        afterfromDtoToModel( dto, bag );

        return bag;
    }

    @Override
    public void updateFromDto(BagDto dto, Bag bag) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getSelectSourceHub() != null ) {
            bag.setSourceHub( hubConverter.selectToHub( dto.getSelectSourceHub() ) );
        }
        else {
            bag.setSourceHub( null );
        }
        if ( dto.getSelectConsignmentsDestinationHub() != null ) {
            bag.setConsignmentsDestinationHub( hubConverter.selectToHub( dto.getSelectConsignmentsDestinationHub() ) );
        }
        else {
            bag.setConsignmentsDestinationHub( null );
        }
        if ( dto.getSelectDestinationHub() != null ) {
            bag.setDestinationHub( hubConverter.selectToHub( dto.getSelectDestinationHub() ) );
        }
        else {
            bag.setDestinationHub( null );
        }
        if ( dto.getSelectOwnerHub() != null ) {
            bag.setOwnerHub( hubConverter.selectToHub( dto.getSelectOwnerHub() ) );
        }
        else {
            bag.setOwnerHub( null );
        }
        Long id = dtoSelectBagTypeId( dto );
        if ( id != null ) {
            bag.setBagType( bagTypeConverter.fromValueToEnum( id ) );
        }
        else {
            bag.setBagType( null );
        }
        if ( dto.getSelectCarrier() != null ) {
            bag.setCarrier( vehicleConverter.fromSelectToModel( dto.getSelectCarrier() ) );
        }
        else {
            bag.setCarrier( null );
        }
        if ( dto.getId() != null ) {
            bag.setId( dto.getId() );
        }
        else {
            bag.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            bag.setIsActive( dto.getIsActive() );
        }
        else {
            bag.setIsActive( null );
        }
        if ( dto.getBagNumber() != null ) {
            bag.setBagNumber( dto.getBagNumber() );
        }
        else {
            bag.setBagNumber( null );
        }
        if ( dto.getWeightCapacity() != null ) {
            bag.setWeightCapacity( dto.getWeightCapacity() );
        }
        else {
            bag.setWeightCapacity( null );
        }
        if ( dto.getWeight() != null ) {
            bag.setWeight( dto.getWeight() );
        }
        else {
            bag.setWeight( null );
        }

        afterfromDtoToModel( dto, bag );
    }

    @Override
    public BagDto fromModelToDto(Bag bag) {
        if ( bag == null ) {
            return null;
        }

        BagDto bagDto = new BagDto();

        if ( bag.getSourceHub() != null ) {
            bagDto.setSelectSourceHub( hubConverter.hubToSelect( bag.getSourceHub() ) );
        }
        if ( bag.getConsignmentsDestinationHub() != null ) {
            bagDto.setSelectConsignmentsDestinationHub( hubConverter.hubToSelect( bag.getConsignmentsDestinationHub() ) );
        }
        if ( bag.getDestinationHub() != null ) {
            bagDto.setSelectDestinationHub( hubConverter.hubToSelect( bag.getDestinationHub() ) );
        }
        if ( bag.getOwnerHub() != null ) {
            bagDto.setSelectOwnerHub( hubConverter.hubToSelect( bag.getOwnerHub() ) );
        }
        if ( bag.getCurrentHub() != null ) {
            bagDto.setSelectCurrentHub( hubConverter.hubToSelect( bag.getCurrentHub() ) );
        }
        if ( bag.getBagType() != null ) {
            bagDto.setSelectBagType( bagTypeConverter.fromEnumToSelect( bag.getBagType() ) );
        }
        if ( bag.getCarrier() != null ) {
            bagDto.setSelectCarrier( vehicleConverter.fromModelToSelect( bag.getCarrier() ) );
        }
        if ( bag.getId() != null ) {
            bagDto.setId( bag.getId() );
        }
        if ( bag.getBagNumber() != null ) {
            bagDto.setBagNumber( bag.getBagNumber() );
        }
        if ( bag.getIsActive() != null ) {
            bagDto.setIsActive( bag.getIsActive() );
        }
        if ( bag.getStatus() != null ) {
            bagDto.setStatus( bagStatusToSelectResponse( bag.getStatus() ) );
        }
        if ( bag.getWeightCapacity() != null ) {
            bagDto.setWeightCapacity( bag.getWeightCapacity() );
        }
        if ( bag.getWeight() != null ) {
            bagDto.setWeight( bag.getWeight() );
        }

        afterffromModelToDto( bag, bagDto );

        return bagDto;
    }

    private Long dtoSelectBagTypeId(BagDto bagDto) {
        if ( bagDto == null ) {
            return null;
        }
        SelectResponse selectBagType = bagDto.getSelectBagType();
        if ( selectBagType == null ) {
            return null;
        }
        Long id = selectBagType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected SelectResponse bagStatusToSelectResponse(BagStatus bagStatus) {
        if ( bagStatus == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        return selectResponse;
    }
}
