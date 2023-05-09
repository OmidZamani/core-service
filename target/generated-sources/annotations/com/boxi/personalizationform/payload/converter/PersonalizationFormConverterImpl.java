package com.boxi.personalizationform.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.personalizationform.entity.PersonalizationForm;
import com.boxi.personalizationform.payload.dto.PersonalizationFormDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class PersonalizationFormConverterImpl implements PersonalizationFormConverter {

    @Override
    public PersonalizationForm fromDtoToModel(PersonalizationFormDto dto) {
        if ( dto == null ) {
            return null;
        }

        PersonalizationForm personalizationForm = new PersonalizationForm();

        Long id = dtoSelectUserId( dto );
        if ( id != null ) {
            personalizationForm.setUserId( id );
        }
        Long id1 = dtoSelectPermissionId( dto );
        if ( id1 != null ) {
            personalizationForm.setPermissionId( id1 );
        }
        if ( dto.getId() != null ) {
            personalizationForm.setId( dto.getId() );
        }
        if ( dto.getFilterPersonalize() != null ) {
            personalizationForm.setFilterPersonalize( dto.getFilterPersonalize() );
        }
        if ( dto.getOperationPersonalize() != null ) {
            personalizationForm.setOperationPersonalize( dto.getOperationPersonalize() );
        }
        if ( dto.getListPersonalize() != null ) {
            personalizationForm.setListPersonalize( dto.getListPersonalize() );
        }

        return personalizationForm;
    }

    @Override
    public PersonalizationFormDto fromModelToDto(PersonalizationForm personalizationForm) {
        if ( personalizationForm == null ) {
            return null;
        }

        PersonalizationFormDto personalizationFormDto = new PersonalizationFormDto();

        if ( personalizationForm != null ) {
            personalizationFormDto.setSelectUser( personalizationFormToSelectResponse( personalizationForm ) );
        }
        if ( personalizationForm != null ) {
            personalizationFormDto.setSelectPermission( personalizationFormToSelectResponse1( personalizationForm ) );
        }
        if ( personalizationForm.getId() != null ) {
            personalizationFormDto.setId( personalizationForm.getId() );
        }
        if ( personalizationForm.getFilterPersonalize() != null ) {
            personalizationFormDto.setFilterPersonalize( personalizationForm.getFilterPersonalize() );
        }
        if ( personalizationForm.getOperationPersonalize() != null ) {
            personalizationFormDto.setOperationPersonalize( personalizationForm.getOperationPersonalize() );
        }
        if ( personalizationForm.getListPersonalize() != null ) {
            personalizationFormDto.setListPersonalize( personalizationForm.getListPersonalize() );
        }

        return personalizationFormDto;
    }

    private Long dtoSelectUserId(PersonalizationFormDto personalizationFormDto) {
        if ( personalizationFormDto == null ) {
            return null;
        }
        SelectResponse selectUser = personalizationFormDto.getSelectUser();
        if ( selectUser == null ) {
            return null;
        }
        Long id = selectUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long dtoSelectPermissionId(PersonalizationFormDto personalizationFormDto) {
        if ( personalizationFormDto == null ) {
            return null;
        }
        SelectResponse selectPermission = personalizationFormDto.getSelectPermission();
        if ( selectPermission == null ) {
            return null;
        }
        Long id = selectPermission.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected SelectResponse personalizationFormToSelectResponse(PersonalizationForm personalizationForm) {
        if ( personalizationForm == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( personalizationForm.getUserId() != null ) {
            selectResponse.setId( personalizationForm.getUserId() );
        }

        return selectResponse;
    }

    protected SelectResponse personalizationFormToSelectResponse1(PersonalizationForm personalizationForm) {
        if ( personalizationForm == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( personalizationForm.getPermissionId() != null ) {
            selectResponse.setId( personalizationForm.getPermissionId() );
        }

        return selectResponse;
    }
}
