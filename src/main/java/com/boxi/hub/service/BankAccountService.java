package com.boxi.hub.service;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.BankAccountDto;

import java.util.List;

public interface BankAccountService {
    BankAccountDto create(BankAccountDto dto);

    BankAccountDto edit(BankAccountDto dto);

    void delete(Long id);

    BankAccountDto findById(Long id);

    List<SelectResponse> select(String filter);
}
