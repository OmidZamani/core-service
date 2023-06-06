package com.boxi.PriceList.payload.converter;


import com.boxi.PriceList.Enum.CategoryType;
import com.boxi.PriceList.Enum.ConsignmentType;
import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.entity.PriceList;

import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.entity.TermsOfServices;
import com.boxi.PriceList.payload.dto.*;
import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.converter.CustomDevisionDetailConverter;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.product.response.ContryDevistionSelect;
import com.boxi.utils.DateUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {PriceListDetailConverter.class, PriceDetailDevisionConverter.class})
@DecoratedWith(PriceListConverters.class)
public interface PriceListConverter {

    @Mapping(target = "priceListDate", ignore = true)
    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    PriceList fromDtoToModel(PriceListDto dto);

    @Mapping(target = "priceListDate", ignore = true)
    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    void updateFromDto(PriceListDto dto, @MappingTarget PriceList attribute);

    @Mapping(target = "priceListDate", ignore = true)
    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    PriceListDto fromModelToDto(PriceList productAttribute);

    @Mapping(target = "priceListDate", ignore = true)
    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "priceListDetails", ignore = true)
    PriceListFilterDto fromDtoSelectToModel(PriceListDto dto);

    @Mapping(target = "priceListDate", ignore = true)
    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    @Mapping(target = "priceListDetails", ignore = true)
    @Mapping(source = "code", target = "priceListCode")
    PriceListDto fromExcelToDto(PriceListExcelDto excel);

    @Mapping(target = "priceListDate", ignore = true)
    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    PriceListFilterDto fromFilterMap(PriceList dto);

    @Mapping(target = "priceListDate", ignore = true)
    @Mapping(target = "validDateFrom", ignore = true)
    @Mapping(target = "validDateTo", ignore = true)
    PriceListFilterDto LoadfromFilterMap(PriceList dto);

    TermsOfServices fromPriceToTerm(PriceList priceList);

    @AfterMapping
    default void afterFilterMap(PriceList priceList, @MappingTarget PriceListFilterDto dto) {
        if (priceList.getPriceListDate() != null && priceList.getPriceListDate() != null) {

            dto.setPriceListDate(DateUtil.convertDateToJalaliDateDto(priceList.getPriceListDate()));
        }

        if (priceList.getValidDateFrom() != null && priceList.getValidDateFrom() != null) {
            dto.setValidDateFrom(DateUtil.convertDateToJalaliDateDto(priceList.getValidDateFrom()));
        }

        if (priceList.getValidDateTo() != null && priceList.getValidDateTo() != null) {
            dto.setValidDateTo(DateUtil.convertDateToJalaliDateDto(priceList.getValidDateTo()));
        }


    }


    @AfterMapping
    default void afterExcelToDto(PriceListExcelDto excel, @MappingTarget PriceListDto dto) {
        if (excel.getPriceListDateDay() != null) {
            DateDto dateDto = new DateDto();
            dateDto.setDay(Integer.valueOf(excel.getPriceListDateDay()));
            dateDto.setMonth(Integer.valueOf(excel.getPriceListDateMonth()));
            dateDto.setYear(Integer.valueOf(excel.getPriceListDateYear()));
            dto.setPriceListDate(dateDto);
        }
        if (excel.getValidDateFromDay() != null) {
            DateDto dateDto = new DateDto();
            dateDto.setDay(Integer.valueOf(excel.getValidDateFromDay()));
            dateDto.setMonth(Integer.valueOf(excel.getValidDateFromMonth()));
            dateDto.setYear(Integer.valueOf(excel.getValidDateFromYear()));
            dto.setValidDateFrom(dateDto);
        }
        if (excel.getValidDateToDay() != null) {
            DateDto dateDto = new DateDto();
            dateDto.setDay(Integer.valueOf(excel.getValidDateToDay()));
            dateDto.setMonth(Integer.valueOf(excel.getValidDateToMonth()));
            dateDto.setYear(Integer.valueOf(excel.getValidDateToYear()));
            dto.setValidDateTo(dateDto);
        }


    }

    @AfterMapping
    default void afterDtoSelectTo(PriceListDto dto, @MappingTarget PriceListFilterDto priceList) {
        if (dto.getPriceListDate() != null && dto.getPriceListDate() != null) {

            priceList.setPriceListDate(dto.getPriceListDate());
        }

        if (dto.getValidDateFrom() != null && dto.getValidDateFrom() != null) {
            priceList.setValidDateFrom(dto.getValidDateFrom());
        }

        if (dto.getValidDateTo() != null && dto.getValidDateTo() != null) {
            priceList.setValidDateTo(dto.getValidDateTo());
        }
    }


    @AfterMapping
    default void afterMapToModel(PriceListDto dto, @MappingTarget PriceList priceList) {
        if (dto.getPriceListDate() != null && dto.getPriceListDate() != null) {
            priceList.setPriceListDate(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getPriceListDate().getDay(), dto.getPriceListDate().getMonth(), dto.getPriceListDate().getYear()));
        }

        if (dto.getValidDateFrom() != null && dto.getValidDateFrom() != null) {
            priceList.setValidDateFrom(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getValidDateFrom().getDay(), dto.getValidDateFrom().getMonth(), dto.getValidDateFrom().getYear()));
        }

        if (dto.getValidDateTo() != null && dto.getValidDateTo() != null) {
            priceList.setValidDateTo(DateUtil.convertJalaliDayTimeToTimeStamp(dto.getValidDateTo().getDay(), dto.getValidDateTo().getMonth(), dto.getValidDateTo().getYear()));
        }
    }

    @AfterMapping
    default void afterMapToDto(PriceList priceList, @MappingTarget PriceListDto dto) {


        if (priceList.getPriceListDate() != null && priceList.getPriceListDate() != null) {

            dto.setPriceListDate(DateUtil.convertDateToJalaliDateDto(priceList.getPriceListDate()));
        }

        if (priceList.getValidDateFrom() != null && priceList.getValidDateFrom() != null) {
            dto.setValidDateFrom(DateUtil.convertDateToJalaliDateDto(priceList.getValidDateFrom()));
        }

        if (priceList.getValidDateTo() != null && priceList.getValidDateTo() != null) {
            dto.setValidDateTo(DateUtil.convertDateToJalaliDateDto(priceList.getValidDateTo()));
        }

    }

    @AfterMapping
    default void afterDtoToModel(PriceListDto dto, @MappingTarget PriceList priceList) {

        if (dto.getPriceListDate() != null && dto.getPriceListDate() != null) {
            priceList.setPriceListDate(DateUtil.convertDateToJalaliDateDto(dto.getPriceListDate()));
        }

        if (dto.getValidDateFrom() != null && dto.getValidDateFrom() != null) {
            priceList.setValidDateFrom(DateUtil.convertDateToJalaliDateDto(dto.getValidDateFrom()));
        }

        if (dto.getValidDateTo() != null && dto.getValidDateTo() != null) {
            priceList.setValidDateTo(DateUtil.convertDateToJalaliDateDto(dto.getValidDateTo()));
        }
    }
}

abstract class PriceListConverters implements PriceListConverter {

    @Autowired
    private CountryDevisionService countryDevisionService;

    @Autowired
    private PriceListDetailConverter priceListDetailConverter;
    @Autowired
    private CustomDevisionDetailConverter converter;

    @Override
    public PriceListFilterDto fromFilterMap(PriceList priceList) {
        PriceListFilterDto priceListFilterDto = LoadfromFilterMap(priceList);
        priceListFilterDto.setPriceListName(priceList.getPriceListName());

        List<PriceListDetailFilterDto> list = new ArrayList<>();
        for (PriceListDetail priceListDetail : priceList.getPriceListDetails()) {

            PriceListDetailFilterDto priceListDetailFilterDto = priceListDetailConverter.fromModelToFilter(priceListDetail);

            if (priceListDetail.getCustomCountryDevision() != null) {
                CustomCountryDevisionDto customCountryDevisionDto = new CustomCountryDevisionDto();
                customCountryDevisionDto.setId(priceListDetail.getCustomCountryDevision().getId());
                customCountryDevisionDto.setName(priceListDetail.getCustomCountryDevision().getName());
                customCountryDevisionDto.setCode(priceListDetail.getCustomCountryDevision().getCode());

                customCountryDevisionDto.setIsActive(priceListDetail.getCustomCountryDevision().getIsActive());

                if (priceListDetail.getCustomCountryDevision().getCustomDevisionDetails() != null)
                    customCountryDevisionDto.setCustomDevisionDetails(priceListDetail.getCustomCountryDevision().getCustomDevisionDetails().stream().map(converter::fromModelToDto).collect(Collectors.toList()));

                priceListDetailFilterDto.setCustomDevision(customCountryDevisionDto);
            }
            List<PriceDetailDevisionFilterDto> priceDetailDevisionDtos = new ArrayList<>();

            for (PriceDetailDevision priceDetailDevision : priceListDetail.getPriceDetailDevisions()) {
                PriceDetailDevisionFilterDto priceDetailDevisionDto = new PriceDetailDevisionFilterDto();

                if (priceDetailDevision.getFromCountryDevision() != null) {
                    List<ContryDevistionSelect> contryDevistionSelectsFrom = countryDevisionService.SelectTreeToParent(priceDetailDevision.getFromCountryDevision().getId());
                    priceDetailDevisionDto.setFromCountryDevision(contryDevistionSelectsFrom);
                }
                if (priceDetailDevision.getToCountryDevision() != null) {
                    List<ContryDevistionSelect> contryDevistionSelectsTo = countryDevisionService.SelectTreeToParent(priceDetailDevision.getToCountryDevision().getId());
                    priceDetailDevisionDto.setToCountryDevision(contryDevistionSelectsTo);
                }
                ConsignmentType byValue = ConsignmentType.findByValue(priceDetailDevision.getPriceListDetail().getConsignmentType());
                priceDetailDevisionDto.setConsignmentType(new SelectResponse(byValue.getValue(), byValue.getType()));
                priceListDetailFilterDto.setConsignmentType(new SelectResponse(byValue.getValue(), byValue.getType()));


                priceDetailDevisionDtos.add(priceDetailDevisionDto);

            }
            if (priceListDetail.getConsignmentType() != null) {
                ConsignmentType byValueConsignmentType = ConsignmentType.findByValue(priceListDetail.getConsignmentType());
                priceListDetailFilterDto.setConsignmentType(new SelectResponse(byValueConsignmentType.getValue(), byValueConsignmentType.getType()));
            }


            priceListDetailFilterDto.setPriceDetailDevisions(priceDetailDevisionDtos);

            if (priceListDetail.getIsParametric() != null) {

                    priceListDetailFilterDto.setIsParametric(priceListDetail.getIsParametric());
                    if (priceListDetail.getIsParametric() == true) {
                    } else {
                        if (priceListDetailFilterDto.getCustomDevision() != null)
                            priceListDetailFilterDto.setCategoryType(new SelectResponse(CategoryType.SPECIAL.getValue(), CategoryType.SPECIAL.getType()));
                        else
                            priceListDetailFilterDto.setCategoryType(new SelectResponse(CategoryType.STANDARD.getValue(), CategoryType.STANDARD.getType()));
                        priceListDetailFilterDto.setIsParametric(priceListDetail.getIsParametric());
                    }

                }
                priceListDetailFilterDto.setProduct(new SelectResponse(priceListDetail.getProduct().getId(), priceListDetail.getProduct().getName()));
                priceListDetailFilterDto.setPriceList(new SelectResponse(priceListDetail.getPriceList().getId(), priceListDetail.getPriceList().getPriceListName()));
                list.add(priceListDetailFilterDto);
                priceListFilterDto.setPriceListDetails(list);


                priceListDetailFilterDto.setPriceDetailDevisions(priceDetailDevisionDtos);
            }


            return priceListFilterDto;

        }


    }