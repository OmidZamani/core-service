package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.HubCategory;
import com.boxi.hub.payload.request.CreateHubCategoryRequest;
import com.boxi.hub.payload.response.CreateHubCategoryResponse;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Qualifier("delegate")
public class HubCategoryConverterImpl_ implements HubCategoryConverter {

    @Override
    public HubCategory fromDtoToModel(CreateHubCategoryRequest dto) {
        if ( dto == null ) {
            return null;
        }

        HubCategory hubCategory = new HubCategory();

        if ( dto.getId() != null ) {
            hubCategory.setId( dto.getId() );
        }
        if ( dto.getCode() != null ) {
            hubCategory.setCode( dto.getCode() );
        }
        if ( dto.getName() != null ) {
            hubCategory.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            hubCategory.setDescription( dto.getDescription() );
        }

        return hubCategory;
    }

    @Override
    public CreateHubCategoryResponse fromModelToDto(HubCategory hubCategory) {
        if ( hubCategory == null ) {
            return null;
        }

        CreateHubCategoryResponse createHubCategoryResponse = new CreateHubCategoryResponse();

        if ( hubCategory.getId() != null ) {
            createHubCategoryResponse.setId( hubCategory.getId() );
        }
        if ( hubCategory.getCode() != null ) {
            createHubCategoryResponse.setCode( hubCategory.getCode() );
        }
        if ( hubCategory.getName() != null ) {
            createHubCategoryResponse.setName( hubCategory.getName() );
        }
        if ( hubCategory.getDescription() != null ) {
            createHubCategoryResponse.setDescription( hubCategory.getDescription() );
        }

        return createHubCategoryResponse;
    }

    @Override
    public void updateFromDto(CreateHubCategoryRequest dto, HubCategory hubCategory) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            hubCategory.setId( dto.getId() );
        }
        else {
            hubCategory.setId( null );
        }
        if ( dto.getCode() != null ) {
            hubCategory.setCode( dto.getCode() );
        }
        else {
            hubCategory.setCode( null );
        }
        if ( dto.getName() != null ) {
            hubCategory.setName( dto.getName() );
        }
        else {
            hubCategory.setName( null );
        }
        if ( dto.getDescription() != null ) {
            hubCategory.setDescription( dto.getDescription() );
        }
        else {
            hubCategory.setDescription( null );
        }
    }

    @Override
    public SelectResponse fromModelToSelect(HubCategory h) {
        if ( h == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( h.getId() != null ) {
            selectResponse.setId( h.getId() );
        }

        return selectResponse;
    }

    @Override
    public HubCategory fromSelectToModel(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        HubCategory hubCategory = new HubCategory();

        if ( select.getId() != null ) {
            hubCategory.setId( select.getId() );
        }

        return hubCategory;
    }
}
