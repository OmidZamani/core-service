package com.boxi.hub.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.BankAccount;
import com.boxi.hub.payload.converter.BankAccountConverter;
import com.boxi.hub.payload.dto.BankAccountDto;
import com.boxi.hub.repo.BankAccountRepository;
import com.boxi.hub.service.BankAccountService;
import org.jsoup.helper.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountConverter bankAccountConverter;
    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountConverter bankAccountConverter
            , BankAccountRepository bankAccountRepository) {
        this.bankAccountConverter = bankAccountConverter;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccountDto create(BankAccountDto dto) {
        return saveCreate(dto);
    }

    @Override
    public BankAccountDto edit(BankAccountDto dto) {

        return saveEdit(dto);
    }

    @Override
    public void delete(Long id) {
        if (isExists(id)) {
            bankAccountRepository.deleteById(id);
        } else throw BusinessException.entityNotFoundException(EntityType.BankAccount, "bank.account.not.found");
    }

    @Override
    public BankAccountDto findById(Long id) {
        if (isExists(id)) {
            return bankAccountConverter.fromModelToDto(bankAccountRepository.findById(id).orElseThrow());
        } else throw BusinessException.entityNotFoundException(EntityType.BankAccount, "bank.account.not.found");

    }

    @Override
    public List<SelectResponse> select(String filter) {
        return bankAccountRepository.findAll((Specification<BankAccount>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList();
            if (!StringUtil.isBlank(filter))
                predicates.add(criteriaBuilder.like(root.get("accountNumber").as(String.class), "%" + filter + "%"));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }).stream().map(this::toSelect).collect(Collectors.toList());
    }

    private SelectResponse toSelect(BankAccount bankAccount) {
        return new SelectResponse(bankAccount.getId(), bankAccount.getAccountNumber().toString()+" - "+bankAccount.getBank().getFa());
    }

    private BankAccountDto saveEdit(BankAccountDto dto) {
        if (isExists(dto.getId())) {
            BankAccount bankAccount = bankAccountConverter.fromDtoToModel(dto);
            bankAccount.setIsDeleted(false);
            return bankAccountConverter.fromModelToDto(bankAccountRepository.save(bankAccount));
        } else throw BusinessException.entityNotFoundException(EntityType.BankAccount, "bank.account.not.found");

    }

    private boolean isExists(Long id) {
        return bankAccountRepository.existsById(id);
    }

    private BankAccountDto saveCreate(BankAccountDto dto) {
        BankAccount bankAccount = bankAccountConverter.fromDtoToModel(dto);
        bankAccount.setIsDeleted(false);
        bankAccount.setId(null);
        return bankAccountConverter.fromModelToDto(bankAccountRepository.save(bankAccount));


    }
}
