package com.example.PrimeDriveBackend.Mapper;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.UserDto;
import com.example.PrimeDriveBackend.Dto.UserSafeDto;
import com.example.PrimeDriveBackend.model.Users;

/**
 * Mapper class for converting between User entities and their DTO representations.
 *
 * Provides methods for mapping full user data (UserDto), safe public user data (UserSafeDto),
 * and transforming incoming DTOs back into entity form (Users).
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class UserMapper {

    /**
     * Converts a User entity to a full UserDto containing all fields, including sensitive ones.
     *
     * @param users The User entity to convert.
     * @return A UserDto with all user data.
     */
    public UserDto toDto(Users users) {
        UserDto dto = new UserDto();
        dto.setId(users.getId());
        dto.setUsername(users.getUsername());
        dto.setPassword(users.getPassword());
        dto.setEMail(users.getEMail());
        dto.setRole(users.getRole());
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setBirthdate(users.getBirthdate());
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

    /**
     * Converts a UserDto back to a User entity.
     *
     * @param dto The UserDto to convert.
     * @return A Users entity based on the provided DTO.
     */
    public Users toEntity(UserDto dto) {
        Users users = new Users();
        users.setId(dto.getId());
        users.setUsername(dto.getUsername());
        users.setPassword(dto.getPassword());
        users.setEMail(dto.getEMail());
        users.setRole(dto.getRole());
        users.setBirthdate(dto.getBirthdate());
        users.setFirstName(dto.getFirstName());
        users.setLastName(dto.getLastName());
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

    /**
     * Converts a User entity to a UserSafeDto, omitting sensitive information like password.
     *
     * @param users The User entity to convert.
     * @return A UserSafeDto with only safe, non-sensitive fields.
     */
    public UserSafeDto toSafeDto(Users users) {
        UserSafeDto dto = new UserSafeDto();
        dto.setId(users.getId());
        dto.setUsername(users.getUsername());
        dto.setEMail(users.getEMail());
        dto.setRole(users.getRole());
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setBirthdate(users.getBirthdate());
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
