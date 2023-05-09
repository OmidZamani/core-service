package com.boxi.PriceList.payload.converter;


import com.boxi.PriceList.Enum.ServiceType;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import com.boxi.utils.DateUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ServiceConvertor {

    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "type", source = "type.id")
    Services fromDtoToModel(ServiceDto dto);

    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "type", source = "type.id")
    void updateFromDto(ServiceDto dto, @MappingTarget Services services);

    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "type.id", source = "type")
    ServiceDto fromModelToDto(Services services);

    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "priceList", ignore = true)
    ServiceDto fromExcelToDto(CreateServiceExcelRequest createServiceExcelRequest);




    @AfterMapping
    default void afterExcelToDto(CreateServiceExcelRequest createServiceExcelRequest, @MappingTarget ServiceDto dto) {
        if (createServiceExcelRequest.getValidDateFromYear() != null) {
            DateDto dateDto = new DateDto();
            dateDto.setYear(Integer.valueOf(createServiceExcelRequest.getValidDateFromYear()));
            dateDto.setMonth(Integer.valueOf(createServiceExcelRequest.getValidDateFrommonth()));
            dateDto.setDay(Integer.valueOf(createServiceExcelRequest.getValidDateFromday()));
            dto.setValidDateFrom(dateDto);
        }
        if (createServiceExcelRequest.getValidDateToyear() != null) {
            DateDto dateDto = new DateDto();
            dateDto.setYear(Integer.valueOf(createServiceExcelRequest.getValidDateToyear()));
            dateDto.setMonth(Integer.valueOf(createServiceExcelRequest.getValidDatemonth()));
            dateDto.setDay(Integer.valueOf(createServiceExcelRequest.getValidDateToday()));
            dto.setValidDateTo(dateDto);
        }
        if (createServiceExcelRequest.getType() != null) {
            dto.setType(new SelectResponse(createServiceExcelRequest.getType().getValue(),createServiceExcelRequest.getType().getType()));
        }

        if (createServiceExcelRequest.getPriceList() != null) {
            dto.setPriceList(new SelectResponse(null,createServiceExcelRequest.getPriceList()));
        }
        if (createServiceExcelRequest.getProduct() != null) {
            dto.setProduct(new SelectResponse(null,createServiceExcelRequest.getProduct()));
        }




    }

    @AfterMapping
    default void afterDtoToModel(ServiceDto dto, @MappingTarget Services services) {
        if (dto.getValidDateFrom() != null) {
            services.setValidDateFrom(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getValidDateFrom()));
        }
        if (dto.getValidDateTo() != null) {
            services.setValidDateTo(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getValidDateTo()));
        }


    }

    @AfterMapping
    default void afterModelToDto(Services services, @MappingTarget ServiceDto dto) {
        if (services.getValidDateTo() != null && services.getValidDateTo() != null) {
            dto.setValidDateTo(DateUtil.convertDateToJalaliDateDto(services.getValidDateTo()));
        }
        if (services.getValidDateFrom() != null && services.getValidDateFrom() != null) {
            dto.setValidDateFrom(DateUtil.convertDateToJalaliDateDto(services.getValidDateFrom()));
        }
        if(services.getProduct()!=null){
            dto.setProduct(new SelectResponse(services.getProduct().getId(),services.getProduct().getName()));
        }
        if(services.getPriceList()!=null){
            dto.setPriceList(new SelectResponse(services.getPriceList().getId(),services.getPriceList().getPriceListName()));
        }
        if(services.getType()!=null){
            ServiceType byValue = ServiceType.findByValue(services.getType());
            dto.setType(new SelectResponse(byValue.getValue(),byValue.getType()));
        }


    }
    @AfterMapping
    default void afterMapToDto(ServiceDto dto, @MappingTarget Services services) {
        if (services.getValidDateTo() != null && services.getValidDateTo() != null) {
            dto.setValidDateTo(DateUtil.convertDateToJalaliDateDto(services.getValidDateTo()));
        }
        if (services.getValidDateFrom() != null && services.getValidDateFrom() != null) {
            dto.setValidDateFrom(DateUtil.convertDateToJalaliDateDto(services.getValidDateFrom()));
        }
    }



}


