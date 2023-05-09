package com.boxi.transport.payload.converter;

import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Connection;
import com.boxi.transport.payload.dto.ConnectionDto;
import com.boxi.transport.payload.dto.ConnectionExcelDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class ConnectionConverterImpl implements ConnectionConverter {

    private final HubConverter hubConverter;

    @Autowired
    public ConnectionConverterImpl(HubConverter hubConverter) {

        this.hubConverter = hubConverter;
    }

    @Override
    public List<Connection> fromDtosToModels(Collection<ConnectionDto> dtos) {
        if ( dtos == null ) {
            return new ArrayList<Connection>();
        }

        List<Connection> list = new ArrayList<Connection>( dtos.size() );
        for ( ConnectionDto connectionDto : dtos ) {
            list.add( fromDtoToModel( connectionDto ) );
        }

        return list;
    }

    @Override
    public List<ConnectionDto> fromModelsToDtos(Collection<Connection> models) {
        if ( models == null ) {
            return new ArrayList<ConnectionDto>();
        }

        List<ConnectionDto> list = new ArrayList<ConnectionDto>( models.size() );
        for ( Connection connection : models ) {
            list.add( fromModelToDto( connection ) );
        }

        return list;
    }

    @Override
    public Connection fromDtoToModel(ConnectionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Connection connection = new Connection();

        if ( dto.getSelectHub() != null ) {
            connection.setHub( hubConverter.selectToHub( dto.getSelectHub() ) );
        }
        if ( dto.getId() != null ) {
            connection.setId( dto.getId() );
        }
        if ( dto.getDistanceFromPreviousHub() != null ) {
            connection.setDistanceFromPreviousHub( dto.getDistanceFromPreviousHub() );
        }
        if ( dto.getDistanceVariance() != null ) {
            connection.setDistanceVariance( dto.getDistanceVariance() );
        }

        after( dto, connection );

        return connection;
    }

    @Override
    public ConnectionDto fromModelToDto(Connection connection) {
        if ( connection == null ) {
            return null;
        }

        ConnectionDto connectionDto = new ConnectionDto();

        if ( connection.getHub() != null ) {
            connectionDto.setSelectHub( hubConverter.hubToSelect( connection.getHub() ) );
        }
        if ( connection.getId() != null ) {
            connectionDto.setId( connection.getId() );
        }
        if ( connection.getDistanceFromPreviousHub() != null ) {
            connectionDto.setDistanceFromPreviousHub( connection.getDistanceFromPreviousHub() );
        }
        if ( connection.getDistanceVariance() != null ) {
            connectionDto.setDistanceVariance( connection.getDistanceVariance() );
        }

        validate( connection, connectionDto );

        return connectionDto;
    }

    @Override
    public ConnectionDto fromExcelToDto(ConnectionExcelDto connection) {
        if ( connection == null ) {
            return null;
        }

        ConnectionDto connectionDto = new ConnectionDto();

        if ( connection.getDistanceFromPreviousHub() != null ) {
            connectionDto.setDistanceFromPreviousHub( connection.getDistanceFromPreviousHub() );
        }
        if ( connection.getDistanceVariance() != null ) {
            connectionDto.setDistanceVariance( connection.getDistanceVariance() );
        }
        if ( connection.getTransitTime() != null ) {
            connectionDto.setTransitTime( connection.getTransitTime() );
        }
        if ( connection.getTimeStoppage() != null ) {
            connectionDto.setTimeStoppage( connection.getTimeStoppage() );
        }

        return connectionDto;
    }
}
