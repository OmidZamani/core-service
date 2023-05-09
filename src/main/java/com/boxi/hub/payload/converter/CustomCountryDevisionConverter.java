package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.hub.payload.dto.*;
import com.boxi.hub.service.CountryDevisionService;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(CustomCountryDevisionConverterabs.class)
public interface CustomCountryDevisionConverter {

    CustomCountryDevision fromDtoToModel(CustomCountryDevisionDto Dto);

    CustomCountryDevisionDto fromModelToDto(CustomCountryDevision customCountryDevision);


    @Mapping(target = "customDevisionDetails", ignore = true)
    CustomCountryDevisionFilterDto fromModelToFilter(CustomCountryDevision countryDevision);


    @Mapping(target = "customDevisionDetails", ignore = true)
    CustomCountryDevisionDto fromExcelToDto(CustomCountryDevisionExcelDto customCountryDevisionDto);



    @AfterMapping
    default void afterExcelToDto(CustomCountryDevisionExcelDto excel,  @MappingTarget CustomCountryDevisionDto dto) {
        if (excel.getCustomDevisionDetails() != null) {
            List<CustomDevisionDetailDto> customDevisionDetails = new ArrayList<>();
            for (CustomDevisionDetailExcelDto customDevisionDetail : excel.getCustomDevisionDetails()) {
                CustomDevisionDetailDto customDevisionDetailDto = new CustomDevisionDetailDto();
                customDevisionDetailDto.setToCountryDevision(new SelectResponse(null, customDevisionDetail.getToCountryDevision()));
                customDevisionDetailDto.setFromCountryDevision(new SelectResponse(null, customDevisionDetail.getFromCountryDevision()));
                customDevisionDetailDto.setIsActive(customDevisionDetail.getIsActive());
                customDevisionDetails.add(customDevisionDetailDto);
            }
            dto.setCustomDevisionDetails(customDevisionDetails);
        }
        dto.setIsActive(excel.getIsActive());
        dto.setCode(excel.getCode());
        dto.setName(excel.getName());

    }


    void updateFromDto(CustomCountryDevisionDto dto, @MappingTarget CustomCountryDevision customCountryDevision);


}

abstract class CustomCountryDevisionConverterabs implements CustomCountryDevisionConverter {
    @Autowired
    private CountryDevisionService countryDevisionService;

    @Autowired
    private CustomCountryDevisionConverter customCountryDevisionConverter;


    public CustomCountryDevisionFilterDto fromModelToFilter(CustomCountryDevision countryDevision) {
        CustomCountryDevisionFilterDto dto = customCountryDevisionConverter.fromModelToFilter(countryDevision);
        if (countryDevision.getCustomDevisionDetails() != null) {
            List<CustomDevisionDetailFilterDto> customDevisionDetails = new ArrayList<>();
            for (CustomDevisionDetail customDevisionDetail : countryDevision.getCustomDevisionDetails()) {
                CustomDevisionDetailFilterDto customDevisionDetail1 = new CustomDevisionDetailFilterDto();
                customDevisionDetail1.setCustomDevision(new SelectResponse(customDevisionDetail.getCustomCountryDevision().getId(), customDevisionDetail.getCustomCountryDevision().getName()));
                customDevisionDetail1.setId(customDevisionDetail.getId());
                customDevisionDetail1.setIsDeleted(customDevisionDetail.getIsDeleted());
                customDevisionDetail1.setIsActive(customDevisionDetail.getIsActive());
                customDevisionDetail1.setToCountryDevision(countryDevisionService.SelectTreeToParent(customDevisionDetail.getToCountryDevision().getId()));
                customDevisionDetail1.setFromCountryDevision(countryDevisionService.SelectTreeToParent(customDevisionDetail.getFromCountryDevision().getId()));
                customDevisionDetails.add(customDevisionDetail1);
            }
            dto.setCustomDevisionDetails(customDevisionDetails);
        }
        return dto;
    }
}