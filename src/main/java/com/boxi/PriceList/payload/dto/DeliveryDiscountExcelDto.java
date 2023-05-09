package com.boxi.PriceList.payload.dto;


import com.boxi.PriceList.Enum.DeliveryDiscountType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryDiscountExcelDto {

    private String serviceDelivery;
    private DeliveryDiscountType type;
    private Long discountPercent;
    private Long discountFrom;
    private Long discountTo;
    private Long discountRialFrom;
    private Long discountRialTo;
    private Boolean isActive;
    private Boolean isDeleted;
}






