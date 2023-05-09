package com.boxi.product.response;


import com.boxi.core.response.SelectResponse;
import com.boxi.product.payload.dto.ProductAttributeDevisionSelectDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = false)
@Data
public class ProductAttributeSelectDto {
    private Long id;
    private Double fromWeight;
    private Double toWeight;
    private Double fromDim;
    private Double toDimension;
    private Long fromValue;
    private Long toValue;
    private SelectResponse product;
    private List<SelectResponse> usingProduct;
    private List<ProductAttributeDevisionSelectDto> attributeDivition;
    private SelectResponse timeCommitment;
}
