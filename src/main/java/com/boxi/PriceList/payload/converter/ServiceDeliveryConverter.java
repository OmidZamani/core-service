package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.Enum.DeliveryServiceType;

import com.boxi.PriceList.entity.DeliveryDiscount;
import com.boxi.PriceList.entity.ServiceDelivery;
import com.boxi.PriceList.entity.ServiceDeliveryCustomers;
import com.boxi.PriceList.payload.dto.*;
import com.boxi.PriceList.service.ServiceDeliveryCustomersService;

import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SalesChannel;
import com.boxi.crm.payload.converter.SalesChannelConverter;

import com.boxi.feign.dto.CustomerDto;
import com.boxi.utils.DateUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = SalesChannelConverter.class)
@DecoratedWith(ServiceDeliveryConverterabs.class)
public interface ServiceDeliveryConverter {

    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "service.id", source = "service.id")
    @Mapping(target = "serviceType", ignore = true)
    @Mapping(target = "deliveryDiscounts", ignore = true)
    ServiceDelivery fromDtoToModel(ServiceDeliveryDto Dto);

    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "deliveryDiscounts", ignore = true)
    @Mapping(target = "service.id", source = "service.id")
    ServiceDeliveryDto fromModelToDto(ServiceDelivery serviceDelivery);

    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "deliveryDiscounts", ignore = true)
    @Mapping(target = "service.id", source = "service.id")
    ServiceDeliveryDto fromModelToDtoc(ServiceDelivery serviceDelivery);

    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "deliveryDiscounts", ignore = true)
    @Mapping(target = "service", ignore = true)
    ServiceDeliveryDto fromExcelToDto(ServiceDeliveryExcelDto serviceDeliveryExcelDto);


    @AfterMapping
    default void afterExcelToDto(ServiceDeliveryExcelDto serviceDeliveryExcelDto, @MappingTarget ServiceDeliveryDto dto) {
        if (serviceDeliveryExcelDto.getValidDateFromYear() != null) {
            DateDto dateDto = new DateDto();
            dateDto.setDay(Integer.valueOf(serviceDeliveryExcelDto.getValidDateFromDay()));
            dateDto.setMonth(Integer.valueOf(serviceDeliveryExcelDto.getValidDateFromMonth()));
            dateDto.setYear(Integer.valueOf(serviceDeliveryExcelDto.getValidDateFromYear()));

            dto.setValidDateFrom(dateDto);
        }
        if (serviceDeliveryExcelDto.getValidDateToYear() != null) {
            DateDto dateDto = new DateDto();
            dateDto.setDay(Integer.valueOf(serviceDeliveryExcelDto.getValidDateToDay()));
            dateDto.setMonth(Integer.valueOf(serviceDeliveryExcelDto.getValidDateToMonth()));
            dateDto.setYear(Integer.valueOf(serviceDeliveryExcelDto.getValidDateToYear()));
            dto.setValidDateTo(dateDto);

        }

        if (serviceDeliveryExcelDto.getType() != null) {
            dto.setType(new SelectResponse(serviceDeliveryExcelDto.getType().getValue(), serviceDeliveryExcelDto.getType().getType()));
        }

        if (serviceDeliveryExcelDto.getSaleschannels() != null) {
            List<SelectResponse> salesChannelList = new ArrayList<>();
            for (ExcelSelectResponseDto salesChannel : serviceDeliveryExcelDto.getSaleschannels()) {


                salesChannelList.add(new SelectResponse(null, salesChannel.getCode()));
            }

            dto.setSaleschannels(salesChannelList);
        }

        if (serviceDeliveryExcelDto.getServiceDeliveryCustomers() != null) {
            dto.setServiceDeliveryCustomers(serviceDeliveryExcelDto.getServiceDeliveryCustomers());
        }
        if (serviceDeliveryExcelDto.getDeliveryDiscounts() != null) {
            List<DeliveryDiscountDto> deliveryDiscountList = new ArrayList<>();

            for (DeliveryDiscountExcelDto deliveryDiscount : serviceDeliveryExcelDto.getDeliveryDiscounts()) {
                DeliveryDiscountDto deliveryDiscountDto = new DeliveryDiscountDto();
                if (deliveryDiscount.getDiscountPercent() != null)
                    deliveryDiscountDto.setDiscountPercent(BigDecimal.valueOf(deliveryDiscount.getDiscountPercent()));

                if (deliveryDiscount.getDiscountTo() != null)
                    deliveryDiscountDto.setDiscountTo(BigDecimal.valueOf(deliveryDiscount.getDiscountTo()));

                if (deliveryDiscount.getDiscountFrom() != null)
                    deliveryDiscountDto.setDiscountFrom(BigDecimal.valueOf(deliveryDiscount.getDiscountFrom()));

                ServiceDeliveryDto serviceDeliveryDto = new ServiceDeliveryDto();
                serviceDeliveryDto.setCode(deliveryDiscount.getServiceDelivery());
                deliveryDiscountDto.setServiceDelivery(serviceDeliveryDto);
                if (deliveryDiscount.getType() != null)
                    deliveryDiscountDto.setType(new SelectResponse(deliveryDiscount.getType().getValue(), deliveryDiscount.getType().getType()));


                deliveryDiscountList.add(deliveryDiscountDto);
            }
            dto.setDeliveryDiscounts(deliveryDiscountList);

        }

        List<SelectResponse> selectResponses = new ArrayList<>();
        if (serviceDeliveryExcelDto.getCustomerSegments() != null) {
            for (ExcelSelectResponseDto customerSegment : serviceDeliveryExcelDto.getCustomerSegments()) {
                selectResponses.add(new SelectResponse(null, customerSegment.getCode()));
            }
            dto.setCustomerSegments(selectResponses);

        }
        if (serviceDeliveryExcelDto.getService() != null) {
            dto.setService(new SelectResponse(null, serviceDeliveryExcelDto.getService()));
        }


    }


    @AfterMapping
    default void afterDtoToModel(ServiceDeliveryDto dto, @MappingTarget ServiceDelivery serviceDelivery) {
        if (dto.getValidDateFrom() != null) {
            serviceDelivery.setValidDateFrom(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getValidDateFrom()));
        }
        if (dto.getValidDateTo() != null) {
            serviceDelivery.setValidDateTo(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getValidDateTo()));
        }
        if (dto.getType() != null) {
            serviceDelivery.setServiceType(DeliveryServiceType.findByValue(dto.getType().getId()));
        }
    }

    @AfterMapping
    default void afterModelToDto(ServiceDelivery serviceDelivery, @MappingTarget ServiceDeliveryDto dto) {
        if (serviceDelivery.getValidDateTo() != null && serviceDelivery.getValidDateTo() != null) {
            dto.setValidDateTo(DateUtil.convertDateToJalaliDateDto(serviceDelivery.getValidDateTo()));
        }
        if (serviceDelivery.getValidDateFrom() != null && serviceDelivery.getValidDateFrom() != null) {
            dto.setValidDateFrom(DateUtil.convertDateToJalaliDateDto(serviceDelivery.getValidDateFrom()));
        }

        if (serviceDelivery.getServiceType() != null) {
            DeliveryServiceType byValue = DeliveryServiceType.findByValue(serviceDelivery.getServiceType().getValue());
            dto.setType(new SelectResponse(Objects.requireNonNull(byValue).getValue(), byValue.getType()));
        }

        if (serviceDelivery.getService() != null) {
            if(serviceDelivery.getService().getName()!=null)
            dto.setService(new SelectResponse(serviceDelivery.getService().getId(),serviceDelivery.getService().getName() ));

        }
        if (serviceDelivery.getSaleschannels() != null) {
            List<SelectResponse> selectResponses = new ArrayList<>();
            for (SalesChannel saleschannel : serviceDelivery.getSaleschannels()) {
                selectResponses.add(new SelectResponse(saleschannel.getId(), saleschannel.getName()));
            }
            dto.setSaleschannels(selectResponses);

        }
        if (serviceDelivery.getDeliveryDiscounts() != null) {
            List<DeliveryDiscountDto> deliveryDiscountList = new ArrayList<>();
            for (DeliveryDiscount deliveryDiscount : serviceDelivery.getDeliveryDiscounts()) {
                DeliveryDiscountDto deliveryDiscountDto = new DeliveryDiscountDto();
                deliveryDiscountDto.setId(deliveryDiscount.getId());
                deliveryDiscountDto.setDiscountTo(deliveryDiscount.getDiscountTo());
                deliveryDiscountDto.setDiscountFrom(deliveryDiscount.getDiscountFrom());
                deliveryDiscountDto.setDiscountPercent(deliveryDiscount.getDiscountPercent());
                deliveryDiscountDto.setIsActive(deliveryDiscount.getIsActive());
                DeliveryServiceType byValue = DeliveryServiceType.findByValue(deliveryDiscount.getType());
                deliveryDiscountDto.setType(new SelectResponse(Objects.requireNonNull(byValue).getValue(), byValue.getType()));
                deliveryDiscountList.add(deliveryDiscountDto);
            }
            dto.setDeliveryDiscounts(deliveryDiscountList);

        }
        if (serviceDelivery.getServiceDeliveryCustomers() != null) {
            List<ServiceDeliveryCustomersDto> serviceDeliveryCustomers = new ArrayList<>();
            for (ServiceDeliveryCustomers serviceDeliveryCustomer : serviceDelivery.getServiceDeliveryCustomers()) {
                ServiceDeliveryCustomersDto serviceDeliveryCustomersDto = new ServiceDeliveryCustomersDto();
                serviceDeliveryCustomersDto.setId(serviceDeliveryCustomer.getId());
                serviceDeliveryCustomers.add(serviceDeliveryCustomersDto);
            }
            dto.setServiceDeliveryCustomers(serviceDeliveryCustomers);

        }
        List<SelectResponse> selectResponses = new ArrayList<>();

        if (serviceDelivery.getCustomerSegments() != null) {
            for (CustomerSegment customerSegment : serviceDelivery.getCustomerSegments()) {
                selectResponses.add(new SelectResponse(customerSegment.getId(), customerSegment.getName()));
            }
            dto.setCustomerSegments(selectResponses);
        }

    }


}

abstract class ServiceDeliveryConverterabs implements ServiceDeliveryConverter {

    @Autowired
    private ServiceDeliveryCustomersService service;

    public ServiceDeliveryDto fromModelToDto(ServiceDelivery serviceDelivery) {
        ServiceDeliveryDto serviceDeliveryDto = fromModelToDtoc(serviceDelivery);
        List<ServiceDeliveryCustomersDto> serviceDeliveryCustomersList = new ArrayList<>();
        for (ServiceDeliveryCustomersDto serviceDeliveryCustomer : serviceDeliveryDto.getServiceDeliveryCustomers()) {
            ServiceDeliveryCustomersDto serviceDeliveryCustomersDto = new ServiceDeliveryCustomersDto();
            CustomerDto customerDto = service.loadCustomerById(serviceDeliveryCustomer.getId());
            serviceDeliveryCustomersDto.setCustomerName(customerDto.getName());
            serviceDeliveryCustomersDto.setText(customerDto.getName());
            serviceDeliveryCustomersDto.setId(customerDto.getId());
            serviceDeliveryCustomersList.add(serviceDeliveryCustomersDto);
        }
        serviceDeliveryDto.setServiceDeliveryCustomers(serviceDeliveryCustomersList);

        return serviceDeliveryDto;

    }
}