START TRANSACTION;

USE PrimeDrive;

SET FOREIGN_KEY_CHECKS=0; 

INSERT INTO PrimeDrive.vehicle_engine (id, engine_type)
VALUES 
("adb2967a-359b-11f0-9e9f-6e56b6962fb5", "2 Cylinder"),
("adb297d9-359b-11f0-9e9f-6e56b6962fb5", "3 Cylinder"),
("adb29877-359b-11f0-9e9f-6e56b6962fb5", "4 Cylinder"),
("adb29894-359b-11f0-9e9f-6e56b6962fb5", "5 Cylinder"),
("adb298a9-359b-11f0-9e9f-6e56b6962fb5", "6 Cylinder"),
("adb298bd-359b-11f0-9e9f-6e56b6962fb5", "8 Cylinder"),
("adb298d1-359b-11f0-9e9f-6e56b6962fb5", "10 Cylinder"),
("adb298e4-359b-11f0-9e9f-6e56b6962fb5", "12 Cylinder"),
("adb298f5-359b-11f0-9e9f-6e56b6962fb5", "16 Cylinder"),
("adb29903-359b-11f0-9e9f-6e56b6962fb5", "Electro"),
("adb29911-359b-11f0-9e9f-6e56b6962fb5", "Hybrid");

SET FOREIGN_KEY_CHECKS=1;

COMMIT;