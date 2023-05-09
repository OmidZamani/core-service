package com.boxi.transport.payload.converter;

import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Dock;
import com.boxi.transport.payload.dto.DockDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-05T14:04:32+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class DockConverterImpl implements DockConverter {

    private final HubConverter hubConverter;

    @Autowired
    public DockConverterImpl(HubConverter hubConverter) {

        this.hubConverter = hubConverter;
    }

    @Override
    public List<Dock> fromDtosToModels(Collection<DockDto> dtos) {
        if ( dtos == null ) {
            return new ArrayList<Dock>();
        }

        List<Dock> list = new ArrayList<Dock>( dtos.size() );
        for ( DockDto dockDto : dtos ) {
            list.add( fromDtoToModel( dockDto ) );
        }

        return list;
    }

    @Override
    public List<DockDto> fromModelsToDtos(Collection<Dock> models) {
        if ( models == null ) {
            return new ArrayList<DockDto>();
        }

        List<DockDto> list = new ArrayList<DockDto>( models.size() );
        for ( Dock dock : models ) {
            list.add( fromModelToDto( dock ) );
        }

        return list;
    }

    @Override
    public Dock fromDtoToModel(DockDto dto) {
        if ( dto == null ) {
            return null;
        }

        Dock dock = new Dock();

        if ( dto.getSelectHub() != null ) {
            dock.setHub( hubConverter.selectToHub( dto.getSelectHub() ) );
        }
        if ( dto.getId() != null ) {
            dock.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            dock.setIsActive( dto.getIsActive() );
        }
        if ( dto.getCode() != null ) {
            dock.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            dock.setName( dto.getName() );
        }

        return dock;
    }

    @Override
    public DockDto fromModelToDto(Dock dock) {
        if ( dock == null ) {
            return null;
        }

        DockDto dockDto = new DockDto();

        if ( dock.getHub() != null ) {
            dockDto.setSelectHub( hubConverter.hubToSelect( dock.getHub() ) );
        }
        if ( dock.getId() != null ) {
            dockDto.setId( dock.getId() );
        }
        if ( dock.getCode() != null ) {
            dockDto.setCode( dock.getCode() );
        }
        if ( dock.getName() != null ) {
            dockDto.setName( dock.getName() );
        }
        if ( dock.getIsActive() != null ) {
            dockDto.setIsActive( dock.getIsActive() );
        }

        return dockDto;
    }

    @Override
    public void updateFromDto(DockDto dto, Dock dock) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getSelectHub() != null ) {
            dock.setHub( hubConverter.selectToHub( dto.getSelectHub() ) );
        }
        else {
            dock.setHub( null );
        }
        if ( dto.getId() != null ) {
            dock.setId( dto.getId() );
        }
        else {
            dock.setId( null );
        }
        if ( dto.getIsActive() != null ) {
            dock.setIsActive( dto.getIsActive() );
        }
        else {
            dock.setIsActive( null );
        }
        if ( dto.getCode() != null ) {
            dock.setCode( dto.getCode() );
        }
        else {
            dock.setCode( null );
        }
        if ( dto.getName() != null ) {
            dock.setName( dto.getName() );
        }
        else {
            dock.setName( null );
        }
    }
}
