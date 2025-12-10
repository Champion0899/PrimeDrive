package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.PrimeDriveBackend.dto.VehicleSpecsDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.VehicleSpecsMapper;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.model.Vehicle;
import com.example.PrimeDriveBackend.model.VehicleSpecs;
import com.example.PrimeDriveBackend.repository.VehicleRepository;
import com.example.PrimeDriveBackend.repository.VehicleSpecsRepository;

@ExtendWith(MockitoExtension.class)
class VehicleSpecsServiceTest {

    @Mock
    private VehicleSpecsRepository vehicleSpecsRepository;

    @Mock
    private VehicleSpecsMapper vehicleSpecsMapper;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleSpecsService vehicleSpecsService;

    private VehicleSpecs specs;
    private VehicleSpecsDto specsDto;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        specs = new VehicleSpecs();
        specs.setId("spec-1");

        specsDto = new VehicleSpecsDto();
        specsDto.setId("spec-1");
        specsDto.setPowerKw(100);
        specsDto.setPowerPs(120);
        specsDto.setLengthMillimeter(4500);
        specsDto.setWidthMillimeter(1800);
        specsDto.setHeightMillimeter(1400);
        specsDto.setTrunkInLiterMin(300);
        specsDto.setTrunkInLiterMax(500);
        specsDto.setZeroToHundredInSeconds(7.5);
        specsDto.setTopSpeedInKmh(200);
        specsDto.setConsumptionHundredInX(6.5);
        specsDto.setCoTwoEmissionInGPerKm(100);
        specsDto.setCubicCapacity(2.0);
        specsDto.setDoorsId("door-1");
        specsDto.setSeatsId("seat-1");
        specsDto.setEngineId("eng-1");
        specsDto.setFuelsId("fuel-1");

        Users owner = new Users();
        owner.setId("owner-1");
        vehicle = new Vehicle();
        vehicle.setId("veh-1");
        vehicle.setUsers(owner);
        vehicle.setSpecs(specs);
    }

    @Test
    void getAllSpecsReturnsDtos() {
        when(vehicleSpecsRepository.findAll()).thenReturn(List.of(specs));
        when(vehicleSpecsMapper.toDto(specs)).thenReturn(specsDto);

        List<VehicleSpecsDto> result = vehicleSpecsService.getAllSpecs();

        assertEquals(1, result.size());
        assertEquals(specsDto, result.get(0));
        verify(vehicleSpecsRepository).findAll();
    }

    @Test
    void getSpecsByIdReturnsDto() {
        when(vehicleSpecsRepository.findById("spec-1")).thenReturn(Optional.of(specs));
        when(vehicleSpecsMapper.toDto(specs)).thenReturn(specsDto);

        VehicleSpecsDto result = vehicleSpecsService.getSpecsById("spec-1");

        assertEquals(specsDto, result);
    }

    @Test
    void saveSpecsPersists() {
        when(vehicleSpecsMapper.toEntity(specsDto)).thenReturn(specs);
        when(vehicleSpecsRepository.save(specs)).thenReturn(specs);
        when(vehicleSpecsMapper.toDto(specs)).thenReturn(specsDto);

        VehicleSpecsDto result = vehicleSpecsService.saveSpecs(specsDto);

        assertEquals(specsDto, result);
        verify(vehicleSpecsRepository).save(specs);
    }

    @Test
    void updateSpecsSucceedsForOwner() {
        setAuthenticated("owner-1");
        VehicleSpecsDto updated = new VehicleSpecsDto();
        updated.setId("spec-1");
        updated.setPowerKw(110);

        VehicleSpecs mapped = new VehicleSpecs();
        mapped.setId("spec-1");

        when(vehicleSpecsRepository.findById("spec-1")).thenReturn(Optional.of(specs));
        when(vehicleRepository.findBySpecs_Id("spec-1")).thenReturn(Optional.of(vehicle));
        when(vehicleSpecsMapper.toEntity(updated)).thenReturn(mapped);
        when(vehicleSpecsRepository.save(mapped)).thenReturn(mapped);
        when(vehicleSpecsMapper.toDto(mapped)).thenReturn(updated);

        VehicleSpecsDto result = vehicleSpecsService.updateSpecs("spec-1", updated);

        assertEquals(updated, result);
        verify(vehicleSpecsRepository).save(mapped);
    }

    @Test
    void updateSpecsThrowsWhenUnauthorized() {
        setAuthenticated("other");
        when(vehicleSpecsRepository.findById("spec-1")).thenReturn(Optional.of(specs));
        when(vehicleRepository.findBySpecs_Id("spec-1")).thenReturn(Optional.of(vehicle));

        assertThrows(SecurityException.class, () -> vehicleSpecsService.updateSpecs("spec-1", specsDto));
    }

    @Test
    void deleteSpecsThrowsWhenInUse() {
        setAuthenticated("owner-1");
        when(vehicleSpecsRepository.existsById("spec-1")).thenReturn(true);
        when(vehicleRepository.findBySpecs_Id("spec-1")).thenReturn(Optional.of(vehicle));

        assertThrows(EntityInUseException.class, () -> vehicleSpecsService.deleteSpecs("spec-1"));
    }

    @Test
    void deleteSpecsDeletesWhenFree() {
        setAuthenticated("owner-1");
        vehicle.setSpecs(null);
        when(vehicleSpecsRepository.existsById("spec-1")).thenReturn(true);
        when(vehicleRepository.findBySpecs_Id("spec-1")).thenReturn(Optional.of(vehicle));

        vehicleSpecsService.deleteSpecs("spec-1");

        verify(vehicleSpecsRepository).deleteById("spec-1");
    }

    private void setAuthenticated(String userId) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, "pw");
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
