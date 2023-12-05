package com.homebanking.grupo13.services;


import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.AccountDto;
import com.homebanking.grupo13.entities.dtos.UserDto;
import com.homebanking.grupo13.mappers.UserMapper;
import com.homebanking.grupo13.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDto> getUsers1(){
        // Obtengo la lista de la entidad usuario de la db
        List<User> users = repository.findAll();
        // Mapear cada usuario de la lista hacia un dto
        List<UserDto> usersDtos = users.stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
        return usersDtos;
    }

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<User> getUsers(){
        List<User> users = repository.findAll();
        return users;
    }

    public UserDto getUserById(Long id){
        User user = repository.findById(id).get();
        user.setPassword("******");
        return UserMapper.userToDto(user);
    }

    public UserDto createUser(UserDto user){
        // TODO: agregar validacion de email existente
        User entity = UserMapper.dtoTouser(user);
        User entitySaved = repository.save(entity);
        user = UserMapper.userToDto(entitySaved);
        user.setPassword("******");
        return user;
    }

    AccountDto dto = new AccountDto();




    public String deleteUser(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "El usuario con id: " + id + " ha sido eliminado";
        } else {
            return "El usuario con id: " + id + ", no ha sido eliminado";
        }
    }

    public UserDto updateUser(Long id, UserDto dto) {
        if (repository.existsById(id)){
            User userToModify = repository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (dto.getUsername() != null){
                userToModify.setUsername(dto.getUsername());
            }

            // TODO: agregar validacion de email existente
            if (dto.getEmail() != null){
                userToModify.setEmail(dto.getEmail());
            }

            if (dto.getPassword() != null){
                userToModify.setPassword(dto.getPassword());
            }

            if (dto.getDni() != null){
                userToModify.setDni(dto.getDni());
            }

            if (dto.getAddress() != null){
                userToModify.setAddress(dto.getAddress());
            }
            if (dto.getBirthday_date() != null){
                userToModify.setBirthday_date(dto.getBirthday_date());
            }

            userToModify.setUpdated_at(LocalDateTime.now());

            User userModified = repository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }

        return null;
    }



    // Validar que existan usuarios unicos por mail
    public User validateUserByEmail(UserDto dto){
        return repository.findByEmail(dto.getEmail());
    }
}
