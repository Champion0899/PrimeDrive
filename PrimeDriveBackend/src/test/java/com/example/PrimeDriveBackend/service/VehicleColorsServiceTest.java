package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.example.PrimeDriveBackend.dto.VehicleColorsDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.VehicleColorsMapper;
import com.example.PrimeDriveBackend.model.VehicleColors;
import com.example.PrimeDriveBackend.repository.VehicleColorsRepository;

@ExtendWith(MockitoExtension.class)
class VehicleColorsServiceTest {

    @Mock
    private VehicleColorsRepository vehicleColorsRepository;

    @Mock
    private VehicleColorsMapper vehicleColorsMapper;

    @InjectMocks
    private VehicleColorsService vehicleColorsService;

    private VehicleColors color;
    private VehicleColorsDto colorDto;

    @BeforeEach
    void setUp() {
        color = new VehicleColors();
        color.setId("color-1");
        color.setName("Red");
        color.setHexCode("FF0000");

        colorDto = new VehicleColorsDto();
        colorDto.setId("color-1");
        colorDto.setName("Red");
        colorDto.setHexCode("FF0000");
    }

    @Test
    void getAllColorsReturnsDtos() {
        when(vehicleColorsRepository.findAll()).thenReturn(List.of(color));
        when(vehicleColorsMapper.toDto(color)).thenReturn(colorDto);

        List<VehicleColorsDto> result = vehicleColorsService.getAllColors();

        assertEquals(1, result.size());
        assertEquals(colorDto, result.get(0));
        verify(vehicleColorsRepository).findAll();
    }

    @Test
    void getColorByIdReturnsDto() {
        when(vehicleColorsRepository.findById("color-1")).thenReturn(Optional.of(color));
        when(vehicleColorsMapper.toDto(color)).thenReturn(colorDto);

        VehicleColorsDto result = vehicleColorsService.getColorById("color-1");

        assertEquals(colorDto, result);
    }

    @Test
    void getColorByIdThrowsWhenMissing() {
        when(vehicleColorsRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleColorsService.getColorById("missing"));
    }

    @Test
    void saveColorPersists() {
        when(vehicleColorsMapper.toEntity(colorDto)).thenReturn(color);
        when(vehicleColorsRepository.save(color)).thenReturn(color);
        when(vehicleColorsMapper.toDto(color)).thenReturn(colorDto);

        VehicleColorsDto result = vehicleColorsService.saveColor(colorDto);

        assertEquals(colorDto, result);
        verify(vehicleColorsRepository).save(color);
    }

    @Test
    void updateColorUpdatesExisting() {
        VehicleColorsDto updateDto = new VehicleColorsDto();
        updateDto.setId("color-1");
        updateDto.setName("Blue");
        updateDto.setHexCode("0000FF");

        when(vehicleColorsRepository.findById("color-1")).thenReturn(Optional.of(color));
        when(vehicleColorsMapper.toEntity(updateDto)).thenReturn(color);
        when(vehicleColorsRepository.save(color)).thenReturn(color);
        when(vehicleColorsMapper.toDto(color)).thenReturn(updateDto);

        VehicleColorsDto result = vehicleColorsService.updateColor("color-1", updateDto);

        assertEquals(updateDto, result);
        verify(vehicleColorsRepository).save(color);
    }

    @Test
    void deleteColorThrowsWhenNotFound() {
        when(vehicleColorsRepository.existsById("missing")).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> vehicleColorsService.deleteColor("missing"));
    }

    @Test
    void deleteColorThrowsWhenInUse() {
        when(vehicleColorsRepository.existsById("color-1")).thenReturn(true);
        when(vehicleColorsRepository.isColorInUse("color-1")).thenReturn(true);

        assertThrows(EntityInUseException.class, () -> vehicleColorsService.deleteColor("color-1"));
    }

    @Test
    void deleteColorDeletesWhenFree() {
        when(vehicleColorsRepository.existsById("color-1")).thenReturn(true);
        when(vehicleColorsRepository.isColorInUse("color-1")).thenReturn(false);

        vehicleColorsService.deleteColor("color-1");

        verify(vehicleColorsRepository).deleteById("color-1");
    }
}
