package com.example.PrimeDriveBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer kontoId;
    private String benutzername;
    private String passwort;
    private String rolle;
    private String eMail;
}
