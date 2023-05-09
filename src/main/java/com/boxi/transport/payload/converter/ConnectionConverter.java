package com.boxi.transport.payload.converter;

import com.boxi.core.conf.BaseMapStructConverter;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Connection;
import com.boxi.transport.payload.dto.ConnectionDto;
import com.boxi.transport.payload.dto.ConnectionExcelDto;
import com.boxi.utils.DateUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = { HubConverter.class})
public interface ConnectionConverter  extends BaseMapStructConverter<Connection, ConnectionDto> {

    @Override
    @Mapping(source = "dto.selectHub", target = "hub")
    @Mapping(target = "timeStoppage", ignore = true)
    @Mapping(target = "transitTime", ignore = true)
    Connection fromDtoToModel(ConnectionDto dto);



    @Override
    @Mapping(source = "hub", target = "selectHub")
    @Mapping(target = "timeStoppage", ignore = true)
    @Mapping(target = "transitTime", ignore = true)
    ConnectionDto fromModelToDto(Connection connection);


    @Mapping( ignore = true, target = "selectHub")
    @Mapping( ignore = true, target = "id")

    ConnectionDto fromExcelToDto(ConnectionExcelDto connection);

    @AfterMapping
    default void validate(Connection connection, @MappingTarget ConnectionDto dto) {
        if (connection.getTimeStoppage()!=null){
            dto.setTimeStoppage(DateUtil.convertMinuteToHHmm(connection.getTimeStoppage().intValue()));
        }
        if (connection.getTransitTime()!=null){
            dto.setTransitTime(DateUtil.convertMinuteToHHmm(connection.getTransitTime().intValue()));
        }

        if(connection.getRoute()!=null)
            dto.setSelectRoute(new SelectResponse(connection.getRoute().getId(),""));
    }

    @AfterMapping
    default void after(ConnectionDto dto,@MappingTarget Connection connection) {
        if (dto.getTimeStoppage()!=null){
            connection.setTimeStoppage((double) DateUtil.convertHHmmToMinute(dto.getTimeStoppage()));
        }
        if (dto.getTransitTime()!=null){
            connection.setTransitTime((double) DateUtil.convertHHmmToMinute(dto.getTransitTime()));
        }
    }

}
