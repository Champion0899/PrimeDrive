package com.example.PrimeDriveBackend.Mapper;

import com.example.PrimeDriveBackend.Dto.PlattformNutzerkontoDto;
import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;

public class PlattformNutzerkontoMapper {

    public PlattformNutzerkontoDto toDto(PlattformNutzerkonto plattformNutzerkonto) {
        PlattformNutzerkontoDto dto = new PlattformNutzerkontoDto();
        dto.setKontoId(plattformNutzerkonto.getKontoId());
        dto.setEMail(plattformNutzerkonto.getEMail());
        dto.setPasswort(plattformNutzerkonto.getPasswort());
        return dto;
    }

    public PlattformNutzerkonto toEntity(PlattformNutzerkontoDto dto) {
        PlattformNutzerkonto plattformNutzerkonto = new PlattformNutzerkonto();
        plattformNutzerkonto.setKontoId(dto.getKontoId());
        plattformNutzerkonto.setEMail(dto.getEMail());
        plattformNutzerkonto.setPasswort(dto.getPasswort());
        return plattformNutzerkonto;
    }
}
