package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.dtos.AccountInfo;
import com.homebanking.grupo13.entities.dtos.BankResponse;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.entities.enums.AccountType;
import com.homebanking.grupo13.entities.enums.Status;
import com.homebanking.grupo13.repositories.AccountRepository;
import com.homebanking.grupo13.repositories.IUserRepository;
import com.homebanking.grupo13.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{

  public IUserRepository userRepository;

  public AccountService accountService;

  private AccountRepository accountRepository;

  private final AccountRepository repository;

  /**
   * Creamos un User - guardando el nuevo usuario en la DB
   * Chequeamos que el usuario ya exista por el email!
     */

  public BankResponse createUser(UserDto userDto) {  // ???

    if (userRepository.existsByEmail(userDto.getEmail())) {
      BankResponse response = BankResponse.builder()
              .responseCode(Utils.USER_EXISTS_CODE)
              .responseMessage(Utils.USER_EXISTS_MESSAGE)
              .build();
      return response;
    }
    User newUser = User.builder()
            .nameUser(userDto.getNameUser())
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .dni(userDto.getDni())
            .birthday(userDto.getBirthday())
            .address(userDto.getAddress())
            .status(Status.USER_ENABLED)
            .build();
    User savedUser = userRepository.save(newUser);

    //Deberia ser con service pero no funciono
  /*
    AccountDto dto=new AccountDto();

    dto.setType(AccountType.CAJA_AHORRO_PESOS);
    dto.setAmount(BigDecimal.ZERO);
    dto.setAlias("al.ias."+Math.random()*100);
    dto.setCbu("c.b.u."+Math.random()*100);
    dto.setOwner(savedUser);
    accountService.createAccount(dto);
*/

    // esto FUNCIONO !
    Account account=new Account();
    account.setAmount(BigDecimal.ZERO);
    account.setType(AccountType.CAJA_AHORRO_PESOS);
    account.setAlias("al.ias."+Math.random()*100);
    account.setCbu("c.b.u."+Math.random()*100);
    account.setOwner(savedUser);
    /// Setear mas atributos
    Account newAccount=accountRepository.save(account);

    return BankResponse.builder()
            .responseCode(Utils.USER_CREATION_SUCCESS_CODE)
            .responseMessage(Utils.USER_CREATION_SUCCESS_MESSAGE)
            .accountInfo(AccountInfo.builder()
                    .nameUser("Nombre: "+savedUser.getNameUser()+" Domicilio: "+savedUser.getAddress()+" D.N.I: "+savedUser.getDni()+" Birthday: "+savedUser.getBirthday())
                    .build())
            .build();
  }

  /**
   * Listamos usuarios
   */
  public List<User> findAll() {
    return userRepository.findAll();
  }



}
