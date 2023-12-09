package com.homebanking.grupo13.mappers;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.repositories.IUserRepository;
import lombok.experimental.UtilityClass;
import com.homebanking.grupo13.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class AccountMapper {


    static public AccountDto accountToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setType(account.getType());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        dto.setEnabled(account.isEnabled());
        User owner=account.getOwner();
        dto.setOwner_id(owner.getId());

        return dto;
    }
    static public Account dtoToAccount(AccountDto dto){
        Account account = new Account();

        account.setAlias(dto.getAlias());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu());
        account.setEnabled(dto.isEnabled());
        account.setAmount(dto.getAmount());

        return account;
    }
}
