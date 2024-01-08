package com.boxi.personalizationform.service.Impl;


import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.PermissionDto;
import com.boxi.feign.PermissionClient;
import com.boxi.personalizationform.entity.PersonalizationForm;
import com.boxi.personalizationform.payload.converter.PersonalizationFormConverter;
import com.boxi.personalizationform.payload.dto.PersonalizationFormDto;
import com.boxi.personalizationform.repo.PersonalizationFormRepository;
import com.boxi.personalizationform.service.PersonalizationFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class PersonalizationFormServiceImpl implements PersonalizationFormService {

    public final PersonalizationFormRepository personalizationFormRepository;
    public final PersonalizationFormConverter personalizationFormConverter;


    public final PermissionClient per;

    public PersonalizationFormServiceImpl(PersonalizationFormRepository personalizationFormRepository,
                                          PersonalizationFormConverter personalizationFormConverter,
                                          PermissionClient per) {
        this.personalizationFormRepository = personalizationFormRepository;
        this.personalizationFormConverter = personalizationFormConverter;
        this.per = per;
    }

    @Override
    public PersonalizationFormDto create(PersonalizationFormDto dto) {
        PermissionDto permissionDto = per.findbyCode(dto.getSelectPermission().getText());
        dto.getSelectPermission().setId(permissionDto.getId());
        return saveCreate(dto);
    }

    private Boolean isExists(PersonalizationFormDto dto) {
        return personalizationFormRepository.existsByUserIdAndPermissionId(dto.getSelectUser().getId(), dto.getSelectPermission().getId());
    }

    private PersonalizationFormDto saveCreate(PersonalizationFormDto dto) {

        PersonalizationForm byUserIdAndPermissionId = personalizationFormRepository.findByUserIdAndPermissionId(dto.getSelectUser().getId(), dto.getSelectPermission().getId());
        PersonalizationForm personalizationForm = personalizationFormConverter.fromDtoToModel(dto);
        if (byUserIdAndPermissionId != null) {
            personalizationForm.setId(byUserIdAndPermissionId.getId());
        } else {
            personalizationForm.setId(null);
        }

        PersonalizationForm save = personalizationFormRepository.save(personalizationForm);
        dto.setId(save.getId());
        return dto;
    }

    @Override
    public PersonalizationFormDto edit(PersonalizationFormDto dto) {
        PermissionDto permissionDto = per.findbyCode(dto.getSelectPermission().getText());
        dto.getSelectPermission().setId(permissionDto.getId());
        return saveEdit(dto);
    }

    private PersonalizationFormDto saveEdit(PersonalizationFormDto dto) {
        if (!isExists(dto))
            throw BusinessException.valueException(EntityType.PersonalizationForm, "personalization.form.not.found");

        PersonalizationForm personalizationForm = personalizationFormConverter.fromDtoToModel(dto);
        personalizationFormRepository.save(personalizationForm);
        return dto;
    }

    @Override
    public void delete(Long id) {
        if (!exists(id))
            throw BusinessException.entityNotFoundException(EntityType.PersonalizationForm, "personalization.form.not.found");

        personalizationFormRepository.deleteById(id);
    }

    public Boolean exists(Long id) {
        return personalizationFormRepository.existsById(id);
    }

    @Override
    public PersonalizationFormDto getByUseridAndPermissionId(Long userId, Long permissionId) {
        PersonalizationForm personalizationForm = personalizationFormRepository.findByUserIdAndPermissionId(userId, permissionId);
        if (personalizationForm == null)
            throw BusinessException.entityNotFoundException(EntityType.PersonalizationForm, "personalization.form.not.found");

        return personalizationFormConverter.fromModelToDto(personalizationForm);
    }

    @Override
    public PersonalizationFormDto findByCode(Long userId, String code) {

        PermissionDto permissionDto = per.findbyCode(code);
        if (permissionDto == null)
//            throw BusinessException.valueException(EntityType.PersonalizationForm, "personalization.form.not.found");
            return null;
        PersonalizationForm byUserIdAndPermissionId = personalizationFormRepository.findByUserIdAndPermissionId(userId, permissionDto.getId());
        return personalizationFormConverter.fromModelToDto(byUserIdAndPermissionId);

    }
}
