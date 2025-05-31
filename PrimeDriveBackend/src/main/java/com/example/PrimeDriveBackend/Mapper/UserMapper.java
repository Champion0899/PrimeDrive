package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.UserDto;
import com.example.PrimeDriveBackend.Dto.UserSafeDto;
import com.example.PrimeDriveBackend.model.Users;

@Component
public class UserMapper {

    public UserDto toDto(Users users) {
        UserDto dto = new UserDto();
        dto.setId(users.getId());
        dto.setUsername(users.getUsername());
        dto.setPassword(users.getPassword());
        dto.setEMail(users.getEMail());
        dto.setRole(users.getRole());
        dto.setAddress(users.getAddress());
        dto.setZipCode(users.getZipCode());
        dto.setCity(users.getCity());
        dto.setCountry(users.getCountry());
        dto.setPhoneNumber(users.getPhoneNumber());
        dto.setCreatedUser(users.getCreatedUser());
        dto.setCreatedDate(users.getCreatedDate());
        dto.setModifiedUser(users.getModifiedUser());
        dto.setModifiedDate(users.getModifiedDate());
        return dto;
    }

    public Users toEntity(UserDto dto) {
        Users users = new Users();
        users.setId(dto.getId());
        users.setUsername(dto.getUsername());
        users.setPassword(dto.getPassword());
        users.setEMail(dto.getEMail());
        users.setRole(dto.getRole());
        users.setAddress(dto.getAddress());
        users.setZipCode(dto.getZipCode());
        users.setCity(dto.getCity());
        users.setCountry(dto.getCountry());
        users.setPhoneNumber(dto.getPhoneNumber());
        users.setCreatedUser(dto.getCreatedUser());
        users.setCreatedDate(dto.getCreatedDate());
        users.setModifiedUser(dto.getModifiedUser());
        users.setModifiedDate(dto.getModifiedDate());
        return users;
    }

    public UserSafeDto toSafeDto(Users users) {
        UserSafeDto dto = new UserSafeDto();
        dto.setId(users.getId());
        dto.setUsername(users.getUsername());
        dto.setEMail(users.getEMail());
        dto.setRole(users.getRole());
        dto.setAddress(users.getAddress());
        dto.setZipCode(users.getZipCode());
        dto.setCity(users.getCity());
        dto.setCountry(users.getCountry());
        dto.setPhoneNumber(users.getPhoneNumber());
        dto.setCreatedUser(users.getCreatedUser());
        dto.setCreatedDate(users.getCreatedDate());
        dto.setModifiedUser(users.getModifiedUser());
        dto.setModifiedDate(users.getModifiedDate());
        return dto;
    }

}
