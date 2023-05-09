package com.boxi.PriceList.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterService  extends JsonBase {
    private Boolean isActive;
    private Boolean isDeleted;
    private String code;
    private String name;
    private SelectResponse type;
    private String description;
    private Long minimumOrderQuantity;
    private Date validDateFrom;
    private Date validDateTo;
    private SelectResponse product;
    private SelectResponse priceList;

}


