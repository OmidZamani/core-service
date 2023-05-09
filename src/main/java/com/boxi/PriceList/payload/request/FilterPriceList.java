package com.boxi.PriceList.payload.request;

import com.boxi.PriceList.payload.dto.PriceListDetailDto;
import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterPriceList extends JsonBase {
    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
    private DateDto priceListDate;
    private DateDto validDateFrom;
    private DateDto validDateTo;
    private PriceListDetailDto priceListDetails;
    private SelectResponse classification;
    private SelectResponse product;
}
