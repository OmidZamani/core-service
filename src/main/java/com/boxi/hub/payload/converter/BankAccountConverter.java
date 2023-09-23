package com.boxi.hub.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.BankAccount;
import com.boxi.hub.enums.BankType;
import com.boxi.hub.payload.dto.BankAccountDto;
import org.mapstruct.*;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY, componentModel = "spring")
public interface BankAccountConverter {
    @Mapping(ignore = true,target = "bank")
    BankAccountDto fromModelToDto(BankAccount bankAccount);

    @Mapping(ignore = true,target = "bank")
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
