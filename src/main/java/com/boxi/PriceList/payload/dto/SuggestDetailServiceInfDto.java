package com.boxi.PriceList.payload.dto;

import java.math.BigDecimal;

public interface SuggestDetailServiceInfDto {
     Long getId();
     Long getProductId();
     Long getPriceListId();
     BigDecimal getPrice();
}
