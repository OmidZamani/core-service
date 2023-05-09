package com.boxi.hub.payload.converter;

import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubDto;
import com.boxi.hub.payload.dto.HubWithLocationDto;
import com.boxi.hub.payload.dto.ZoneHubDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T15:17:20+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class HubConverterImpl extends HubSelectConverter {

    @Autowired
    @Qualifier("delegate")
    private HubConverter delegate;

    @Override
    public Hub fromDtoToModel(HubDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public ZoneHubDto fromHubToZone(Hub hub)  {
        return delegate.fromHubToZone( hub );
    }

    @Override
    public HubWithLocationDto fromModelToHubWithLocationDto(Hub hub)  {
        return delegate.fromModelToHubWithLocationDto( hub );
    }

    @Override
    public void updateFromDto(HubDto dto, Hub hub)  {
        delegate.updateFromDto( dto, hub );
    }
}
