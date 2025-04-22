package com.example.PrimeDriveBackend.service;

import com.example.PrimeDriveBackend.Dto.FahrzeugDto;
import com.example.PrimeDriveBackend.Mapper.FahrzeugMapper;
import com.example.PrimeDriveBackend.model.Fahrzeug;
import com.example.PrimeDriveBackend.repository.FahrzeugRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FahrzeugService {
    private final FahrzeugRepository fahrzeugRepository;
    private final FahrzeugMapper fahrzeugMapper;

    public List<FahrzeugDto> getAllFahrzeuge() {
        return fahrzeugRepository.findAll()
                .stream()
                .map(fahrzeugMapper::toDto)
                .collect(Collectors.toList());
    }

    public FahrzeugDto getFahrzeugById(Long id) {
        return fahrzeugRepository.findById(id)
                .map(fahrzeugMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Fahrzeug nicht gefunden"));
    }

    public FahrzeugDto saveFahrzeug(FahrzeugDto dto) {
        Fahrzeug fahrzeug = fahrzeugMapper.toEntity(dto);
        return fahrzeugMapper.toDto(fahrzeugRepository.save(fahrzeug));
    }
}
