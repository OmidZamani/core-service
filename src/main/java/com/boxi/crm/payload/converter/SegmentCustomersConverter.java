package com.boxi.crm.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.SegmentCustomers;
import com.boxi.crm.payload.dto.SegmentCustomersDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SegmentCustomersConverter {

    SegmentCustomers fromDtoToModel(SegmentCustomersDto Dto);

    SegmentCustomersDto fromModelToDto(SegmentCustomers segment);

    @AfterMapping
    default void AfterfromModelToDto(SegmentCustomers  segmentCustomers, @MappingTarget SegmentCustomersDto dto){
        if(segmentCustomers.getSegment()!=null)
        dto.setSegment(new SelectResponse(segmentCustomers.getSegment().getId(),segmentCustomers.getSegment().getName()));

        if (segmentCustomers.getCustomerId()!=null)
        dto.setSelectcustomer(new SelectResponse(segmentCustomers.getCustomerId(),""));
    }

}
