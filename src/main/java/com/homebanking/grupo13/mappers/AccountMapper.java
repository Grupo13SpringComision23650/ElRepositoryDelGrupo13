package com.homebanking.grupo13.mappers;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {


    static public AccountDto accountToDto(Account account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setType(account.getType());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        dto.setEnabled(account.getEnabled());
        User owner = account.getOwner();
        dto.setOwner(owner.getId());
        dto.setCreated_at(account.getCreated_at());
        dto.setUpdated_at(account.getUpdated_at());

        return dto;
    }

    static public Account dtoToAccount(AccountDto dto) {
        Account account = new Account();

        account.setId(dto.getId());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setAmount(dto.getAmount());
        account.setEnabled(dto.getEnabled());   
        account.setCreated_at(dto.getCreated_at());
        account.setUpdated_at(dto.getUpdated_at());

        return account;
    }
}
