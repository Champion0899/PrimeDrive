package com.example.PrimeDriveBackend.Dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String role;
    private String eMail;
    private Date birthdate;
    private String address;
    private String zipCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String createdUser;
    private Date createdDate;
    private String modifiedUser;
    private Date modifiedDate;
    private Date lastLogin;
    private String lastLoginIp;
}
