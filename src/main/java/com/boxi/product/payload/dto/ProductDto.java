package com.boxi.product.payload.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = false)
@Data
public class ProductDto extends JsonBase {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
    private SelectResponse productGroup;
    private List<ProductAttributeDto> Attribute;

}
