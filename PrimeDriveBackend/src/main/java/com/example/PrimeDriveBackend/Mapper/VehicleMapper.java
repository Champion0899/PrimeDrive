package com.example.PrimeDriveBackend.Mapper;

import com.example.PrimeDriveBackend.Dto.VehicleDto;
import com.example.PrimeDriveBackend.service.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VehicleMapper {

        private final service.UserService userService;

        VehicleMapper(service.UserService userService) {
                this.userService = userService;
        }

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
                vehicle.setBrands(vehicleBrandsService.findById(dto.getBrandsId())
                                .orElseThrow(() -> new RuntimeException("Brand not found")));
                vehicle.setSpecs(vehicleSpecsService.findById(dto.getSpecsId())
                                .orElseThrow(() -> new RuntimeException("Specs not found")));
                vehicle.setTypes(vehicleTypesService.findById(dto.getTypesId())
                                .orElseThrow(() -> new RuntimeException("Type not found")));
                vehicle.setColors(vehicleColorsService.findById(dto.getColorsId())
                                .orElseThrow(() -> new RuntimeException("Color not found")));
                vehicle.setUsers(
                                userService.findById(dto.getSellerId())
                                                .orElseThrow(() -> new RuntimeException("User not found")));
                return vehicle;
        }
}