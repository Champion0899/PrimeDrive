package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PrimeDriveBackend.dto.VehicleEngineDto;
import com.example.PrimeDriveBackend.mapper.VehicleEngineMapper;
import com.example.PrimeDriveBackend.model.VehicleEngine;
import com.example.PrimeDriveBackend.repository.VehicleEngineRepository;

@ExtendWith(MockitoExtension.class)
class VehicleEngineServiceTest {

    @Mock
    private VehicleEngineRepository vehicleEngineRepository;

    @Mock
    private VehicleEngineMapper vehicleEngineMapper;

    @InjectMocks
    private VehicleEngineService vehicleEngineService;

    @Test
    void getAllEnginesReturnsDtos() {
        VehicleEngine engine = new VehicleEngine();
        engine.setId("eng-1");
        VehicleEngineDto dto = new VehicleEngineDto();
        dto.setId("eng-1");

        when(vehicleEngineRepository.findAll()).thenReturn(List.of(engine));
        when(vehicleEngineMapper.toDto(engine)).thenReturn(dto);

        List<VehicleEngineDto> result = vehicleEngineService.getAllEngines();

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void getEngineByIdReturnsDto() {
        VehicleEngine engine = new VehicleEngine();
        engine.setId("eng-1");
        VehicleEngineDto dto = new VehicleEngineDto();
        dto.setId("eng-1");

        when(vehicleEngineRepository.findById("eng-1")).thenReturn(Optional.of(engine));
        when(vehicleEngineMapper.toDto(engine)).thenReturn(dto);

        VehicleEngineDto result = vehicleEngineService.getEngineById("eng-1");

        assertEquals(dto, result);
    }

    @Test
    void getEngineByIdThrowsWhenMissing() {
        when(vehicleEngineRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleEngineService.getEngineById("missing"));
    }

    @Test
    void saveEnginePersists() {
        VehicleEngineDto dto = new VehicleEngineDto();
        VehicleEngine engine = new VehicleEngine();

        when(vehicleEngineMapper.toEntity(dto)).thenReturn(engine);
        when(vehicleEngineRepository.save(engine)).thenReturn(engine);
        when(vehicleEngineMapper.toDto(engine)).thenReturn(dto);

        VehicleEngineDto result = vehicleEngineService.saveEngine(dto);

        assertEquals(dto, result);
        verify(vehicleEngineRepository).save(engine);
    }

    @Test
    void updateEnginePersists() {
        VehicleEngineDto dto = new VehicleEngineDto();
        VehicleEngine existing = new VehicleEngine();
        existing.setId("eng-1");
        VehicleEngine updated = new VehicleEngine();

        when(vehicleEngineRepository.findById("eng-1")).thenReturn(Optional.of(existing));
        when(vehicleEngineMapper.toEntity(dto)).thenReturn(updated);
        when(vehicleEngineRepository.save(updated)).thenReturn(updated);
        when(vehicleEngineMapper.toDto(updated)).thenReturn(dto);

        VehicleEngineDto result = vehicleEngineService.updateEngine("eng-1", dto);

        assertEquals(dto, result);
        verify(vehicleEngineRepository).save(updated);
    }

    @Test
    void updateEngineThrowsWhenMissing() {
        VehicleEngineDto dto = new VehicleEngineDto();
        when(vehicleEngineRepository.findById("eng-1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleEngineService.updateEngine("eng-1", dto));
    }

    @Test
    void deleteEngineRemoves() {
        when(vehicleEngineRepository.existsById("eng-1")).thenReturn(true);

        vehicleEngineService.deleteEngine("eng-1");

        verify(vehicleEngineRepository).deleteById("eng-1");
    }

    @Test
    void deleteEngineThrowsWhenMissing() {
        when(vehicleEngineRepository.existsById("eng-1")).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> vehicleEngineService.deleteEngine("eng-1"));
    }
}
