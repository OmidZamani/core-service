package com.boxi.PriceList.payload.dto;


import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class PriceListDto extends JsonBase {

    private Long id;
    private Boolean isActive;
    private Boolean isDeleted;
    private String priceListCode;
    private String priceListName;
    private DateDto priceListDate;
    private DateDto validDateFrom;
    private DateDto validDateTo;
    private List<PriceListDetailDto> priceListDetails;

}







