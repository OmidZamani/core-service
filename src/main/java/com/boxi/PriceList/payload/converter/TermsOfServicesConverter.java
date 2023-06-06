package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.Enum.ConsignmentType;
import com.boxi.PriceList.Enum.ServiceType;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.entity.TermsOfServices;
import com.boxi.PriceList.payload.dto.TermsOfServicesDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.ProductAttribute;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TermsOfServicesConverter {

    @Mapping(ignore = true, target = "serviceType")
    @Mapping(ignore = true, target = "consignmentType")
    TermsOfServicesDto fromModelToDto(TermsOfServices termsOfServices);

    @Mapping(ignore = true, target = "serviceType")
    @Mapping(ignore = true, target = "consignmentType")
    TermsOfServices fromDtoToModel(TermsOfServicesDto dto);

    Services fromTermToService(TermsOfServices termsOfServices);

    TermsOfServices fromServiceToTerms(Services services);


    TermsOfServices fromProductAttributesToTerms(ProductAttribute productAttribute);

    @AfterMapping
    default void  afterFromProductAttributesToTerms(ProductAttribute productAttribute ,@MappingTarget TermsOfServices termsOfServices){

    }

    @AfterMapping
    default void afterFromDtoToModel(TermsOfServicesDto dto, @MappingTarget TermsOfServices termsOfServices) {
        if (dto.getServiceType() != null)
            termsOfServices.setServiceType(ServiceType.findByValue(dto.getServiceType().getId()));

        if (dto.getConsignmentType() != null)
            termsOfServices.setConsignmentType(ConsignmentType.findByValue(dto.getConsignmentType().getId()));
    }

    @AfterMapping
    default void afterFromModelToDto(TermsOfServices termsOfServices, @MappingTarget TermsOfServicesDto dto) {
        if (termsOfServices.getServiceType() != null)
            dto.setServiceType(new SelectResponse(termsOfServices.getServiceType().getValue()
                    , termsOfServices.getServiceType().getType()));

        if (termsOfServices.getConsignmentType() != null)
            dto.setConsignmentType(new SelectResponse(termsOfServices.getConsignmentType().getValue(), termsOfServices.getConsignmentType().getType()));
    }

}
