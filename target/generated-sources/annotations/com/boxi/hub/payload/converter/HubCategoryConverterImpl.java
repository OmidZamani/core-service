package com.boxi.hub.payload.converter;

import com.boxi.hub.entity.HubCategory;
import com.boxi.hub.payload.request.CreateHubCategoryRequest;
import com.boxi.hub.payload.response.CreateHubCategoryResponse;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class HubCategoryConverterImpl extends HubCategorySelectConverter {

    private final HubCategoryConverter delegate;

    @Autowired
    public HubCategoryConverterImpl(@Qualifier("delegate") HubCategoryConverter delegate) {

        this.delegate = delegate;
    }

    @Override
    public HubCategory fromDtoToModel(CreateHubCategoryRequest dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public CreateHubCategoryResponse fromModelToDto(HubCategory hubCategory)  {
        return delegate.fromModelToDto( hubCategory );
    }

    @Override
    public void updateFromDto(CreateHubCategoryRequest dto, HubCategory hubCategory)  {
        delegate.updateFromDto( dto, hubCategory );
    }
}
