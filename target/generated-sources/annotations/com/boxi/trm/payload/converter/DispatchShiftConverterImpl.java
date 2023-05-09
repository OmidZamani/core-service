package com.boxi.trm.payload.converter;

import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.trm.entity.Calendar;
import com.boxi.trm.entity.CalendarHub;
import com.boxi.trm.entity.DispatchShift;
import com.boxi.trm.payload.dto.DispatchShiftDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class DispatchShiftConverterImpl implements DispatchShiftConverter {

    @Override
    public DispatchShift fromDtoToModel(DispatchShiftDto dto) {
        if ( dto == null ) {
            return null;
        }

        DispatchShift dispatchShift = new DispatchShift();

        if ( dto.getSelecthub() != null ) {
            dispatchShift.setHub( selectResponseToHub( dto.getSelecthub() ) );
        }
        if ( dto.getSelectcalendarhub() != null ) {
            dispatchShift.setCalendarHub( selectResponseToCalendarHub( dto.getSelectcalendarhub() ) );
        }
        if ( dto.getId() != null ) {
            dispatchShift.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            dispatchShift.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            dispatchShift.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getCalendarDate() != null ) {
            dispatchShift.setCalendarDate( dateDtoToCalendar( dto.getCalendarDate() ) );
        }

        AfterDtoToModel( dto, dispatchShift );

        return dispatchShift;
    }

    @Override
    public DispatchShiftDto fromModelToDto(DispatchShift dispatchShift) {
        if ( dispatchShift == null ) {
            return null;
        }

        DispatchShiftDto dispatchShiftDto = new DispatchShiftDto();

        if ( dispatchShift.getHub() != null ) {
            dispatchShiftDto.setSelecthub( hubToSelectResponse( dispatchShift.getHub() ) );
        }
        if ( dispatchShift.getCalendarHub() != null ) {
            dispatchShiftDto.setSelectcalendarhub( calendarHubToSelectResponse( dispatchShift.getCalendarHub() ) );
        }
        if ( dispatchShift.getId() != null ) {
            dispatchShiftDto.setId( dispatchShift.getId() );
        }
        if ( dispatchShift.getIsActive() != null ) {
            dispatchShiftDto.setIsActive( dispatchShift.getIsActive() );
        }
        if ( dispatchShift.getIsDeleted() != null ) {
            dispatchShiftDto.setIsDeleted( dispatchShift.getIsDeleted() );
        }
        if ( dispatchShift.getCalendarDate() != null ) {
            dispatchShiftDto.setCalendarDate( calendarToDateDto( dispatchShift.getCalendarDate() ) );
        }

        AfterModelToDto( dispatchShift, dispatchShiftDto );

        return dispatchShiftDto;
    }

    protected Hub selectResponseToHub(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        Hub hub = new Hub();

        if ( selectResponse.getId() != null ) {
            hub.setId( selectResponse.getId() );
        }

        return hub;
    }

    protected CalendarHub selectResponseToCalendarHub(SelectResponse selectResponse) {
        if ( selectResponse == null ) {
            return null;
        }

        CalendarHub calendarHub = new CalendarHub();

        if ( selectResponse.getId() != null ) {
            calendarHub.setId( selectResponse.getId() );
        }

        return calendarHub;
    }

    protected Calendar dateDtoToCalendar(DateDto dateDto) {
        if ( dateDto == null ) {
            return null;
        }

        Calendar calendar = new Calendar();

        return calendar;
    }

    protected SelectResponse hubToSelectResponse(Hub hub) {
        if ( hub == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( hub.getId() != null ) {
            selectResponse.setId( hub.getId() );
        }

        return selectResponse;
    }

    protected SelectResponse calendarHubToSelectResponse(CalendarHub calendarHub) {
        if ( calendarHub == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( calendarHub.getId() != null ) {
            selectResponse.setId( calendarHub.getId() );
        }

        return selectResponse;
    }

    protected DateDto calendarToDateDto(Calendar calendar) {
        if ( calendar == null ) {
            return null;
        }

        DateDto dateDto = new DateDto();

        return dateDto;
    }
}
