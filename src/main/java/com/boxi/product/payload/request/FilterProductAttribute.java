package com.boxi.product.payload.request;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.entity.Product;
import com.boxi.product.entity.ProductAttribute;
import com.boxi.product.entity.ProductAttributeDevision;
import com.boxi.product.entity.TimeCommitment;
import com.boxi.product.payload.dto.ProductAttributeDevisionDto;
import com.boxi.product.payload.dto.TimeCommitmentDto;
import com.boxi.product.payload.dto.UsingProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class FilterProductAttribute extends JsonBase {
    private Long id;
    private Double fromWeight;
    private Double toWeight;
    private Double fromDim;
    private Double toDimension;
    private Long fromValue;
    private Long toValue;
    private SelectResponse product;
    private TimeCommitmentDto timeCommitment;
    private UsingProductDto usingProductDto;
    private ProductAttributeDevisionDto productAttributeDevisionsDto;
}

