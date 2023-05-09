package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.payload.dto.*;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.product.response.ContryDevistionSelect;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = PriceDetailDevisionConverter.class)
@DecoratedWith(PriceListDetailConverters.class)
public interface PriceListDetailConverter {


    @Mapping(source = "dto.consignmentType.id", target = "consignmentType")
    @Mapping(source = "dto.customDevision.id", target = "customCountryDevision.id")
    PriceListDetail fromDtoToModel(PriceListDetailDto dto);

    @Mapping(source = "dto.consignmentType.id", target = "consignmentType")
    void updateFromDto(PriceListDetailDto dto, @MappingTarget PriceListDetail attribute);


    @Mapping(source = "consignmentType", target = "consignmentType.id")
    @Mapping(target = "customDevision.id", source = "customCountryDevision.id")
    PriceListDetailDto fromModelToDto(PriceListDetail priceListDetail);


    @Mapping(target = "customDevision", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "consignmentType", ignore = true)
    PriceListDetailDto fromExcelToDto(PriceListDetailExcelDto priceListDetail);


    @Mapping(target = "priceDetailDevisions", ignore = true)
    PriceListDetailFilterDto fromDtoToFilter(PriceListDetailDto priceListDetail);

    @Mapping(target = "priceDetailDevisions", ignore = true)
    @Mapping(target = "consignmentType", ignore = true)
    PriceListDetailFilterDto fromModelToFilter(PriceListDetail priceListDetail);

    @AfterMapping
    default void afterfromDtoToModel(PriceListDetailDto dto, @MappingTarget PriceListDetail priceListDetail) {
        if (dto.getPriceDetailDevisions() != null && dto.getPriceDetailDevisions().size() != 0) {
            List<PriceDetailDevision> list = new ArrayList<>();
            for (PriceDetailDevision priceDetailDevision : priceListDetail.getPriceDetailDevisions()) {
                list.add(priceDetailDevision);
            }
            if (list != null)
                priceListDetail.setPriceDetailDevisions(list);

        }
    }

    @AfterMapping
    default void validate(PriceListDetail priceListDetail, @MappingTarget PriceListDetailDto dto) {
        if (priceListDetail.getPriceList() != null) {
            dto.setPriceList(new SelectResponse(priceListDetail.getPriceList().getId(), priceListDetail.getPriceList().getPriceListName()));
        }

        if (priceListDetail.getProduct() != null) {
            dto.setProduct(new SelectResponse(priceListDetail.getProduct().getId(), priceListDetail.getProduct().getName()));
        }

    }


}

abstract class PriceListDetailConverters implements PriceListDetailConverter {

    @Autowired
    private CountryDevisionService countryDevisionService;

    @Override
    public PriceListDetailFilterDto fromDtoToFilter(PriceListDetailDto priceListDetail) {
        PriceListDetailFilterDto priceListDetailFilterDto = new PriceListDetailFilterDto();
        List<PriceDetailDevisionFilterDto> priceDetailDevisionDtos = new ArrayList<>();

        for (PriceDetailDevisionDto priceDetailDevision : priceListDetail.getPriceDetailDevisions()) {
            PriceDetailDevisionFilterDto priceDetailDevisionDto = new PriceDetailDevisionFilterDto();
            List<ContryDevistionSelect> contryDevistionSelectsFrom = countryDevisionService.SelectTreeToParent(priceDetailDevision.getFromCountryDevision().getId());
            priceDetailDevisionDto.setFromCountryDevision(contryDevistionSelectsFrom);
            List<ContryDevistionSelect> contryDevistionSelectsTo = countryDevisionService.SelectTreeToParent(priceDetailDevision.getToCountryDevision().getId());
            priceDetailDevisionDto.setToCountryDevision(contryDevistionSelectsTo);
            priceDetailDevisionDtos.add(priceDetailDevisionDto);
        }


        priceListDetailFilterDto.setPriceDetailDevisions(priceDetailDevisionDtos);
        return priceListDetailFilterDto;

    }


}