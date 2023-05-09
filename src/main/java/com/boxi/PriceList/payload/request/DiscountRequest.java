package com.boxi.PriceList.payload.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DiscountRequest {
    private Long senderCustomerId;
    private Long senderAddressId;
    private List<Long> serviceIds;
    private Date orderDate;
}
