package com.boxi.personalizationform.payload.converter;


import com.boxi.personalizationform.entity.PersonalizationForm;
import com.boxi.personalizationform.payload.dto.PersonalizationFormDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PersonalizationFormConverter {

    @Mapping(source = "selectUser.id", target = "userId")
    @Mapping(source = "selectPermission.id", target = "permissionId")
    PersonalizationForm fromDtoToModel(PersonalizationFormDto dto);

    @Mapping(source = "userId", target = "selectUser.id")
    @Mapping(source = "permissionId", target = "selectPermission.id")
    PersonalizationFormDto fromModelToDto(PersonalizationForm personalizationForm);
}
