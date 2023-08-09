package com.boxi.product.payload.dto;

import com.boxi.core.request.DateDto;
import lombok.Data;

@Data
public class FilterDiscountCodeDto {
    private Boolean isActive;
    private String discountCode;
    private Boolean isPublic;
    private Boolean oneTimeDiscountCode;
    private DateDto validDateFrom;
    private DateDto validDateTo;
}
