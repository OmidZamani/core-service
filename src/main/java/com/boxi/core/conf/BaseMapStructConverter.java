package com.boxi.core.conf;
import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;



public interface BaseMapStructConverter<M, D> {


   M fromDtoToModel(final D dto);

    default Optional<M> fromDtoToOptionalModel(final D dto) {
        return Optional.ofNullable(this.fromDtoToModel(dto));
    }

    @IterableMapping(nullValueMappingStrategy= NullValueMappingStrategy.RETURN_DEFAULT)
    List<M> fromDtosToModels(final Collection<D> dtos);


    D fromModelToDto(final M model);

    default Optional<D> fromModelToOptionalDto(final M model) {
        return Optional.ofNullable(this.fromModelToDto(model));
    }

    @IterableMapping(nullValueMappingStrategy=NullValueMappingStrategy.RETURN_DEFAULT)
    List<D> fromModelsToDtos(final Collection<M> models);


}