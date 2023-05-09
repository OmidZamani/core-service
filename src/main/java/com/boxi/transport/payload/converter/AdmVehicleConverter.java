package com.boxi.transport.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.payload.dto.AdmVehicleDto;
import com.boxi.transport.repo.VehicleRepository;
import com.boxi.utils.DateUtil;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {FleetTypeConverter.class, HubConverter.class, VehicleMakeConverter.class,RouteConverter.class, VehicleCategoryConverter.class})
@DecoratedWith(AdmVehicleSelectConverter.class)
public interface AdmVehicleConverter {


    @Mapping(source = "dto.selectHub", target = "hub")
    @Mapping(source = "dto.vehicleMakeSelect", target = "vehicleMake")
    @Mapping(target = "timeToStartWork", ignore = true)
    @Mapping(target = "timeToFinishWork", ignore = true)
    @Mapping(source = "selectRoute",target = "route")
    Vehicle fromDtoToModel(AdmVehicleDto dto);


    @Mapping(source = "dto.selectHub", target = "hub")
    @Mapping(source = "dto.vehicleMakeSelect", target = "vehicleMake")
    @Mapping(target = "timeToStartWork", ignore = true)
    @Mapping(target = "timeToFinishWork", ignore = true)
    @Mapping(source = "selectRoute",target = "route")
    void updateFromDto(AdmVehicleDto dto, @MappingTarget Vehicle vehicle);

    @Mapping(source = "hub", target = "selectHub")
    @Mapping(source = "vehicleMake", target = "vehicleMakeSelect")
    @Mapping(target = "timeToStartWork", ignore = true)
    @Mapping(target = "timeToFinishWork", ignore = true)
    @Mapping(source = "route",target = "selectRoute")
    AdmVehicleDto fromModelToDto(Vehicle vehicle);


    @AfterMapping
    default void afterMapToModel(AdmVehicleDto dto, @MappingTarget Vehicle vehicle) {

        if (dto.getDayToFinishWork() != null && dto.getTimeToFinishWork() != null) {
            DateDto day = dto.getDayToFinishWork();
            String time = dto.getTimeToFinishWork();
            String[] tparts = time.split(":");
            vehicle.setTimeToFinishWork(DateUtil.convertJalaliDayTimeToTimeStampWithTime(day,Integer.valueOf(tparts[0]),Integer.valueOf(tparts[1])));
        }

        if (dto.getDayToStartWork() != null && dto.getTimeToStartWork() != null) {
            DateDto day = dto.getDayToStartWork();
            String time = dto.getTimeToStartWork();
            String[] tparts = time.split(":");
            vehicle.setTimeToStartWork(DateUtil.convertJalaliDayTimeToTimeStampWithTime(day,Integer.valueOf(tparts[0]),Integer.valueOf(tparts[1])));
        }

    }


    @AfterMapping
    default void afterMapToDto(Vehicle vehicle, @MappingTarget AdmVehicleDto dto) {

        if (vehicle.getTimeToFinishWork() != null) { //TODO test it new date utils
            dto.setDayToFinishWork(DateUtil.convertTimeStampStringToJalaliObject(vehicle.getTimeToFinishWork()));
            String hm = vehicle.getTimeToFinishWork().toString().substring(11, 16);
            dto.setTimeToFinishWork(hm);
        }

        if (vehicle.getTimeToStartWork() != null) {
            dto.setDayToStartWork(DateUtil.convertTimeStampStringToJalaliObject(vehicle.getTimeToStartWork()));
            String hm = vehicle.getTimeToStartWork().toString().substring(11, 16);
            dto.setTimeToStartWork(hm);
        }

        if(vehicle.getIsActive()!=null){
            dto.setIsActive(vehicle.getIsActive());
        }
    }




    SelectResponse fromModelToSelect(Vehicle v);

    Vehicle fromSelectToModel(SelectResponse select);

}

abstract class AdmVehicleSelectConverter implements AdmVehicleConverter {

    @Autowired
    private VehicleRepository repo;

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
}