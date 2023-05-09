package com.boxi.hub.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.enums.CountryType;
import com.boxi.hub.payload.dto.CountryDevisionPolygonDto;
import com.boxi.hub.repo.CountryDevisionRepository;
import com.boxi.product.payload.dto.CountryDevisionDto;
import com.boxi.product.response.ContryDevistionSelect;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(CountryDevisionSelectConverter.class)
public interface CountryDevisionConverter {

    SelectResponse fromModelToSelect(CountryDevision c);

    ContryDevistionSelect fromModelToType(CountryDevision c);

    CountryDevision fromSelectToModel(SelectResponse select);

    @Mapping(target = "countryType",ignore = true)
    CountryDevision fromDtotoModel(CountryDevisionDto dto);

    CountryDevisionDto fromModeltoDto(CountryDevision countryDevision);

    CountryDevisionPolygonDto fromModeltoPolygoneDto(CountryDevisionDto countryDevision);

    @AfterMapping
    default void afterfromModelToDto(CountryDevision countryDevision, @MappingTarget CountryDevisionDto dto) {
        if (countryDevision.getHubId() != null)
            dto.setSelectHub(new SelectResponse(countryDevision.getHubId(), ""));
        if (countryDevision.getResponsiblePersonelId() != null)
            dto.setSelectResponsiblePersonel(new SelectResponse(countryDevision.getResponsiblePersonelId(), ""));
        if(countryDevision.getCountryType()!=null)
        {
            CountryType byValue = CountryType.findByValue(countryDevision.getCountryType().getValue());
            dto.setCountryType(new SelectResponse(byValue.getValue(),byValue.getFa()));
        }

    }

    @AfterMapping
    default void afterfromDtoToModel(CountryDevisionDto dto, @MappingTarget CountryDevision countryDevision) {
        if (dto.getSelectHub() != null)
            countryDevision.setHubId(dto.getSelectHub().getId());

        if (dto.getSelectResponsiblePersonel() != null)
            countryDevision.setResponsiblePersonelId(dto.getSelectResponsiblePersonel().getId());

        if(dto.getCountryType()!=null){
            CountryType byValue = CountryType.findByValue(dto.getCountryType().getId());
            countryDevision.setCountryType(byValue);
        }
    }

}

abstract class CountryDevisionSelectConverter implements CountryDevisionConverter {

    @Autowired
    CountryDevisionRepository repo;

    @Override
    public SelectResponse fromModelToSelect(CountryDevision c) {
        return new SelectResponse(c.getId(), c.selectToString());
    }

    @Override
    public CountryDevision fromSelectToModel(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return repo.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.CountryDevision, ExceptionType.ENTITY_NOT_FOUND);
        });
    }
}

