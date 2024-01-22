package com.boxi.hub.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.BankAccount;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SharePercentageDto {

    private Long id;
    private Boolean isActive;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private BigDecimal amount;
    private Short version;
    private Date validFrom;
    private Date validTo;
    private SelectResponse bankAccount;
}
