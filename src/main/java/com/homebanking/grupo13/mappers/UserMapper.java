package com.homebanking.grupo13.mappers;

import com.homebanking.grupo13.entities.User;
import com.homebanking.grupo13.entities.dtos.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class userMapper {

  public static User dtoToUser(UserDto dto){
    User user = new User();

    user.setId(dto.getId());
    user.setNameUser(dto.getNameUser());
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    user.setDni(user.getDni());
    user.setBirthday(dto.getBirthday());
    user.setAddress(dto.getAddress());
    user.setStatus(dto.getStatus());
    user.setCreateAt(dto.getCreateAt());
    user.setModifiedAt(dto.getModifiedAt());

    return user;
  }
  public static UserDto userToDto(User user){
    UserDto dto = new UserDto();

    dto.setId(user.getId());
    dto.setNameUser(user.getNameUser());
    dto.setEmail(user.getEmail());
    dto.setPassword(user.getPassword());
    dto.setDni(user.getDni());
    dto.setBirthday(user.getBirthday());
    dto.setAddress(user.getAddress());
    dto.setStatus(user.getStatus());
    dto.setCreateAt(user.getCreateAt());
    dto.setModifiedAt(user.getModifiedAt());

    return dto;
  }
}
