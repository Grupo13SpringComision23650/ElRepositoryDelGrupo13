package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.mappers.AccountMapper;
import com.homebanking.grupo13.repositories.IAccountRepository;
import com.homebanking.grupo13.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.homebanking.grupo13.exceptions.RecordNotFoundException;
@Service
public class AccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IUserRepository userRepository;


    public AccountDto getAccountById(Long id) {
        Account acc = accountRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Cuenta no encontrada id="+id));
        return AccountMapper.accountToDto(acc);
    }

    public List<AccountDto> getAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    public AccountDto createAccount(AccountDto dto) {
        Account newAccount = AccountMapper.dtoToAccount(dto);
        User owner = userRepository.getReferenceById(dto.getOwner());
        newAccount.setOwner(owner);
        newAccount.setCbu("00000123" + (int) (Math.random() * Integer.MAX_VALUE));
        newAccount.setEnabled(true);
        newAccount.setUpdated_at(LocalDateTime.now()); // Fecha de modificación
        Account accountSaved = accountRepository.save(newAccount);
        return AccountMapper.accountToDto(accountSaved);
    }


    public AccountDto updateAccount(AccountDto dto) {
        Account acc = accountRepository.findById(dto.getId())
                .orElseThrow(() -> new RecordNotFoundException("Cuenta no encontrada id="+dto.getId()));

        if (dto.getAlias() != null) {
            acc.setAlias(dto.getAlias());
        }

        if (dto.getType() != null) {
            acc.setType(dto.getType());
        }

        if (dto.getCbu() != null) {
            acc.setCbu(dto.getCbu());
        }
        if (dto.getEnabled() != null) {
            acc.setEnabled(dto.getEnabled());
        }

        if (dto.getAmount() != null) {
            acc.setAmount(acc.getAmount().add(dto.getAmount()));
        }
        if (dto.getEnabled() != null) {
            acc.setEnabled(dto.getEnabled());
        }
        Account accountModified = accountRepository.save(acc);

        return AccountMapper.accountToDto(accountModified);
    }

    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Cuenta no encontrada id="+id));

        account.setEnabled(false);
        Account accountSaved = accountRepository.save(account);
        return AccountMapper.accountToDto(accountSaved);
    }
}
