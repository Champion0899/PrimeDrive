package com.example.PrimeDriveBackend.mapper;

import com.example.PrimeDriveBackend.dto.VehicleDto;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.model.Vehicle;
import com.example.PrimeDriveBackend.model.VehicleBrands;
import com.example.PrimeDriveBackend.model.VehicleColors;
import com.example.PrimeDriveBackend.model.VehicleSpecs;
import com.example.PrimeDriveBackend.model.VehicleTypes;
import com.example.PrimeDriveBackend.service.*;

import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Vehicle entities and their DTO
 * representations.
 *
 * Transforms full vehicle information to DTOs for transport and reverses the
 * transformation
 * for persistence. Resolves all associated references using related service
 * classes.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleMapper {

    private final UserService userService;
    private final VehicleBrandsService vehicleBrandsService;
    private final VehicleSpecsService vehicleSpecsService;
    private final VehicleTypesService vehicleTypesService;
    private final VehicleColorsService vehicleColorsService;

    VehicleMapper(UserService userService,
            VehicleSpecsService vehicleSpecsService,
            VehicleTypesService vehicleTypesService,
            VehicleColorsService vehicleColorsService,
            VehicleBrandsService vehicleBrandsService) {
        this.userService = userService;
        this.vehicleBrandsService = vehicleBrandsService;
        this.vehicleTypesService = vehicleTypesService;
        this.vehicleSpecsService = vehicleSpecsService;
        this.vehicleColorsService = vehicleColorsService;
    }

    /**
     * Converts a Vehicle entity to a VehicleDto.
     *
     * @param vehicle The Vehicle entity to convert.
     * @return A DTO containing vehicle data with IDs of related entities.
     */
    public VehicleDto toDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setName(vehicle.getName());
        dto.setPrice(vehicle.getPrice());
        dto.setYear(vehicle.getYear());
        dto.setImage(vehicle.getImage());
        dto.setMileage(vehicle.getMileage());
        dto.setCondition(vehicle.getCondition());
        dto.setVehicleHistory(vehicle.getVehicleHistory());

        dto.setBrandsId(vehicle.getBrands() != null ? vehicle.getBrands().getId() : null);
        dto.setSpecsId(vehicle.getSpecs() != null ? vehicle.getSpecs().getId() : null);
        dto.setTypesId(vehicle.getTypes() != null ? vehicle.getTypes().getId() : null);
        dto.setColorsId(vehicle.getColors() != null ? vehicle.getColors().getId() : null);
        dto.setSellerId(vehicle.getUsers() != null ? vehicle.getUsers().getId() : null);

        return dto;
    }

    /**
     * Converts a VehicleDto to a Vehicle entity.
     *
     * Resolves all related entities (brands, types, specs, colors, users) using
     * services.
     *
     * @param dto The VehicleDto containing vehicle data.
     * @return A fully constructed Vehicle entity.
     * @throws RuntimeException if any referenced entity cannot be found.
     */
    public Vehicle toEntity(VehicleDto dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setName(dto.getName());
        vehicle.setPrice(dto.getPrice());
        vehicle.setYear(dto.getYear());
        vehicle.setImage(dto.getImage());
        vehicle.setMileage(dto.getMileage());
        vehicle.setCondition(dto.getCondition());
        vehicle.setVehicleHistory(dto.getVehicleHistory());

        VehicleBrands vehicleBrands = vehicleBrandsService.getBrandByIdEntity(dto.getBrandsId());
        if (vehicleBrands == null) {
            throw new RuntimeException("Brand not found");
        }
        vehicle.setBrands(vehicleBrands);

        VehicleTypes vehicleTypes = vehicleTypesService.getTypeByIdEntity(dto.getTypesId());
        if (vehicleTypes == null) {
            throw new RuntimeException("Type not found");
        }
        vehicle.setTypes(vehicleTypes);

        VehicleSpecs vehicleSpecs = vehicleSpecsService.getSpecsByIdEntity(dto.getSpecsId());
        if (vehicleSpecs == null) {
            throw new RuntimeException("Specs not found");
        }
        vehicle.setSpecs(vehicleSpecs);

        VehicleColors vehicleColors = vehicleColorsService.getColorByIdEntity(dto.getColorsId());
        if (vehicleColors == null) {
            throw new RuntimeException("Color not found");
        }
        vehicle.setColors(vehicleColors);

        Users users = userService.getByIdEntity(dto.getSellerId());
        if (users == null) {
            throw new RuntimeException("User not found");
        }
        vehicle.setUsers(users);

        return vehicle;
    }
}