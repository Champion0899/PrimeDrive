package com.example.PrimeDriveBackend.Mapper;


import com.example.PrimeDriveBackend.Dto.FahrzeugDto;
import com.example.PrimeDriveBackend.model.Fahrzeug;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FahrzeugMapper {

    public FahrzeugDto toDto(Fahrzeug fahrzeug) {
        FahrzeugDto dto = new FahrzeugDto();
        dto.setImage(fahrzeug.getImage());
        dto.setId(Long.valueOf(fahrzeug.getFahrzeugId()));
        dto.setMarke(fahrzeug.getMarke());
        dto.setModell(fahrzeug.getModell());
        dto.setBaujahr(fahrzeug.getBaujahr());
        dto.setKilometerstand(fahrzeug.getKilometerstand());
        dto.setPreis(BigDecimal.valueOf(fahrzeug.getPreis()));
        return dto;
    }

    public Fahrzeug toEntity(FahrzeugDto dto) {
        Fahrzeug fahrzeug = new Fahrzeug();
        fahrzeug.setFahrzeugId(Math.toIntExact(dto.getId()));
        fahrzeug.setImage(dto.getImage());
        fahrzeug.setMarke(dto.getMarke());
        fahrzeug.setModell(dto.getModell());
        fahrzeug.setBaujahr(dto.getBaujahr());
        fahrzeug.setKilometerstand(dto.getKilometerstand());
        fahrzeug.setPreis(dto.getPreis().toBigInteger().doubleValue());
        return fahrzeug;
    }
}