package com.boxi.transport.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.feign.DriverClient;
import com.boxi.feign.dto.DriverDto;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.enums.VehicleStatus;
import com.boxi.transport.enums.VehicleType;
import com.boxi.transport.payload.dto.VehicleDto;
import com.boxi.transport.payload.dto.VehicleExcelDto;
import com.boxi.transport.repo.VehicleRepository;
import com.boxi.utils.DateUtil;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {FleetTypeConverter.class, HubConverter.class, VehicleMakeConverter.class, VehicleCategoryConverter.class})
@DecoratedWith(VehicleSelectConverter.class)
public interface VehicleConverter {

    @Mapping(source = "dto.fleetTypeSelect.id", target = "fleetType")
    @Mapping(source = "dto.selectHub", target = "hub")
    @Mapping(source = "dto.vehicleMakeSelect", target = "vehicleMake")
    @Mapping(source = "dto.vehicleCategorySelect", target = "vehicleCategory")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "timeToStartWork", ignore = true)
    @Mapping(target = "timeToFinishWork", ignore = true)
    Vehicle fromDtoToModel(VehicleDto dto);


    @Mapping(source = "dto.fleetTypeSelect.id", target = "fleetType")
    @Mapping(source = "dto.selectHub", target = "hub")
    @Mapping(source = "dto.vehicleMakeSelect", target = "vehicleMake")
    @Mapping(source = "dto.vehicleCategorySelect", target = "vehicleCategory")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "timeToStartWork", ignore = true)
    @Mapping(target = "timeToFinishWork", ignore = true)
    void updateFromDto(VehicleDto dto, @MappingTarget Vehicle vehicle);

    @Mapping(source = "fleetType", target = "fleetTypeSelect")
    @Mapping(source = "hub", target = "selectHub")
    @Mapping(source = "vehicleMake", target = "vehicleMakeSelect")
    @Mapping(source = "vehicleCategory", target = "vehicleCategorySelect")
    @Mapping(target = "selectStatus", ignore = true)
    @Mapping(target = "timeToStartWork", ignore = true)
    @Mapping(target = "timeToFinishWork", ignore = true)
    VehicleDto fromModelToDto(Vehicle vehicle);


    @Mapping(ignore = true, target = "fleetTypeSelect")
    @Mapping(ignore = true, target = "selectHub")
    @Mapping(ignore = true, target = "vehicleMakeSelect")
    @Mapping(ignore = true, target = "vehicleCategorySelect")
    VehicleDto fromExcelToDto(VehicleExcelDto vehicle);

    SelectResponse fromModelToSelect(Vehicle v);

    Vehicle fromSelectToModel(SelectResponse select);


    @AfterMapping
    default void fromAfterDtoToModel(VehicleDto dto, @MappingTarget Vehicle vehicle) {
        if (dto.getSelectStatus() != null)
            vehicle.setStatus(VehicleStatus.findByValue(dto.getSelectStatus().getId()));

        if (dto.getFixedDriverId() != null)
            vehicle.setFixedDriverId(dto.getFixedDriverId());

        if (dto.getSelecttype() != null)
            vehicle.setType(VehicleType.findByValue(dto.getSelecttype().getId()));

        if (dto.getTimeToStartWork() != null) {
            DateDto dateDto = DateUtil.convertDateToJalaliDateDto(DateUtil.nowTimeStamp());
            vehicle.setTimeToStartWork(DateUtil.convertJalaliDayTimeToTimeStampWithTime(dateDto, Integer.valueOf(dto.getTimeToStartWork().split(":")[0])
                    , Integer.valueOf(dto.getTimeToStartWork().split(":")[1])));
        }

        if (dto.getTimeToFinishWork() != null) {
            DateDto dateDto = DateUtil.convertDateToJalaliDateDto(DateUtil.nowTimeStamp());
            vehicle.setTimeToFinishWork(DateUtil.convertJalaliDayTimeToTimeStampWithTime(dateDto, Integer.valueOf(dto.getTimeToFinishWork().split(":")[0])
                    , Integer.valueOf(dto.getTimeToFinishWork().split(":")[1])));
        }


    }

    @AfterMapping
    default void fromAfterModelToDto(Vehicle vehicle, @MappingTarget VehicleDto dto) {
        if (vehicle.getStatus() != null)
            dto.setSelectStatus(new SelectResponse(Objects.requireNonNull(VehicleStatus.findByValue(vehicle.getStatus().getValue())).getValue(),
                    Objects.requireNonNull(VehicleStatus.findByValue(vehicle.getStatus().getValue())).getFa()));
        if (vehicle.getType() != null) {
            dto.setSelecttype(new SelectResponse(vehicle.getType().getValue(), vehicle.getType().getFa()));
        }
        if (vehicle.getAllocatedVolume() != null)
            dto.setAllocatedVolume(vehicle.getAllocatedVolume());

        if (vehicle.getAllocatedWeight() != null)
            dto.setAllocatedWeight(vehicle.getAllocatedWeight());

        if (vehicle.getType() != null)
            dto.setSelecttype(new SelectResponse(vehicle.getType().getValue(), vehicle.getType().getFa()));

        if (vehicle.getTimeToStartWork() != null)
            dto.setTimeToStartWork(vehicle.getTimeToStartWork().toString().substring(11, 16));

        if (vehicle.getTimeToFinishWork() != null)
            dto.setTimeToFinishWork(vehicle.getTimeToFinishWork().toString().substring(11, 16));

        if (vehicle.getFirstDriverId() != null)
            dto.setFirstDriverId(vehicle.getFirstDriverId());

        if (vehicle.getFixedDriverId() != null)
            dto.setFixedDriverId(vehicle.getFixedDriverId());

    }
}

abstract class VehicleSelectConverter implements VehicleConverter {


    @Autowired
    private VehicleRepository repo;

    @Autowired
    private VehicleConverter vehicleConverter;
    @Autowired
    private DriverClient driverClient;

    @Override
    public SelectResponse fromModelToSelect(Vehicle v) {
        return new SelectResponse(v.getId(), v.selectToString());
    }

    @Override
    public Vehicle fromSelectToModel(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return repo.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.Vehicle, ExceptionType.ENTITY_NOT_FOUND);
        });
    }

    @Override
    public VehicleDto fromModelToDto(Vehicle vehicle) {
        VehicleDto vehicleDto = vehicleConverter.fromModelToDto(vehicle);

        if(vehicleDto != null) {
            if (vehicleDto != null && vehicleDto.getFixedDriverId() != null) {
                DriverDto driverDto = driverClient.findbyIdbyid(vehicleDto.getFixedDriverId());
                vehicleDto.setFixedDriverName(driverDto.getName());
            }
            if (vehicleDto != null && vehicleDto.getFirstDriverId() != null) {
                DriverDto driverDto = driverClient.findbyIdbyid(vehicleDto.getFirstDriverId());
                vehicleDto.setFirstDriverName(driverDto.getName());
            }
        }

        return vehicleDto;
    }

}