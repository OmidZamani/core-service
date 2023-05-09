package com.boxi.trm.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.trm.entity.CalendarHub;
import com.boxi.trm.payload.dto.CalendarHubDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = { CalendarConverter.class})
public interface CalendarHubConverter {


    @Mapping(target = "selecthub",source = "hub")
    @Mapping(target = "dispatchShifts",ignore = true)
    CalendarHubDto fromModelToDto(CalendarHub calendarHub);


    @Mapping(target = "hub",source = "selecthub")
    @Mapping(target = "dispatchShifts",ignore = true)
    CalendarHub fromDtoToModel(CalendarHubDto dto);

    @AfterMapping
    default void AfterModelToDto(CalendarHub calendarHub , @MappingTarget CalendarHubDto dto){
        if(calendarHub.getHub()!=null){
            dto.setSelecthub(new SelectResponse(calendarHub.getHub().getId(),calendarHub.getHub().getName()));
        }
    }

}
