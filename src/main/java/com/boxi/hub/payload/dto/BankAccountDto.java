package com.boxi.hub.payload.dto;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.enums.BankType;
import lombok.Data;

@Data
public class BankAccountDto {
    private Long id;
    private Boolean isActive;
    private Boolean isDeleted;
    private Long accountNumber;
    private SelectResponse bank;
    private String accountOwner;
    private String accountID;
    private Long customerId;
    private Long hubId;
}
