package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.mockito.MockedStatic;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.PrimeDriveBackend.dto.VehicleDto;
import com.example.PrimeDriveBackend.mapper.VehicleMapper;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.model.Vehicle;
import com.example.PrimeDriveBackend.repository.VehicleRepository;
import com.example.PrimeDriveBackend.util.ImageValidator;
import com.example.PrimeDriveBackend.exception.UnauthorizedAccessException;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @InjectMocks
    private VehicleService vehicleService;

    private Vehicle vehicle;
    private VehicleDto vehicleDto;

    @BeforeEach
    void setUp() {
        Users owner = new Users();
        owner.setId("owner-1");

        vehicle = new Vehicle();
        vehicle.setId("veh-1");
        vehicle.setUsers(owner);

        vehicleDto = new VehicleDto();
        vehicleDto.setId("veh-1");
        vehicleDto.setSellerId("owner-1");
        vehicleDto.setName("Car");
        vehicleDto.setPrice(10_000d);
        vehicleDto.setYear(2020);
        vehicleDto.setImage("https://img.png");
        vehicleDto.setMileage(1000);
        vehicleDto.setCondition("new");
        vehicleDto.setVehicleHistory("clean");
        vehicleDto.setBrandsId("b1");
        vehicleDto.setSpecsId("s1");
        vehicleDto.setTypesId("t1");
        vehicleDto.setColorsId("c1");
    }

    @Test
    void getAllVehiclesReturnsDtos() {
        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));
        when(vehicleMapper.toDto(vehicle)).thenReturn(vehicleDto);

        List<VehicleDto> result = vehicleService.getAllVehicles();

        assertEquals(1, result.size());
        assertEquals(vehicleDto, result.get(0));
        verify(vehicleRepository).findAll();
        verify(vehicleMapper).toDto(vehicle);
    }

    @Test
    void getVehicleByIdReturnsDto() {
        when(vehicleRepository.findById("veh-1")).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.toDto(vehicle)).thenReturn(vehicleDto);

        VehicleDto result = vehicleService.getVehicleById("veh-1");

        assertNotNull(result);
        assertEquals(vehicleDto, result);
        verify(vehicleRepository).findById("veh-1");
    }

    @Test
    void getVehicleByIdThrowsWhenMissing() {
        when(vehicleRepository.findById("missing")).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> vehicleService.getVehicleById("missing"));
    }

    @Test
    void saveVehicleValidatesAndPersists() {
        Vehicle mapped = new Vehicle();
        when(vehicleMapper.toEntity(vehicleDto)).thenReturn(mapped);
        when(vehicleRepository.save(mapped)).thenReturn(mapped);
        when(vehicleMapper.toDto(mapped)).thenReturn(vehicleDto);

        try (MockedStatic<ImageValidator> validator = org.mockito.Mockito.mockStatic(ImageValidator.class)) {
            VehicleDto result = vehicleService.saveVehicle(vehicleDto);
            assertEquals(vehicleDto, result);
            validator.verify(() -> ImageValidator.validate(vehicleDto.getImage()));
        }
    }

    @Test
    void updateVehicleSucceedsForOwner() {
        VehicleDto updatedDto = new VehicleDto();
        updatedDto.setImage("https://img2.png");

        Vehicle updatedEntity = new Vehicle();
        updatedEntity.setId("veh-1");

        setAuthenticatedUser("owner-1");

        when(vehicleRepository.findById("veh-1")).thenReturn(Optional.of(vehicle));
        when(vehicleMapper.toEntity(updatedDto)).thenReturn(updatedEntity);
        when(vehicleRepository.save(updatedEntity)).thenReturn(updatedEntity);
        when(vehicleMapper.toDto(updatedEntity)).thenReturn(updatedDto);

        try (MockedStatic<ImageValidator> validator = org.mockito.Mockito.mockStatic(ImageValidator.class)) {
            VehicleDto result = vehicleService.updateVehicle("veh-1", updatedDto);
            assertEquals(updatedDto, result);
            validator.verify(() -> ImageValidator.validate(updatedDto.getImage()));
        }
    }

    @Test
    void updateVehicleThrowsForUnauthorizedUser() {
        setAuthenticatedUser("other-user");
        when(vehicleRepository.findById("veh-1")).thenReturn(Optional.of(vehicle));

        assertThrows(UnauthorizedAccessException.class, () -> vehicleService.updateVehicle("veh-1", vehicleDto));
    }

    @Test
    void deleteVehicleSucceedsForOwner() {
        setAuthenticatedUser("owner-1");
        when(vehicleRepository.findById("veh-1")).thenReturn(Optional.of(vehicle));

        vehicleService.deleteVehicle("veh-1");

        verify(vehicleRepository).deleteById("veh-1");
    }

    @Test
    void deleteVehicleThrowsForUnauthorizedUser() {
        setAuthenticatedUser("other-user");
        when(vehicleRepository.findById("veh-1")).thenReturn(Optional.of(vehicle));

        assertThrows(UnauthorizedAccessException.class, () -> vehicleService.deleteVehicle("veh-1"));
    }

    @Test
    void deleteVehicleThrowsWhenMissing() {
        setAuthenticatedUser("owner-1");
        when(vehicleRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleService.deleteVehicle("missing"));
    }

    private void setAuthenticatedUser(String userId) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, "pass");
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
