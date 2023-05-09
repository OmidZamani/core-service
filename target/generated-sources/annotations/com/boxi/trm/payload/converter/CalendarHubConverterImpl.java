package com.boxi.trm.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.trm.entity.CalendarHub;
import com.boxi.trm.payload.dto.CalendarHubDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class CalendarHubConverterImpl implements CalendarHubConverter {

    @Override
    public CalendarHubDto fromModelToDto(CalendarHub calendarHub) {
        if ( calendarHub == null ) {
            return null;
        }

        CalendarHubDto calendarHubDto = new CalendarHubDto();

        if ( calendarHub.getHub() != null ) {
            calendarHubDto.setSelecthub( hubToSelectResponse( calendarHub.getHub() ) );
        }
        if ( calendarHub.getId() != null ) {
            calendarHubDto.setId( calendarHub.getId() );
        }
        if ( calendarHub.getIsActive() != null ) {
            calendarHubDto.setIsActive( calendarHub.getIsActive() );
        }
        if ( calendarHub.getCode() != null ) {
            calendarHubDto.setCode( calendarHub.getCode() );
        }
        if ( calendarHub.getName() != null ) {
            calendarHubDto.setName( calendarHub.getName() );
        }
        if ( calendarHub.getYearNO() != null ) {
            calendarHubDto.setYearNO( calendarHub.getYearNO() );
        }

        AfterModelToDto( calendarHub, calendarHubDto );

        return calendarHubDto;
    }

    @Override
    public CalendarHub fromDtoToModel(CalendarHubDto dto) {
        if ( dto == null ) {
            return null;
        }

        CalendarHub calendarHub = new CalendarHub();

        if ( dto.getSelecthub() != null ) {
            calendarHub.setHub( selectResponseToHub( dto.getSelecthub() ) );
        }
        if ( dto.getId() != null ) {
            calendarHub.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            calendarHub.setIsActive( dto.getIsActive() );
        }
        if ( dto.getCode() != null ) {
            calendarHub.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            calendarHub.setName( dto.getName() );
        }
        if ( dto.getYearNO() != null ) {
            calendarHub.setYearNO( dto.getYearNO() );
        }

        return calendarHub;
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
}
