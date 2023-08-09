package com.boxi.product.payload.dto;

import com.boxi.core.request.DateDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DiscountCodeDto {
    private Long id;
    private Boolean isActive;
    private String discountCode;
    private Boolean isPublic;
    private Boolean oneTimeDiscountCode;
    private BigDecimal amount;
    private BigDecimal percent;
    private DateDto validDateFrom;
    private DateDto validDateTo;
    private List<DiscountCodesArrangementDto> arrangements;
}







