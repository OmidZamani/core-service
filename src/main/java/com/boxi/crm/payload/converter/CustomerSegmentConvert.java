package com.boxi.crm.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SegmentCustomers;
import com.boxi.crm.payload.dto.CustomerSegmentDto;
import com.boxi.crm.payload.dto.SegmentCustomersDto;
import org.hibernate.annotations.Target;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomerSegmentConvert {

    CustomerSegment fromDtoToModel(CustomerSegmentDto Dto);

    @Mapping(ignore = true,target = "segmentCustomers")
    CustomerSegmentDto fromModelToDto(CustomerSegment segment);
    @AfterMapping
    default void AfterfromModelToDto(CustomerSegment segment , @MappingTarget CustomerSegmentDto dto ){
        List<SegmentCustomersDto> segmentCustomersDtos = new ArrayList<>();
        for (SegmentCustomers segmentCustomer : segment.getSegmentCustomers()) {
            SegmentCustomersDto segmentCustomersDto = new SegmentCustomersDto();
            segmentCustomersDto.setSegment(new SelectResponse(segment.getId(),segment.getName()));
            segmentCustomersDto.setSelectcustomer(new SelectResponse(segmentCustomer.getCustomerId(),""));
            segmentCustomersDto.setId(segmentCustomer.getId());

            segmentCustomersDtos.add(segmentCustomersDto);
        }
        dto.setSegmentCustomers(segmentCustomersDtos);
    }


}
