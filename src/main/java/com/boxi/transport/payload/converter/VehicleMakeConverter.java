package com.boxi.transport.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleMake;
import com.boxi.transport.payload.dto.VehicleMakeDto;
import com.boxi.transport.payload.dto.VehicleMakeExcelDto;
import com.boxi.transport.repo.VehicleMakeRepository;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {VehicleCategoryConverter.class, VendorConverter.class, FuelTypeConverter.class})
@DecoratedWith(VehicleMakeSelectConverter.class)
public interface VehicleMakeConverter {


    @Mapping(source = "dto.vendorSelect", target = "vendor")
    @Mapping(source = "dto.fuelTypeSelect.id", target = "fuelType")
    VehicleMake fromDtoToModel(VehicleMakeDto dto);

    @Mapping(source = "dto.vendorSelect", target = "vendor")
    @Mapping(source = "dto.fuelTypeSelect.id", target = "fuelType")
    void updateFromDto(VehicleMakeDto dto, @MappingTarget VehicleMake vehicleMake);

    @Mapping(source = "fuelType", target = "fuelTypeSelect")
    @Mapping(source = "vendor", target = "vendorSelect")
    VehicleMakeDto fromModelToDto(VehicleMake vehicleMake);

    @Mapping(ignore = true, target = "fuelTypeSelect")
    @Mapping(ignore = true, target = "vendorSelect")
    VehicleMakeDto fromExcelToDto(VehicleMakeExcelDto vehicleMake);

    SelectResponse vehicleMakeToSelect(VehicleMake v) ;

    VehicleMake selectToVehicleMake(SelectResponse select) ;

}


abstract class VehicleMakeSelectConverter implements VehicleMakeConverter {
    @Autowired
    VehicleMakeRepository vehicleMakeRepository;

    @Override
    public SelectResponse vehicleMakeToSelect(VehicleMake v) {
        return new SelectResponse(v.getId(), v.selectToString());
    }

    @Override
    public VehicleMake selectToVehicleMake(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return vehicleMakeRepository.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.VehicleMake, ExceptionType.ENTITY_NOT_FOUND);
        });
    }


}

