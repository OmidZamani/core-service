package com.boxi.transport.payload.converter;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.VehicleCategory;
import com.boxi.transport.payload.dto.VehicleCategoryDto;
import com.boxi.transport.repo.VehicleCategoryRepository;
import com.boxi.utils.SelectUtil;
import org.mapstruct.DecoratedWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {})
@DecoratedWith(VehicleCategorySelectConverter.class)
public interface VehicleCategoryConverter {

    VehicleCategory fromDtoToModel(final VehicleCategoryDto dto);

    VehicleCategoryDto fromModelToDto(final VehicleCategory model);

    SelectResponse fromModelToSelect(VehicleCategory v);

    VehicleCategory fromSelectToModel(SelectResponse select);

}

abstract class VehicleCategorySelectConverter implements VehicleCategoryConverter {
    @Autowired
    private VehicleCategoryRepository repo;

    @Override
    public SelectResponse fromModelToSelect(VehicleCategory v) {
        return new SelectResponse(v.getId(), v.selectToString());
    }

    @Override
    public VehicleCategory fromSelectToModel(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return repo.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.VehicleCategory, ExceptionType.ENTITY_NOT_FOUND);
        });
    }

}