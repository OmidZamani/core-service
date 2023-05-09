package com.boxi.transport.payload.converter;

import com.boxi.core.conf.BaseMapStructConverter;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Dock;
import com.boxi.transport.payload.dto.DockDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = {HubConverter.class})
public interface DockConverter extends BaseMapStructConverter<Dock, DockDto> {

    @Override
    @Mapping(source = "dto.selectHub", target = "hub")
    Dock fromDtoToModel(DockDto dto);

    @Override
    @Mapping(source = "hub", target = "selectHub")
    DockDto fromModelToDto(Dock dock);

    @Mapping(source = "dto.selectHub", target = "hub")
    void updateFromDto(DockDto dto,@MappingTarget Dock dock);
}
