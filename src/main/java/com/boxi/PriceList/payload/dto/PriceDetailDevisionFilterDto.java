package com.boxi.PriceList.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.response.ContryDevistionSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class PriceDetailDevisionFilterDto {
    private Long id;
    private List<ContryDevistionSelect> fromCountryDevision;
    private List<ContryDevistionSelect> toCountryDevision;
    private SelectResponse priceListDetail;
    private SelectResponse consignmentType;

}




