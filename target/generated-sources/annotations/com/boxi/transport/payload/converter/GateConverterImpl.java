package com.boxi.transport.payload.converter;

import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Gate;
import com.boxi.transport.payload.dto.GateDto;
import com.boxi.transport.payload.dto.GateExcelDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-05T14:04:32+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class GateConverterImpl implements GateConverter {

    private final HubConverter hubConverter;

    @Autowired
    public GateConverterImpl(HubConverter hubConverter) {

        this.hubConverter = hubConverter;
    }

    @Override
    public Gate fromDtoToModel(GateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Gate gate = new Gate();

        if ( dto.getSelectHub() != null ) {
            gate.setHub( hubConverter.selectToHub( dto.getSelectHub() ) );
        }
        if ( dto.getId() != null ) {
            gate.setId( dto.getId() );
        }
        if ( dto.getCode() != null ) {
            gate.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            gate.setName( dto.getName() );
        }

        return gate;
    }

    @Override
    public void updateFromDto(GateDto dto, Gate gate) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getSelectHub() != null ) {
            gate.setHub( hubConverter.selectToHub( dto.getSelectHub() ) );
        }
        else {
            gate.setHub( null );
        }
        if ( dto.getId() != null ) {
            gate.setId( dto.getId() );
        }
        else {
            gate.setId( null );
        }
        if ( dto.getCode() != null ) {
            gate.setCode( dto.getCode() );
        }
        else {
            gate.setCode( null );
        }
        if ( dto.getName() != null ) {
            gate.setName( dto.getName() );
        }
        else {
            gate.setName( null );
        }
    }

    @Override
    public GateDto fromModelToDto(Gate gate) {
        if ( gate == null ) {
            return null;
        }

        GateDto gateDto = new GateDto();

        if ( gate.getHub() != null ) {
            gateDto.setSelectHub( hubConverter.hubToSelect( gate.getHub() ) );
        }
        if ( gate.getId() != null ) {
            gateDto.setId( gate.getId() );
        }
        if ( gate.getCode() != null ) {
            gateDto.setCode( gate.getCode() );
        }
        if ( gate.getName() != null ) {
            gateDto.setName( gate.getName() );
        }

        return gateDto;
    }

    @Override
    public GateDto fromExcelToDto(GateExcelDto gate) {
        if ( gate == null ) {
            return null;
        }

        GateDto gateDto = new GateDto();

        if ( gate.getCode() != null ) {
            gateDto.setCode( gate.getCode() );
        }
        if ( gate.getName() != null ) {
            gateDto.setName( gate.getName() );
        }

        return gateDto;
    }
}
