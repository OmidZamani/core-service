package com.boxi.transport.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.HubConverter;
import com.boxi.transport.entity.Connection;
import com.boxi.transport.entity.Route;
import com.boxi.transport.payload.dto.ConnectionDto;
import com.boxi.transport.payload.dto.RouteDto;
import com.boxi.transport.payload.dto.RouteExcelDto;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class RouteConverterImpl_ implements RouteConverter {

    private final HubConverter hubConverter;
    private final ConnectionConverter connectionConverter;

    @Autowired
    public RouteConverterImpl_(HubConverter hubConverter, ConnectionConverter connectionConverter) {

        this.hubConverter = hubConverter;
        this.connectionConverter = connectionConverter;
    }

    @Override
    public Route fromDtoToModel(RouteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Route route = new Route();

        if ( dto.getSelectSourceHub() != null ) {
            route.setSourceHub( hubConverter.selectToHub( dto.getSelectSourceHub() ) );
        }
        if ( dto.getSelectTargetHub() != null ) {
            route.setTargetHub( hubConverter.selectToHub( dto.getSelectTargetHub() ) );
        }
        List<Connection> list = connectionConverter.fromDtosToModels( dto.getConnections() );
        if ( list != null ) {
            route.setConnections( list );
        }
        if ( dto.getIsActive() != null ) {
            route.setIsActive( dto.getIsActive() );
        }
        if ( dto.getId() != null ) {
            route.setId( dto.getId() );
        }
        if ( dto.getCode() != null ) {
            route.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            route.setName( dto.getName() );
        }
        if ( dto.getNodes() != null ) {
            route.setNodes( dto.getNodes() );
        }
        if ( dto.getDistance() != null ) {
            route.setDistance( dto.getDistance() );
        }
        if ( dto.getDistanceVariance() != null ) {
            route.setDistanceVariance( dto.getDistanceVariance() );
        }

        after( dto, route );

        return route;
    }

    @Override
    public RouteDto fromModelToDto(Route saved) {
        if ( saved == null ) {
            return null;
        }

        RouteDto routeDto = new RouteDto();

        if ( saved.getSourceHub() != null ) {
            routeDto.setSelectSourceHub( hubConverter.hubToSelect( saved.getSourceHub() ) );
        }
        if ( saved.getTargetHub() != null ) {
            routeDto.setSelectTargetHub( hubConverter.hubToSelect( saved.getTargetHub() ) );
        }
        List<ConnectionDto> list = connectionConverter.fromModelsToDtos( saved.getConnections() );
        if ( list != null ) {
            routeDto.setConnections( list );
        }
        if ( saved.getIsActive() != null ) {
            routeDto.setIsActive( saved.getIsActive() );
        }
        if ( saved.getId() != null ) {
            routeDto.setId( saved.getId() );
        }
        if ( saved.getCode() != null ) {
            routeDto.setCode( saved.getCode() );
        }
        if ( saved.getName() != null ) {
            routeDto.setName( saved.getName() );
        }
        if ( saved.getNodes() != null ) {
            routeDto.setNodes( saved.getNodes() );
        }
        if ( saved.getDistance() != null ) {
            routeDto.setDistance( saved.getDistance() );
        }
        if ( saved.getDistanceVariance() != null ) {
            routeDto.setDistanceVariance( saved.getDistanceVariance() );
        }

        validate( saved, routeDto );

        return routeDto;
    }

    @Override
    public RouteDto fromExcelToDto(RouteExcelDto saved) {
        if ( saved == null ) {
            return null;
        }

        RouteDto routeDto = new RouteDto();

        if ( saved.getCode() != null ) {
            routeDto.setCode( saved.getCode() );
        }
        if ( saved.getName() != null ) {
            routeDto.setName( saved.getName() );
        }
        if ( saved.getIsActive() != null ) {
            routeDto.setIsActive( saved.getIsActive() );
        }

        return routeDto;
    }

    @Override
    public void updateFromDto(RouteDto dto, Route route) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getSelectSourceHub() != null ) {
            route.setSourceHub( hubConverter.selectToHub( dto.getSelectSourceHub() ) );
        }
        else {
            route.setSourceHub( null );
        }
        if ( dto.getSelectTargetHub() != null ) {
            route.setTargetHub( hubConverter.selectToHub( dto.getSelectTargetHub() ) );
        }
        else {
            route.setTargetHub( null );
        }
        if ( route.getConnections() != null ) {
            List<Connection> list = connectionConverter.fromDtosToModels( dto.getConnections() );
            if ( list != null ) {
                route.getConnections().clear();
                route.getConnections().addAll( list );
            }
        }
        else {
            List<Connection> list = connectionConverter.fromDtosToModels( dto.getConnections() );
            if ( list != null ) {
                route.setConnections( list );
            }
        }
        if ( dto.getIsActive() != null ) {
            route.setIsActive( dto.getIsActive() );
        }
        else {
            route.setIsActive( null );
        }
        if ( dto.getId() != null ) {
            route.setId( dto.getId() );
        }
        else {
            route.setId( null );
        }
        if ( dto.getCode() != null ) {
            route.setCode( dto.getCode() );
        }
        else {
            route.setCode( null );
        }
        if ( dto.getName() != null ) {
            route.setName( dto.getName() );
        }
        else {
            route.setName( null );
        }
        if ( dto.getNodes() != null ) {
            route.setNodes( dto.getNodes() );
        }
        else {
            route.setNodes( null );
        }
        if ( dto.getDistance() != null ) {
            route.setDistance( dto.getDistance() );
        }
        else {
            route.setDistance( null );
        }
        if ( dto.getDistanceVariance() != null ) {
            route.setDistanceVariance( dto.getDistanceVariance() );
        }
        else {
            route.setDistanceVariance( null );
        }

        after( dto, route );
    }

    @Override
    public SelectResponse fromModelToSelect(Route route) {
        if ( route == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( route.getId() != null ) {
            selectResponse.setId( route.getId() );
        }

        return selectResponse;
    }

    @Override
    public Route fromSelectToModel(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        Route route = new Route();

        if ( select.getId() != null ) {
            route.setId( select.getId() );
        }

        return route;
    }
}
