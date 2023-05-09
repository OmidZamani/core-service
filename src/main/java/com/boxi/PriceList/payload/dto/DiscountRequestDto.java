package com.boxi.PriceList.payload.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class DiscountRequestDto {
    private Long senderCustomerId;
    private Long senderAddressId;
    private List<Long> serviceIds;
    private Date orderDate;
}
