package com.boxi.product.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductAttributeSelectDto {
    private Long id;
    private Double fromWeight;
    private Double toWeight;
    private Double fromDim;
    private Double toDimension;
    private Long fromValue;
    private Long toValue;
    private SelectResponse product;
    private List<UsingProductDto> usingProduct;
    private List<ProductAttributeDevisionSelectDto> attributeDivition;
    private SelectResponse timeCommitment;
}
