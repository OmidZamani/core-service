package com.boxi.transport.payload.converter;

import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.hub.service.HubService;
import com.boxi.transport.entity.Gate;
import com.boxi.transport.payload.dto.GateDto;
import com.boxi.transport.payload.dto.GateExcelDto;
import com.boxi.transport.service.GateService;
import org.mapstruct.*;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = {HubConverter.class})
public interface GateConverter {

    @Mapping(source = "dto.selectHub", target = "hub")
    Gate fromDtoToModel(GateDto dto);

    @Mapping(source = "dto.selectHub", target = "hub")
    void updateFromDto(GateDto dto, @MappingTarget Gate gate);

    @Mapping(source = "hub", target = "selectHub")
    GateDto fromModelToDto(Gate gate);

    @Mapping(target =  "selectHub", ignore = true)
    GateDto fromExcelToDto(GateExcelDto gate);
}
