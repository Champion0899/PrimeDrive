package com.example.PrimeDriveBackend.Mapper;

import com.example.PrimeDriveBackend.Dto.UserDto;
import com.example.PrimeDriveBackend.model.Users;

public class UserMapper {

    public UserDto toDto(Users users) {
        UserDto dto = new UserDto();
        dto.setKontoId(users.getId());
        dto.setEMail(users.getEMail());
        dto.setPasswort(users.getPassword());
        return dto;
    }

    public Users toEntity(UserDto dto) {
        Users users = new Users();
        users.setId(dto.getKontoId());
        users.setEMail(dto.getEMail());
        users.setPassword(dto.getPasswort());
        return users;
    }
}
