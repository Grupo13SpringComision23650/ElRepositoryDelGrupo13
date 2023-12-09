package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.enums.AccountType;
import com.homebanking.grupo13.exceptions.AccountNotFoundException;
import com.homebanking.grupo13.exceptions.UserNotFoundException;
import com.homebanking.grupo13.mappers.AccountMapper;
import com.homebanking.grupo13.mappers.UserMapper;
import com.homebanking.grupo13.repositories.AccountRepository;
import com.homebanking.grupo13.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private IUserRepository userRepository;

    private AccountService(AccountRepository repository,IUserRepository userRepository){
        this.repository = repository;
        this.userRepository=userRepository;
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

    public AccountDto createAccount(AccountDto dto) {

        Account newAccount = AccountMapper.dtoToAccount(dto);

        User owner=userRepository.getReferenceById(dto.getOwner_id());
        newAccount.setOwner(owner);

        newAccount.setEnabled(true);
        Account accountSaved=repository.save(newAccount);
        return AccountMapper.accountToDto(accountSaved);
    }


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
            if(dto.getEnabled() != null ) {
                acc.setEnabled(dto.getEnabled());
            }

            if (dto.getAmount() != null){
                acc.setAmount(acc.getAmount().add(dto.getAmount()));
            }
            Account accountModified = repository.save(acc);

            return AccountMapper.accountToDto(accountModified);

        } else {
            throw new RuntimeException("Se ejecuto la rama del false en AccountService.updateAccount");
        }
    }

    // TODO: Deshabilitar la cuenta (no eliminar)
    public AccountDto deleteAccount(Long id) {
        Account account = repository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Cuenta no encontrada"));

        account.setEnabled(false);
        Account accountSaved=repository.save(account);
        return AccountMapper.accountToDto(accountSaved);
    }
}
