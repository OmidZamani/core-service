package com.boxi.hub.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.HubCategory;
import com.boxi.hub.payload.request.CreateHubCategoryRequest;
import com.boxi.hub.payload.response.CreateHubCategoryResponse;
import com.boxi.hub.repo.HubCategoryRepository;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(HubCategorySelectConverter.class)
public interface HubCategoryConverter {


    HubCategory fromDtoToModel(CreateHubCategoryRequest dto);

    CreateHubCategoryResponse fromModelToDto(HubCategory hubCategory);

    void updateFromDto(CreateHubCategoryRequest dto, @MappingTarget HubCategory hubCategory);

    SelectResponse fromModelToSelect(HubCategory h);

    HubCategory fromSelectToModel(SelectResponse select);
}

abstract class HubCategorySelectConverter implements HubCategoryConverter {

    @Autowired
    private HubCategoryRepository hubCategoryRepository;

    @Override
    public SelectResponse fromModelToSelect(HubCategory h) {
        return new SelectResponse(h.getId(), h.selectToString());
    }

    @Override
    public HubCategory fromSelectToModel(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return hubCategoryRepository.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.HubCategory, ExceptionType.ENTITY_NOT_FOUND);
        });
    }

}
