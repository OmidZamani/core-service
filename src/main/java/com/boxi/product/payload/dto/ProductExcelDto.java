package com.boxi.product.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = false)
@Data
public class ProductExcelDto extends JsonBase {


    private String code;
    private String name;
    private String productGroup;
    private Boolean isActive;

    private List<ProductAttributeExcelDto> productAttribute;

}
