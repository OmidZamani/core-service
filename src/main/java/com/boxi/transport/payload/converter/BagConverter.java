package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Bag;
import com.boxi.transport.enums.BagStatus;
import com.boxi.transport.payload.dto.BagDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {HubConverter.class, BagTypeConverter.class, VehicleConverter.class})
public interface BagConverter {

    @Mapping(source = "dto.selectSourceHub", target = "sourceHub")
    @Mapping(source = "dto.selectConsignmentsDestinationHub", target = "consignmentsDestinationHub")
    @Mapping(source = "dto.selectDestinationHub", target = "destinationHub")
    @Mapping(source = "dto.selectOwnerHub", target = "ownerHub")
    @Mapping(source = "dto.selectBagType.id", target = "bagType")
    @Mapping(source = "dto.selectCarrier", target = "carrier")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "tripId", ignore = true)
    Bag fromDtoToModel(BagDto dto);

    @Mapping(source = "dto.selectSourceHub", target = "sourceHub")
    @Mapping(source = "dto.selectConsignmentsDestinationHub", target = "consignmentsDestinationHub")
    @Mapping(source = "dto.selectDestinationHub", target = "destinationHub")
    @Mapping(source = "dto.selectOwnerHub", target = "ownerHub")
    @Mapping(source = "dto.selectBagType.id", target = "bagType")
    @Mapping(source = "dto.selectCarrier", target = "carrier")
    @Mapping(source = "dto.selectCurrentHub", target = "currentHub")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "tripId", ignore = true)
    void updateFromDto(BagDto dto, @MappingTarget Bag bag);

    @Mapping(source = "sourceHub", target = "selectSourceHub")
    @Mapping(source = "consignmentsDestinationHub", target = "selectConsignmentsDestinationHub")
    @Mapping(source = "destinationHub", target = "selectDestinationHub")
    @Mapping(source = "ownerHub.", target = "selectOwnerHub")
    @Mapping(source = "currentHub.", target = "selectCurrentHub")
    @Mapping(source = "bagType", target = "selectBagType")
    @Mapping(source = "carrier", target = "selectCarrier")
    BagDto fromModelToDto(Bag bag);

    @AfterMapping
    default void afterfromDtoToModel(BagDto BagDto, @MappingTarget Bag bag) {
        if (BagDto.getStatus() != null) {
            bag.setStatus(BagStatus.findByValue(BagDto.getStatus().getId()));
        }

        if (BagDto.getSelecttrip() != null) {
            bag.setTripId(BagDto.getSelecttrip().getId());
        }
    }

    @AfterMapping
    default void afterffromModelToDto(Bag bag, @MappingTarget BagDto BagDto) {
        if (bag.getStatus() != null) {

            BagDto.setStatus(new SelectResponse(bag.getStatus().getValue(), bag.getStatus().getFa()));
        }

        if (bag.getTripId() != null) {
            BagDto.setSelecttrip(new SelectResponse(bag.getTripId(), ""));
        }
    }

}
