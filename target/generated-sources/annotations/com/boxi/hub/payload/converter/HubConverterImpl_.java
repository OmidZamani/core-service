package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubDto;
import com.boxi.hub.payload.dto.HubWithLocationDto;
import com.boxi.hub.payload.dto.ZoneHubDto;
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
public class HubConverterImpl_ implements HubConverter {

    @Autowired
    private HubCategoryConverter hubCategoryConverter;
    @Autowired
    private CountryDevisionConverter countryDevisionConverter;
    @Autowired
    private HubTypeConverter hubTypeConverter;

    @Override
    public Hub fromDtoToModel(HubDto dto) {
        if ( dto == null ) {
            return null;
        }

        Hub hub = new Hub();

        Long id = dtoSelectHubTypeId( dto );
        if ( id != null ) {
            hub.setType( hubTypeConverter.fromValueToEnum( id ) );
        }
        if ( dto.getSelectParentHub() != null ) {
            hub.setParentHub( selectToHub( dto.getSelectParentHub() ) );
        }
        if ( dto.getSelectHubCategory() != null ) {
            hub.setHubCategory( hubCategoryConverter.fromSelectToModel( dto.getSelectHubCategory() ) );
        }
        if ( dto.getSelectCity() != null ) {
            hub.setCity( countryDevisionConverter.fromSelectToModel( dto.getSelectCity() ) );
        }
        if ( dto.getId() != null ) {
            hub.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            hub.setIsActive( dto.getIsActive() );
        }
        if ( dto.getName() != null ) {
            hub.setName( dto.getName() );
        }
        if ( dto.getActiveFlag() != null ) {
            hub.setActiveFlag( dto.getActiveFlag() );
        }
        if ( dto.getLocLate() != null ) {
            hub.setLocLate( dto.getLocLate() );
        }
        if ( dto.getLocLong() != null ) {
            hub.setLocLong( dto.getLocLong() );
        }
        if ( dto.getAddressLine() != null ) {
            hub.setAddressLine( dto.getAddressLine() );
        }
        if ( dto.getAddressDetail() != null ) {
            hub.setAddressDetail( dto.getAddressDetail() );
        }
        if ( dto.getIsPickupPossible() != null ) {
            hub.setIsPickupPossible( dto.getIsPickupPossible() );
        }
        if ( dto.getIsPossibleConsignmentStorage() != null ) {
            hub.setIsPossibleConsignmentStorage( dto.getIsPossibleConsignmentStorage() );
        }
        if ( dto.getIsDeliveryPossible() != null ) {
            hub.setIsDeliveryPossible( dto.getIsDeliveryPossible() );
        }
        if ( dto.getIsPossibleOrderRegistration() != null ) {
            hub.setIsPossibleOrderRegistration( dto.getIsPossibleOrderRegistration() );
        }
        if ( dto.getIsArrivalScanPossible() != null ) {
            hub.setIsArrivalScanPossible( dto.getIsArrivalScanPossible() );
        }
        List<Hub> list = selectResponseListToHubList( dto.getHubs() );
        if ( list != null ) {
            hub.setHubs( list );
        }

        after( dto, hub );

        return hub;
    }

    @Override
    public ZoneHubDto fromHubToZone(Hub hub) {
        if ( hub == null ) {
            return null;
        }

        ZoneHubDto zoneHubDto = new ZoneHubDto();

        if ( hub.getId() != null ) {
            zoneHubDto.setHubId( hub.getId() );
        }
        if ( hub.getCode() != null ) {
            zoneHubDto.setHubCode( hub.getCode() );
        }
        if ( hub.getName() != null ) {
            zoneHubDto.setName( hub.getName() );
        }
        if ( hub.getLocLate() != null ) {
            zoneHubDto.setLocLate( hub.getLocLate() );
        }
        if ( hub.getLocLong() != null ) {
            zoneHubDto.setLocLong( hub.getLocLong() );
        }
        if ( hub.getIsActive() != null ) {
            zoneHubDto.setIsActive( hub.getIsActive() );
        }

        return zoneHubDto;
    }

    @Override
    public HubDto fromModelToDto(Hub hub) {
        if ( hub == null ) {
            return null;
        }

        HubDto hubDto = new HubDto();

        if ( hub != null ) {
            hubDto.setSelectHubType( hubToSelectResponse( hub ) );
        }
        if ( hub.getParentHub() != null ) {
            hubDto.setSelectParentHub( hubToSelect( hub.getParentHub() ) );
        }
        if ( hub.getHubCategory() != null ) {
            hubDto.setSelectHubCategory( hubCategoryConverter.fromModelToSelect( hub.getHubCategory() ) );
        }
        if ( hub.getCity() != null ) {
            hubDto.setSelectCity( countryDevisionConverter.fromModelToSelect( hub.getCity() ) );
        }
        if ( hub.getId() != null ) {
            hubDto.setId( hub.getId() );
        }
        if ( hub.getCode() != null ) {
            hubDto.setCode( hub.getCode() );
        }
        if ( hub.getName() != null ) {
            hubDto.setName( hub.getName() );
        }
        if ( hub.getAddressLine() != null ) {
            hubDto.setAddressLine( hub.getAddressLine() );
        }
        if ( hub.getAddressDetail() != null ) {
            hubDto.setAddressDetail( hub.getAddressDetail() );
        }
        if ( hub.getLocLate() != null ) {
            hubDto.setLocLate( hub.getLocLate() );
        }
        if ( hub.getLocLong() != null ) {
            hubDto.setLocLong( hub.getLocLong() );
        }
        if ( hub.getIsActive() != null ) {
            hubDto.setIsActive( hub.getIsActive() );
        }
        if ( hub.getIsPickupPossible() != null ) {
            hubDto.setIsPickupPossible( hub.getIsPickupPossible() );
        }
        if ( hub.getIsPossibleConsignmentStorage() != null ) {
            hubDto.setIsPossibleConsignmentStorage( hub.getIsPossibleConsignmentStorage() );
        }
        if ( hub.getIsDeliveryPossible() != null ) {
            hubDto.setIsDeliveryPossible( hub.getIsDeliveryPossible() );
        }
        if ( hub.getIsPossibleOrderRegistration() != null ) {
            hubDto.setIsPossibleOrderRegistration( hub.getIsPossibleOrderRegistration() );
        }
        if ( hub.getIsArrivalScanPossible() != null ) {
            hubDto.setIsArrivalScanPossible( hub.getIsArrivalScanPossible() );
        }
        List<SelectResponse> list = hubListToSelectResponseList( hub.getHubs() );
        if ( list != null ) {
            hubDto.setHubs( list );
        }
        if ( hub.getActiveFlag() != null ) {
            hubDto.setActiveFlag( hub.getActiveFlag() );
        }

        validate( hub, hubDto );

        return hubDto;
    }

    @Override
    public HubWithLocationDto fromModelToHubWithLocationDto(Hub hub) {
        if ( hub == null ) {
            return null;
        }

        HubWithLocationDto hubWithLocationDto = new HubWithLocationDto();

        if ( hub.getId() != null ) {
            hubWithLocationDto.setId( hub.getId() );
        }
        if ( hub.getName() != null ) {
            hubWithLocationDto.setName( hub.getName() );
        }
        if ( hub.getCode() != null ) {
            hubWithLocationDto.setCode( hub.getCode() );
        }
        if ( hub.getAddressLine() != null ) {
            hubWithLocationDto.setAddressLine( hub.getAddressLine() );
        }
        if ( hub.getLocLate() != null ) {
            hubWithLocationDto.setLocLate( hub.getLocLate() );
        }
        if ( hub.getLocLong() != null ) {
            hubWithLocationDto.setLocLong( hub.getLocLong() );
        }
        if ( hub.getAddressDetail() != null ) {
            hubWithLocationDto.setAddressDetail( hub.getAddressDetail() );
        }
        if ( hub.getIsPickupPossible() != null ) {
            hubWithLocationDto.setIsPickupPossible( hub.getIsPickupPossible() );
        }
        if ( hub.getIsPossibleConsignmentStorage() != null ) {
            hubWithLocationDto.setIsPossibleConsignmentStorage( hub.getIsPossibleConsignmentStorage() );
        }
        if ( hub.getIsDeliveryPossible() != null ) {
            hubWithLocationDto.setIsDeliveryPossible( hub.getIsDeliveryPossible() );
        }
        if ( hub.getIsPossibleOrderRegistration() != null ) {
            hubWithLocationDto.setIsPossibleOrderRegistration( hub.getIsPossibleOrderRegistration() );
        }
        if ( hub.getIsArrivalScanPossible() != null ) {
            hubWithLocationDto.setIsArrivalScanPossible( hub.getIsArrivalScanPossible() );
        }

        validatefromModelToHubWithLocationDto( hub, hubWithLocationDto );

        return hubWithLocationDto;
    }

    @Override
    public void updateFromDto(HubDto dto, Hub hub) {
        if ( dto == null ) {
            return;
        }

        Long id = dtoSelectHubTypeId( dto );
        if ( id != null ) {
            hub.setType( hubTypeConverter.fromValueToEnum( id ) );
        }
        else {
            hub.setType( null );
        }
        if ( dto.getSelectParentHub() != null ) {
            hub.setParentHub( selectToHub( dto.getSelectParentHub() ) );
        }
        else {
            hub.setParentHub( null );
        }
        if ( dto.getSelectHubCategory() != null ) {
            hub.setHubCategory( hubCategoryConverter.fromSelectToModel( dto.getSelectHubCategory() ) );
        }
        else {
            hub.setHubCategory( null );
        }
        if ( dto.getSelectCity() != null ) {
            hub.setCity( countryDevisionConverter.fromSelectToModel( dto.getSelectCity() ) );
        }
        else {
            hub.setCity( null );
        }
        if ( dto.getId() != null ) {
            hub.setId( dto.getId() );
        }
        else {
            hub.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            hub.setIsActive( dto.getIsActive() );
        }
        else {
            hub.setIsActive( null );
        }
        if ( dto.getName() != null ) {
            hub.setName( dto.getName() );
        }
        else {
            hub.setName( null );
        }
        if ( dto.getActiveFlag() != null ) {
            hub.setActiveFlag( dto.getActiveFlag() );
        }
        else {
            hub.setActiveFlag( null );
        }
        if ( dto.getLocLate() != null ) {
            hub.setLocLate( dto.getLocLate() );
        }
        else {
            hub.setLocLate( null );
        }
        if ( dto.getLocLong() != null ) {
            hub.setLocLong( dto.getLocLong() );
        }
        else {
            hub.setLocLong( null );
        }
        if ( dto.getAddressLine() != null ) {
            hub.setAddressLine( dto.getAddressLine() );
        }
        else {
            hub.setAddressLine( null );
        }
        if ( dto.getAddressDetail() != null ) {
            hub.setAddressDetail( dto.getAddressDetail() );
        }
        else {
            hub.setAddressDetail( null );
        }
        if ( dto.getIsPickupPossible() != null ) {
            hub.setIsPickupPossible( dto.getIsPickupPossible() );
        }
        else {
            hub.setIsPickupPossible( null );
        }
        if ( dto.getIsPossibleConsignmentStorage() != null ) {
            hub.setIsPossibleConsignmentStorage( dto.getIsPossibleConsignmentStorage() );
        }
        else {
            hub.setIsPossibleConsignmentStorage( null );
        }
        if ( dto.getIsDeliveryPossible() != null ) {
            hub.setIsDeliveryPossible( dto.getIsDeliveryPossible() );
        }
        else {
            hub.setIsDeliveryPossible( null );
        }
        if ( dto.getIsPossibleOrderRegistration() != null ) {
            hub.setIsPossibleOrderRegistration( dto.getIsPossibleOrderRegistration() );
        }
        else {
            hub.setIsPossibleOrderRegistration( null );
        }
        if ( dto.getIsArrivalScanPossible() != null ) {
            hub.setIsArrivalScanPossible( dto.getIsArrivalScanPossible() );
        }
        else {
            hub.setIsArrivalScanPossible( null );
        }
        if ( hub.getHubs() != null ) {
            List<Hub> list = selectResponseListToHubList( dto.getHubs() );
            if ( list != null ) {
                hub.getHubs().clear();
                hub.getHubs().addAll( list );
            }
        }
        else {
            List<Hub> list = selectResponseListToHubList( dto.getHubs() );
            if ( list != null ) {
                hub.setHubs( list );
            }
        }

        after( dto, hub );
    }

    @Override
    public SelectResponse hubToSelect(Hub h) {
        if ( h == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( h.getId() != null ) {
            selectResponse.setId( h.getId() );
        }

        return selectResponse;
    }

    @Override
    public Hub selectToHub(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        Hub hub = new Hub();

        if ( select.getId() != null ) {
            hub.setId( select.getId() );
        }

        return hub;
    }

    private Long dtoSelectHubTypeId(HubDto hubDto) {
        if ( hubDto == null ) {
            return null;
        }
        SelectResponse selectHubType = hubDto.getSelectHubType();
        if ( selectHubType == null ) {
            return null;
        }
        Long id = selectHubType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<Hub> selectResponseListToHubList(List<SelectResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<Hub> list1 = new ArrayList<Hub>( list.size() );
        for ( SelectResponse selectResponse : list ) {
            list1.add( selectToHub( selectResponse ) );
        }

        return list1;
    }

    protected SelectResponse hubToSelectResponse(Hub hub) {
        if ( hub == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( hub.getType() != null ) {
            selectResponse.setId( hubTypeConverter.fromEnumToValue( hub.getType() ) );
        }

        return selectResponse;
    }

    protected List<SelectResponse> hubListToSelectResponseList(List<Hub> list) {
        if ( list == null ) {
            return null;
        }

        List<SelectResponse> list1 = new ArrayList<SelectResponse>( list.size() );
        for ( Hub hub : list ) {
            list1.add( hubToSelect( hub ) );
        }

        return list1;
    }
}
