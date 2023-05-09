package com.boxi.excel.payload;

import com.boxi.core.request.JsonBase;
import com.boxi.excel.Enum.productType;
import lombok.Data;

@Data
public class CreateServiceExcelRequest extends JsonBase {
//    private Long Id;
    private String code;
    private String name;
    private productType type;
    private String product;
    private String priceList;
    private Long minimumOrderQuantity;
    private String validDateFromday;
    private String validDateFrommonth;
    private String validDateFromYear;
    private String validDateToday;
    private String validDatemonth;
    private String validDateToyear;
    private String description;
    private Boolean isActive;

}
