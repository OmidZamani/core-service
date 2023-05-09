package com.boxi.trm.payload.converter;

import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.trm.entity.Calendar;
import com.boxi.trm.entity.DispatchShift;
import com.boxi.trm.enums.DispatchShiftType;
import com.boxi.trm.payload.dto.DispatchShiftDto;
import com.boxi.utils.DateUtil;
import org.mapstruct.*;
import org.springframework.util.unit.DataUnit;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DispatchShiftConverter {

    @Mapping(ignore = true, target = "timeFrom")
    @Mapping(ignore = true, target = "timeTo")
    @Mapping(ignore = true, target = "dispatchShiftType")
    @Mapping(source = "selecthub.id", target = "hub.id")
    @Mapping(target = "calendarHub.id", source = "selectcalendarhub.id")
    DispatchShift fromDtoToModel(DispatchShiftDto dto);

    @Mapping(ignore = true, target = "timeFrom")
    @Mapping(ignore = true, target = "timeTo")
    @Mapping(ignore = true, target = "dispatchShiftType")
    @Mapping(source = "hub.id", target = "selecthub.id")
    @Mapping(source = "calendarHub.id", target = "selectcalendarhub.id")
    DispatchShiftDto fromModelToDto(DispatchShift dispatchShift);

    @AfterMapping
    default void AfterModelToDto(DispatchShift dispatchShift, @MappingTarget DispatchShiftDto dto) {

        if (dispatchShift.getTimeFrom() != null) {
            dto.setTimeFrom(dispatchShift.getTimeFrom().toString().substring(11, 16));

        }
        if (dispatchShift.getTimeTo() != null) {
            dto.setTimeTo(dispatchShift.getTimeTo().toString().substring(11, 16));
        }
        if (dispatchShift.getDispatchShiftType() != null) {
            dto.setDispatchShiftType(new SelectResponse(dispatchShift.getDispatchShiftType().getValue(), dispatchShift.getDispatchShiftType().getFa()));
        }
        if (dispatchShift.getHub() != null) {
            dto.setSelecthub(new SelectResponse(dispatchShift.getHub().getId(), dispatchShift.getHub().getName()));
        }
        if (dispatchShift.getCalendarDate() != null) {
            String s = dispatchShift.getCalendarDate().getId().toString();
            DateDto dateDto = new DateDto();
            dateDto.setYear(Integer.valueOf(s.substring(0, 4)));
            dateDto.setMonth(Integer.valueOf(s.substring(4, 6)));
            dateDto.setDay(Integer.valueOf(s.substring(6, 8)));
            dto.setCalendarDate(dateDto);
            dto.setSelectcalendar(new SelectResponse(dispatchShift.getCalendarDate().getId(), String.valueOf(dispatchShift.getCalendarDate().getId())));
        }
    }

    @AfterMapping
    default void AfterDtoToModel(DispatchShiftDto dto, @MappingTarget DispatchShift dispatchShift) {
        if (dto.getTimeFrom() != null) {
            String[] split = dto.getTimeFrom().split(":");

            dispatchShift.setTimeFrom(DateUtil.convertJalaliDayTimeToTimeStampWithTime(dto.getCalendarDate(),
                    Integer.parseInt(split[0]),
                    Integer.parseInt(split[1])));
        }

        if (dto.getTimeTo() != null) {
            String[] split = dto.getTimeTo().split(":");
            dispatchShift.setTimeTo(DateUtil.convertJalaliDayTimeToTimeStampWithTime(dto.getCalendarDate(),
                    Integer.parseInt(split[0]),
                    Integer.parseInt(split[1])));

        }

        if (dto.getDispatchShiftType() != null) {
            DispatchShiftType byValue = DispatchShiftType.findByValue(dto.getDispatchShiftType().getId());
            dispatchShift.setDispatchShiftType(byValue);
        }
        dispatchShift.setIsDeleted(false);


        if (dto.getSelectcalendar() != null)
            dispatchShift.setCalendarDate(new Calendar().setId(dto.getSelectcalendar().getId()));

    }

}
