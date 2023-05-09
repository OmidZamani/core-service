package com.boxi.product.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Data
public class ProductAttributeExcelDto extends JsonBase {
    private String ParentCode;
    private String usingProduct;
    private String timeCommitment;
    private String attributeDivitionfrom;
    private String attributeDivitionto;
    private Double fromWeight;
    private Double toWeight;
    private Double fromDim;
    private Double toDimension;
    private BigDecimal fromValue;
    private BigDecimal toValue;
    private Boolean isActive;
}
