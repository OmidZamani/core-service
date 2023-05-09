package com.boxi.product.payload.converter;


import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.TimeCommitment;
import com.boxi.product.payload.dto.TimeCommitmentDto;
import com.boxi.product.repo.TimeCommitmentRepository;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TimeCommitmentConverter {
    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.from", target = "from")
    @Mapping(source = "dto.to", target = "to")
    @Mapping(source = "dto.selecttedtimeUnit.text", target = "timeUnit")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(source = "dto.description", target = "description")
    TimeCommitment fromDtoToModel(TimeCommitmentDto dto);

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.from", target = "from")
    @Mapping(source = "dto.to", target = "to")
    @Mapping(source = "dto.selecttedtimeUnit.text", target = "timeUnit")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(source = "dto.description", target = "description")
    void updateFromDto(TimeCommitmentDto dto,@MappingTarget TimeCommitment timeCommitment);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "from", target = "from")
    @Mapping(source = "to", target = "to")
    @Mapping(source = "timeUnit", target = "selecttedtimeUnit.text")

    @Mapping(source = "isActive", target = "isActive")
    @Mapping(source = "description", target = "description")
    TimeCommitmentDto fromModelToDto(TimeCommitment timeCommitment);

    TimeCommitment selectToTimeCommitment(SelectResponse select);
    SelectResponse TimeCommitmentToSelect(TimeCommitment h);

    @AfterMapping
    default void validate(TimeCommitment timeCommitment,@MappingTarget TimeCommitmentDto dto) {
        if (timeCommitment.getTimeUnit()!=null){
            dto.setSelecttedtimeUnit( new SelectResponse(timeCommitment.getTimeUnit().getValue(), timeCommitment.getTimeUnit().getType()));

        }
    }

    @AfterMapping
    default void after(TimeCommitmentDto timeCommitmentDto) {
        
    }


}

abstract class TimeCommitmentSelectConverter implements TimeCommitmentConverter {

    @Autowired
    private TimeCommitmentRepository timeCommitmentRepository;

    @Override
    public SelectResponse TimeCommitmentToSelect(TimeCommitment h) {
        return new SelectResponse(h.getId(), h.selectToString());
    }

    @Override
    public TimeCommitment selectToTimeCommitment(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return timeCommitmentRepository.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.TimeCommitment, ExceptionType.ENTITY_NOT_FOUND);
        });
    }


}