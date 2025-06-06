START TRANSACTION;

USE PrimeDrive;

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO PrimeDrive.vehicle_fuels (id, fuel_type)
VALUES 
("5cece0a3-359d-11f0-9e9f-6e56b6962fb5", "Benzin"),
("5cece1ee-359d-11f0-9e9f-6e56b6962fb5", "Diesel"),
("5cece256-359d-11f0-9e9f-6e56b6962fb5", "Elektrizität");

SET FOREIGN_KEY_CHECKS=1;

COMMIT;