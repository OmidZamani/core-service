package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.ServiceDeliveryCustomers;
import com.boxi.PriceList.payload.dto.ServiceDeliveryCustomersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ServiceDeliveryCustomersConverter {

    @Mapping(target = "serviceDelivery.id",source = "serviceDelivery.id")
    ServiceDeliveryCustomers fromDtoToModel(ServiceDeliveryCustomersDto Dto);

    @Mapping(target = "serviceDelivery.id",source = "serviceDelivery.id")
    @Mapping(target = "serviceDelivery.text",source = "serviceDelivery.name")
    ServiceDeliveryCustomersDto fromModelToDto(ServiceDeliveryCustomers customCountryDevision);


}
