package com.boxi.hub.payload.dto;

import com.boxi.core.response.SelectResponse;
import lombok.Data;

@Data
public class BankAccountDto {
    private Long id;
    private Boolean isActive;
    private Long accountNumber;
    private SelectResponse bank;
}
