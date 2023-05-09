package com.boxi.personalizationform.service;


import com.boxi.personalizationform.payload.dto.PersonalizationFormDto;

public interface PersonalizationFormService {

    PersonalizationFormDto create(PersonalizationFormDto dto);

    PersonalizationFormDto edit(PersonalizationFormDto dto);

    void delete(Long id);

    PersonalizationFormDto getByUseridAndPermissionId(Long userId ,Long permissionId);

    PersonalizationFormDto findByCode(Long userId ,String code);
}
