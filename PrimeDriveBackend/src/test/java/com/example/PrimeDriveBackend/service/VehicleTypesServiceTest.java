package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PrimeDriveBackend.dto.VehicleTypesDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.VehicleTypesMapper;
import com.example.PrimeDriveBackend.model.VehicleTypes;
import com.example.PrimeDriveBackend.repository.VehicleTypesRepository;

@ExtendWith(MockitoExtension.class)
class VehicleTypesServiceTest {

    @Mock
    private VehicleTypesRepository vehicleTypesRepository;

    @Mock
    private VehicleTypesMapper vehicleTypesMapper;

    @InjectMocks
    private VehicleTypesService vehicleTypesService;

    private VehicleTypes suvEntity;
    private VehicleTypesDto suvDto;

    @BeforeEach
    void setUp() {
        suvEntity = new VehicleTypes("1111-aaaa", "SUV");
        suvDto = new VehicleTypesDto("1111-aaaa", "SUV");
    }

    @Test
    void getAllTypesReturnsMappedDtos() {
        when(vehicleTypesRepository.findAll()).thenReturn(List.of(suvEntity));
        when(vehicleTypesMapper.toDto(suvEntity)).thenReturn(suvDto);

        List<VehicleTypesDto> result = vehicleTypesService.getAllTypes();

        assertEquals(1, result.size());
        assertEquals(suvDto, result.get(0));
        verify(vehicleTypesRepository).findAll();
        verify(vehicleTypesMapper).toDto(suvEntity);
    }

    @Test
    void getTypeByIdReturnsDtoWhenFound() {
        when(vehicleTypesRepository.findById("1111-aaaa")).thenReturn(Optional.of(suvEntity));
        when(vehicleTypesMapper.toDto(suvEntity)).thenReturn(suvDto);

        VehicleTypesDto result = vehicleTypesService.getTypeById("1111-aaaa");

        assertNotNull(result);
        assertEquals(suvDto, result);
        verify(vehicleTypesRepository).findById("1111-aaaa");
        verify(vehicleTypesMapper).toDto(suvEntity);
    }

    @Test
    void getTypeByIdThrowsWhenMissing() {
        when(vehicleTypesRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleTypesService.getTypeById("missing"));
        verify(vehicleTypesRepository).findById("missing");
    }

    @Test
    void saveTypePersistsAndMaps() {
        when(vehicleTypesMapper.toEntity(suvDto)).thenReturn(suvEntity);
        when(vehicleTypesRepository.save(suvEntity)).thenReturn(suvEntity);
        when(vehicleTypesMapper.toDto(suvEntity)).thenReturn(suvDto);

        VehicleTypesDto result = vehicleTypesService.saveType(suvDto);

        assertEquals(suvDto, result);
        verify(vehicleTypesMapper).toEntity(suvDto);
        verify(vehicleTypesRepository).save(suvEntity);
        verify(vehicleTypesMapper).toDto(suvEntity);
    }

    @Test
    void updateTypeUpdatesExistingEntity() {
        VehicleTypes updatedEntity = new VehicleTypes("1111-aaaa", "Crossover");
        VehicleTypesDto updatedDto = new VehicleTypesDto("1111-aaaa", "Crossover");

        when(vehicleTypesRepository.findById("1111-aaaa")).thenReturn(Optional.of(suvEntity));
        when(vehicleTypesMapper.toEntity(updatedDto)).thenReturn(updatedEntity);
        when(vehicleTypesRepository.save(updatedEntity)).thenReturn(updatedEntity);
        when(vehicleTypesMapper.toDto(updatedEntity)).thenReturn(updatedDto);

        VehicleTypesDto result = vehicleTypesService.updateType("1111-aaaa", updatedDto);

        assertEquals(updatedDto, result);
        verify(vehicleTypesRepository).findById("1111-aaaa");
        verify(vehicleTypesMapper).toEntity(updatedDto);
        verify(vehicleTypesRepository).save(updatedEntity);
        verify(vehicleTypesMapper).toDto(updatedEntity);
    }

    @Test
    void deleteTypeThrowsWhenMissing() {
        when(vehicleTypesRepository.existsById("missing"))
                .thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> vehicleTypesService.deleteType("missing"));
        verify(vehicleTypesRepository).existsById("missing");
        verify(vehicleTypesRepository, never()).isTypeInUse(any());
        verify(vehicleTypesRepository, never()).deleteById(any());
    }

    @Test
    void deleteTypeThrowsWhenInUse() {
        when(vehicleTypesRepository.existsById("1111-aaaa")).thenReturn(true);
        when(vehicleTypesRepository.isTypeInUse("1111-aaaa")).thenReturn(true);

        assertThrows(EntityInUseException.class, () -> vehicleTypesService.deleteType("1111-aaaa"));
        verify(vehicleTypesRepository).existsById("1111-aaaa");
        verify(vehicleTypesRepository).isTypeInUse("1111-aaaa");
        verify(vehicleTypesRepository, never()).deleteById(any());
    }

    @Test
    void deleteTypeDeletesWhenFree() {
        when(vehicleTypesRepository.existsById("1111-aaaa")).thenReturn(true);
        when(vehicleTypesRepository.isTypeInUse("1111-aaaa")).thenReturn(false);

        vehicleTypesService.deleteType("1111-aaaa");

        verify(vehicleTypesRepository).existsById("1111-aaaa");
        verify(vehicleTypesRepository).isTypeInUse("1111-aaaa");
        verify(vehicleTypesRepository).deleteById("1111-aaaa");
    }
}
