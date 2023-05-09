package com.boxi.product.payload.dto;

import com.boxi.core.request.JsonBase;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
public class ProductGroupDto extends JsonBase {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Boolean isActive;
    private Boolean isDeleted;
}
