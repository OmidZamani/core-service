package com.boxi.product.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class ProductAttributeDto extends JsonBase {
    private Long id;
    private Double fromWeight;
    private Double toWeight;
    private Double fromDim;
    private Double toDimension;
    private Long fromValue;
    private Long toValue;
    private SelectResponse product;
    private List<UsingProductDto> usingProduct;
    private List<ProductAttributeDevisionDto> attributeDivition;
    private SelectResponse timeCommitment;
}
