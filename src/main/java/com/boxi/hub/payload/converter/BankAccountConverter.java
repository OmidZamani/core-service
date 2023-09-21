package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.BankAccount;
import com.boxi.hub.enums.BankType;
import com.boxi.hub.payload.dto.BankAccountDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY, componentModel = "spring")
public interface BankAccountConverter {
    BankAccountDto fromModelToDto(BankAccount bankAccount);

    BankAccount fromDtoToModel(BankAccountDto dto);

    @AfterMapping
    default void afterFromModelToDto(BankAccount bankAccount, @MappingTarget BankAccountDto dto) {
        if (bankAccount.getBank() != null)
            dto.setBank(new SelectResponse(bankAccount.getBank().getValue(), bankAccount.getBank().getFa()));
    }

    @AfterMapping

    default void afterFromDtoToModel(BankAccountDto dto, @MappingTarget BankAccount bankAccount) {
        if (dto.getBank() != null) {
            bankAccount.setBank(BankType.findByValue(dto.getBank().getId()));
        }
    }
}
