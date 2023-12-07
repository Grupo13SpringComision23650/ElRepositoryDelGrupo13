package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.enums.AccountType;
import com.homebanking.grupo13.mappers.AccountMapper;
import com.homebanking.grupo13.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private static AccountRepository repository;

    private AccountService(AccountRepository repository){
        this.repository = repository;
    }

    public AccountDto getAccountById(Long id) {
        Account acc = repository.findById(id).get();
        return AccountMapper.accountToDto(acc);
    }

    public List<AccountDto> getAccounts() {
        return repository.findAll().stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    public static AccountDto createAccount(AccountDto dto) {
        dto.setAmount(BigDecimal.ZERO);
        // TODO: REFACTOR para crear diferentes tipos de cuenta inicial
        dto.setType(AccountType.CAJA_AHORRO_PESOS);
        Account newAccount = AccountMapper.dtoToAccount(dto);
        return AccountMapper.accountToDto(repository.save(newAccount));
    }

@Transactional
    public AccountDto updateAccount(Long id, AccountDto dto) {
        if (repository.existsById(id)){
            Account acc =  repository.findById(id).get();

            if (dto.getAlias() != null){
                acc.setAlias(dto.getAlias());
            }

            if (dto.getType() != null){
                acc.setType(dto.getType());
            }

            if (dto.getCbu() != null){
                acc.setCbu(dto.getCbu());
            }

            if (dto.getAmount() != null){
                acc.setAmount(dto.getAmount());
            }

            return AccountMapper.accountToDto(acc);

        } else {
            return null;
        }
    }


    public String deleteAccount(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Cuenta eliminada";
        } else {
            return "No se pudo eliminar la cuenta";
        }
    }

}
