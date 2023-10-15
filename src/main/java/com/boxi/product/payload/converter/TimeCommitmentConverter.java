package com.boxi.product.payload.converter;


import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.TimeCommitment;
import com.boxi.product.payload.dto.TimeCommitmentDto;
import com.boxi.product.repo.TimeCommitmentRepository;
import com.boxi.utils.DateUtil;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TimeCommitmentConverter {
    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.from", target = "from")
    @Mapping(source = "dto.to", target = "to")
    @Mapping(source = "dto.selecttedtimeUnit.text", target = "timeUnit")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(ignore = true, target = "pickupFrom")
    @Mapping(ignore = true, target = "pickupTo")
    @Mapping(ignore = true, target = "deliveryFrom")
    @Mapping(ignore = true, target = "deliveryTo")
    TimeCommitment fromDtoToModel(TimeCommitmentDto dto);

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.from", target = "from")
    @Mapping(source = "dto.to", target = "to")
    @Mapping(source = "dto.selecttedtimeUnit.text", target = "timeUnit")
    @Mapping(source = "dto.isActive", target = "isActive")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(ignore = true, target = "pickupFrom")
    @Mapping(ignore = true, target = "pickupTo")
    @Mapping(ignore = true, target = "deliveryFrom")
    @Mapping(ignore = true, target = "deliveryTo")
    void updateFromDto(TimeCommitmentDto dto, @MappingTarget TimeCommitment timeCommitment);

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
    default void validate(TimeCommitment timeCommitment, @MappingTarget TimeCommitmentDto dto) {
        if (timeCommitment.getTimeUnit() != null)
            dto.setSelecttedtimeUnit(new SelectResponse(timeCommitment.getTimeUnit().getValue(), timeCommitment.getTimeUnit().getType()));

        if (timeCommitment.getPickupFrom() != null)
            dto.setPickupFrom(timeCommitment.getPickupFrom().toString().substring(11, 16));

        if (timeCommitment.getPickupTo() != null)
            dto.setPickupTo(timeCommitment.getPickupTo().toString().substring(11, 16));

        if (timeCommitment.getDeliveryFrom() != null)
            dto.setDeliveryFrom(timeCommitment.getDeliveryFrom().toString().substring(11, 16));

        if (timeCommitment.getDeliveryTo() != null)
            dto.setDeliveryTo(timeCommitment.getDeliveryTo().toString().substring(11, 16));

    }

    @AfterMapping
    default void after(TimeCommitmentDto dto, @MappingTarget TimeCommitment timeCommitment) {
        if (dto.getPickupFrom() != null) {
            String[] split = dto.getPickupFrom().split(":");
            Timestamp timestamp = DateUtil.convertJalaliDayTimeToTimeStampWithTime(DateUtil.convertDateToJalaliDateDto(DateUtil.nowTimeStamp()), Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            timeCommitment.setPickupFrom(timestamp);
        }
        if (dto.getPickupTo() != null) {
            String[] split = dto.getPickupTo().split(":");
            Timestamp timestamp = DateUtil.convertJalaliDayTimeToTimeStampWithTime(DateUtil.convertDateToJalaliDateDto(DateUtil.nowTimeStamp()), Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            timeCommitment.setPickupTo(timestamp);
        }
        if (dto.getDeliveryFrom() != null) {
            String[] split = dto.getDeliveryFrom().split(":");
            Timestamp timestamp = DateUtil.convertJalaliDayTimeToTimeStampWithTime(DateUtil.convertDateToJalaliDateDto(DateUtil.nowTimeStamp()), Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            timeCommitment.setDeliveryFrom(timestamp);
        }
        if (dto.getDeliveryTo() != null) {
            String[] split = dto.getDeliveryTo().split(":");
            Timestamp timestamp = DateUtil.convertJalaliDayTimeToTimeStampWithTime(DateUtil.convertDateToJalaliDateDto(DateUtil.nowTimeStamp()), Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            timeCommitment.setDeliveryTo(timestamp);
        }


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