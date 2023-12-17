package com.homebanking.grupo13.services;

import com.homebanking.grupo13.entities.Account;
import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.entities.enums.AccountType;
import com.homebanking.grupo13.exceptions.UserAlreadyExistsException;
import com.homebanking.grupo13.exceptions.UserNotFoundException;
import com.homebanking.grupo13.mappers.AccountMapper;
import com.homebanking.grupo13.mappers.UserMapper;
import com.homebanking.grupo13.repositories.IAccountRepository;
import com.homebanking.grupo13.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IAccountRepository IAccountRepository;

    // listar por ID
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException());
        return UserMapper.userToDto(user);
    }

    public UserDto getUserByDni(Long dni) {
        User user = userRepository.findByDni(dni)
                .orElseThrow(() -> new UserNotFoundException());
        return UserMapper.userToDto(user);
    }

    //listar Todo (activos e inactivos)
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        User user = null;
        Optional<User> optionalUser = userRepository.findByDni(userDto.getDni());


        // En caso que ya exista
        if (optionalUser.isPresent()){
                throw new UserAlreadyExistsException();
        }
        userDto.setEnabled(true);
        // Sino generar un nuevo usuario

        user = UserMapper.dtoToUser(userDto);
        User savedUser = userRepository.save(user);

        // consigna 8) crear una cuenta automaticamete
        Account account = new Account();
        account.setAmount(BigDecimal.ZERO);
        account.setType(AccountType.CAJA_AHORRO_PESOS); // por defecto
        account.setAlias("codo.a.codo.alias." + (int) (Math.random() * 10000));
        account.setCbu("00000123" + (long) (Math.random() * Long.MAX_VALUE));
        account.setOwner(savedUser);
        account.setEnabled(true);
        Account newAccount = IAccountRepository.save(account);


        final AccountDto accountDto = AccountMapper.accountToDto(newAccount);
        final UserDto savedUserDto = UserMapper.userToDto(savedUser);
        savedUserDto.setAccountsDtos(List.of(accountDto));

        return savedUserDto;
    }

// update user

    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException());

        if (userDto.getNameUser() != null) {
            user.setNameUser(userDto.getNameUser());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        if (userDto.getDni() != null) {
            user.setDni(userDto.getDni());
        }
        if (userDto.getBirthday() != null) {
            user.setBirthday(userDto.getBirthday());
        }
        if (userDto.getAddress() != null) {
            user.setAddress(userDto.getAddress());
        }
        if (userDto.getEnabled() != null) {
            user.setEnabled(userDto.getEnabled());
            // Tambien actualizar el estado de las cuentas
            for (Account acc : user.getAccounts()) {
                acc.setEnabled(userDto.getEnabled());
            }
        }

        // TODO: si accounts (dto) es enviado por el usuario, son agregados a la lista de accounts


        User updatedUser = userRepository.save(user);
        return UserMapper.userToDto(updatedUser);
    }

    //Solo hay que deshabilitarlo
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        user.setEnabled(false);

        // dehabilitar todas las cuentas
        for (Account acc : user.getAccounts()) {
            acc.setEnabled(false);
        }
        User userSaved = userRepository.save(user);
        return UserMapper.userToDto(userSaved);
    }
}

