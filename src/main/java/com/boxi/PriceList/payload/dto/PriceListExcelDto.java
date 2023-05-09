package com.boxi.PriceList.payload.dto;


import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class PriceListExcelDto extends JsonBase {


    private String code;
    private String name;
    private String priceListDateYear;
    private String priceListDateMonth;
    private String priceListDateDay;
    private String validDateFromYear;
    private String validDateFromMonth;
    private String validDateFromDay;
    private String validDateToYear;
    private String validDateToMonth;
    private String validDateToDay;
    private Boolean isActive;
    private List<PriceListDetailExcelDto> priceListDetails;
}







