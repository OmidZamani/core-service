package com.boxi.trm.payload.converter;

import com.boxi.trm.entity.Calendar;
import com.boxi.trm.payload.dto.CalendarDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class CalendarConverterImpl implements CalendarConverter {

    @Override
    public Calendar fromDtoToModel(CalendarDto dto) {
        if ( dto == null ) {
            return null;
        }

        Calendar calendar = new Calendar();

        if ( dto.getId() != null ) {
            calendar.setId( dto.getId() );
        }
        if ( dto.getShamsi() != null ) {
            calendar.setShamsi( dto.getShamsi() );
        }
        if ( dto.getShamsiDayName() != null ) {
            calendar.setShamsiDayName( dto.getShamsiDayName() );
        }
        if ( dto.getShamsiDayNO() != null ) {
            calendar.setShamsiDayNO( dto.getShamsiDayNO() );
        }
        if ( dto.getShamsiQuarterName() != null ) {
            calendar.setShamsiQuarterName( dto.getShamsiQuarterName() );
        }
        if ( dto.getMonthNO() != null ) {
            calendar.setMonthNO( dto.getMonthNO() );
        }
        if ( dto.getMonthName() != null ) {
            calendar.setMonthName( dto.getMonthName() );
        }
        if ( dto.getYearNO() != null ) {
            calendar.setYearNO( dto.getYearNO() );
        }
        if ( dto.getMonthDayCount() != null ) {
            calendar.setMonthDayCount( dto.getMonthDayCount() );
        }
        if ( dto.getShamsiDayNumber() != null ) {
            calendar.setShamsiDayNumber( dto.getShamsiDayNumber() );
        }

        AfterDtotoModel( dto, calendar );

        return calendar;
    }

    @Override
    public CalendarDto fromModelToDto(Calendar calendar) {
        if ( calendar == null ) {
            return null;
        }

        CalendarDto calendarDto = new CalendarDto();

        if ( calendar.getId() != null ) {
            calendarDto.setId( calendar.getId() );
        }
        if ( calendar.getShamsi() != null ) {
            calendarDto.setShamsi( calendar.getShamsi() );
        }
        if ( calendar.getShamsiDayName() != null ) {
            calendarDto.setShamsiDayName( calendar.getShamsiDayName() );
        }
        if ( calendar.getShamsiDayNO() != null ) {
            calendarDto.setShamsiDayNO( calendar.getShamsiDayNO() );
        }
        if ( calendar.getShamsiQuarterName() != null ) {
            calendarDto.setShamsiQuarterName( calendar.getShamsiQuarterName() );
        }
        if ( calendar.getMonthNO() != null ) {
            calendarDto.setMonthNO( calendar.getMonthNO() );
        }
        if ( calendar.getMonthName() != null ) {
            calendarDto.setMonthName( calendar.getMonthName() );
        }
        if ( calendar.getYearNO() != null ) {
            calendarDto.setYearNO( calendar.getYearNO() );
        }
        if ( calendar.getMonthDayCount() != null ) {
            calendarDto.setMonthDayCount( calendar.getMonthDayCount() );
        }
        if ( calendar.getShamsiDayNumber() != null ) {
            calendarDto.setShamsiDayNumber( calendar.getShamsiDayNumber() );
        }

        AfterModelToDto( calendar, calendarDto );

        return calendarDto;
    }
}
