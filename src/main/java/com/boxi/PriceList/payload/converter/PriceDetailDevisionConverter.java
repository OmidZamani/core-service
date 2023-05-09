package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.payload.dto.PriceDetailDevisionDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.enums.CountryType;
import com.boxi.hub.payload.converter.CountryDevisionConverter;
import com.boxi.product.response.ContryDevistionSelect;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
uses = CountryDevisionConverter.class)
public interface PriceDetailDevisionConverter {

//    @Mapping(source = "dto.fromCountryDevision.id",target = "fromCountryDevision.id")
//    @Mapping(source = "dto.toCountryDevision.id",target = "toCountryDevision.id")
    @Mapping(ignore = true,target = "fromCountryDevision")
    @Mapping(ignore = true,target = "toCountryDevision")
    PriceDetailDevision fromDtoToModel(PriceDetailDevisionDto dto);

    @Mapping(ignore = true,target = "fromCountryDevision")
    @Mapping(ignore = true,target = "toCountryDevision")
    void updateFromDto(PriceDetailDevisionDto dto,@MappingTarget PriceDetailDevision priceDetailDevision);

    @Mapping(source = "fromCountryDevision.id",target = "fromCountryDevision.id")
    @Mapping(source = "toCountryDevision.id",target = "toCountryDevision.id")
    PriceDetailDevisionDto fromModelToDto(PriceDetailDevision priceDetailDevision);

    @AfterMapping
    default void afterMapToModel(PriceDetailDevisionDto dto, @MappingTarget PriceDetailDevision priceDetailDevision) {
        if(dto.getFromCountryDevision()!=null)
        {
            CountryDevision contryDevistionSelect = new CountryDevision();
            if(dto.getFromCountryDevision().getCountryType()!=null)
            contryDevistionSelect.setCountryType(CountryType.findByValue(dto.getFromCountryDevision().getCountryType().getId()));
            contryDevistionSelect.setId(dto.getFromCountryDevision().getId());
//            contryDevistionSelect.setText(dto.getFromCountryDevision().getText());
            priceDetailDevision.setFromCountryDevision(contryDevistionSelect);
        }

        if(dto.getToCountryDevision()!=null)
        {
            CountryDevision contryDevistionSelect = new CountryDevision();
            if(dto.getToCountryDevision().getCountryType()!=null)
                contryDevistionSelect.setCountryType(CountryType.findByValue(dto.getToCountryDevision().getCountryType().getId()));
            contryDevistionSelect.setId(dto.getToCountryDevision().getId());
//            contryDevistionSelect.setText(priceDetailDevision.getToCountryDevision().getName());

            priceDetailDevision.setToCountryDevision(contryDevistionSelect);
        }


    }

    @AfterMapping
    default void afterMapToDto(PriceDetailDevision priceDetailDevision, @MappingTarget PriceDetailDevisionDto dto) {
        if(priceDetailDevision.getFromCountryDevision()!=null)
        {
            ContryDevistionSelect contryDevistionSelect = new ContryDevistionSelect();
            if(priceDetailDevision.getFromCountryDevision().getCountryType()!=null)
            contryDevistionSelect.setCountryType(new SelectResponse(priceDetailDevision.getFromCountryDevision().getCountryType().getValue(),priceDetailDevision.getFromCountryDevision().getCountryType().getFa()));
            contryDevistionSelect.setId(priceDetailDevision.getFromCountryDevision().getId());
            contryDevistionSelect.setText(priceDetailDevision.getFromCountryDevision().getName());
            dto.setFromCountryDevision(contryDevistionSelect);

        }

        if(priceDetailDevision.getToCountryDevision()!=null)
        {
            ContryDevistionSelect contryDevistionSelect = new ContryDevistionSelect();
            if(priceDetailDevision.getToCountryDevision().getCountryType()!=null)
            contryDevistionSelect.setCountryType(new SelectResponse(priceDetailDevision.getToCountryDevision().getCountryType().getValue(),priceDetailDevision.getToCountryDevision().getCountryType().getFa()));
            contryDevistionSelect.setId(priceDetailDevision.getToCountryDevision().getId());
            contryDevistionSelect.setText(priceDetailDevision.getToCountryDevision().getName());

            dto.setToCountryDevision(contryDevistionSelect);
        }


    }

}
