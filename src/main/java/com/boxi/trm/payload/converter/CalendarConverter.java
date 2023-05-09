package com.boxi.trm.payload.converter;

import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.trm.entity.Calendar;
import com.boxi.trm.enums.DAY;
import com.boxi.trm.enums.MONTH;
import com.boxi.trm.payload.dto.CalendarDto;
import com.boxi.utils.DateUtil;
import org.mapstruct.*;

import java.lang.annotation.Target;
import java.util.Date;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
        )
public interface CalendarConverter {



    @Mapping(ignore = true, target = "dispatchShifts")
    Calendar fromDtoToModel(CalendarDto dto);


    @Mapping(ignore = true, target = "date")
    @Mapping(ignore = true, target = "dispatchShifts")
    CalendarDto fromModelToDto(Calendar calendar);

    @AfterMapping
    default void AfterDtotoModel(CalendarDto dto, @MappingTarget Calendar calendar) {


    }

    @AfterMapping
    default void AfterModelToDto(Calendar calendar, @MappingTarget CalendarDto dto) {
        dto.setDate(DateUtil.convertDateToJalaliDateDto(calendar.getMiladiDate()));
    }


}
