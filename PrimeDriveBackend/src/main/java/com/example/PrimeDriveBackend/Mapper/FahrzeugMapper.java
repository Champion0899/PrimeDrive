package com.example.PrimeDriveBackend.Mapper;


import com.example.PrimeDriveBackend.Dto.FahrzeugDto;
import com.example.PrimeDriveBackend.model.Fahrzeug;
import org.springframework.stereotype.Component;

@Component
public class FahrzeugMapper {

    public FahrzeugDto toDto(Fahrzeug fahrzeug) {
        FahrzeugDto dto = new FahrzeugDto();
        dto.setId(fahrzeug.getFahrzeugId());
        dto.setMarke(fahrzeug.getMarke());
        dto.setModell(fahrzeug.getModell());
        dto.setBaujahr(fahrzeug.getBaujahr());
        dto.setKilometerstand(fahrzeug.getKilometerstand());
        dto.setPreis(fahrzeug.getPreis());
        return dto;
    }

    public Fahrzeug toEntity(FahrzeugDto dto) {
        Fahrzeug fahrzeug = new Fahrzeug();
        fahrzeug.setFahrzeugId(dto.getId());
        fahrzeug.setMarke(dto.getMarke());
        fahrzeug.setModell(dto.getModell());
        fahrzeug.setBaujahr(dto.getBaujahr());
        fahrzeug.setKilometerstand(dto.getKilometerstand());
        fahrzeug.setPreis(dto.getPreis());
        return fahrzeug;
    }
}