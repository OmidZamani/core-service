package com.boxi.transport.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Route;
import com.boxi.transport.payload.dto.RouteDto;
import com.boxi.transport.payload.dto.RouteExcelDto;
import com.boxi.transport.repo.RouteRepository;
import com.boxi.utils.DateUtil;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {HubConverter.class, ConnectionConverter.class})
@DecoratedWith(RouteSelectConverter.class)
public interface RouteConverter {

    @Mapping(source = "dto.selectSourceHub", target = "sourceHub")
    @Mapping(source = "dto.selectTargetHub", target = "targetHub")
    @Mapping(source = "dto.connections", target = "connections")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(target = "timeStoppage", ignore = true)
    @Mapping(target = "transitTime", ignore = true)
    Route fromDtoToModel(RouteDto dto);

    @Mapping(source = "sourceHub", target = "selectSourceHub")
    @Mapping(source = "targetHub", target = "selectTargetHub")
    @Mapping(source = "connections", target = "connections")
    @Mapping(source = "isActive", target = "isActive")
    @Mapping(target = "timeStoppage", ignore = true)
    @Mapping(target = "transitTime", ignore = true)
    RouteDto fromModelToDto(Route saved);

    @Mapping(ignore = true, target = "selectSourceHub")
    @Mapping(ignore = true, target = "selectTargetHub")
    @Mapping(ignore = true, target = "connections")
//    @Mapping(target = "timeStoppage", ignore = true)
//    @Mapping(target = "transitTime", ignore = true)
    RouteDto fromExcelToDto(RouteExcelDto saved);

    @Mapping(source = "dto.selectSourceHub", target = "sourceHub")
    @Mapping(source = "dto.selectTargetHub", target = "targetHub")
    @Mapping(source = "dto.connections", target = "connections")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(target = "timeStoppage", ignore = true)
    @Mapping(target = "transitTime", ignore = true)
    void updateFromDto(RouteDto dto, @MappingTarget Route route);

    SelectResponse fromModelToSelect(Route route);

    Route fromSelectToModel(SelectResponse select);

    @AfterMapping
    default void validate(Route route,@MappingTarget RouteDto dto) {
        if (route.getTimeStoppage()!=null){

            dto.setTimeStoppage(DateUtil.convertMinuteToHHmm(route.getTimeStoppage().intValue()));
        }
        if (route.getTransitTime()!=null){
            dto.setTransitTime(DateUtil.convertMinuteToHHmm(route.getTransitTime().intValue()));
        }
    }

    @AfterMapping
    default void after(RouteDto dto,@MappingTarget Route route) {
        if (dto.getTimeStoppage()!=null){
            Double timeStopPage= Double.valueOf(0);
            String[] split = dto.getTimeStoppage().split(":");
            timeStopPage=Double.valueOf(split[0])*60;
            timeStopPage+=Double.valueOf(split[1])*1;
            route.setTimeStoppage(timeStopPage);
        }
        if (dto.getTransitTime()!=null){
            Double transitTime= Double.valueOf(0);
            String[] split = dto.getTransitTime().split(":");
            transitTime=Double.valueOf(split[0])*60;
            transitTime+=Double.valueOf(split[1])*1;
            route.setTransitTime(Double.valueOf(transitTime));
        }
    }

}

abstract class RouteSelectConverter implements RouteConverter {

    @Autowired
    private RouteRepository repo;

    @Override
    public SelectResponse fromModelToSelect(Route r) {
        return new SelectResponse(r.getId(), r.selectToString());
    }

    @Override
    public Route fromSelectToModel(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return repo.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.Route, ExceptionType.ENTITY_NOT_FOUND);
        });
    }
}
