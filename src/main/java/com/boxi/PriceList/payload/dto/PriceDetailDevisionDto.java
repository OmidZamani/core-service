package com.boxi.PriceList.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.response.ContryDevistionSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class PriceDetailDevisionDto {
    private Long id;
    private ContryDevistionSelect fromCountryDevision;
    private ContryDevistionSelect toCountryDevision;
    private SelectResponse priceListDetail;
    private SelectResponse consignmentType;

}




