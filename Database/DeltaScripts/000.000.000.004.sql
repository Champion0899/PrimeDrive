START TRANSACTION;

USE PrimeDrive;

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO PrimeDrive.vehicle_colors (id, name, hex_code)
VALUES 
('250b0265-3596-11f0-9e9f-6e56b6962fb5', "Red", "ff0000"),
('250b0403-3596-11f0-9e9f-6e56b6962fb5', "Blau", "0000ff"),
('250b0482-3596-11f0-9e9f-6e56b6962fb5', "Grün", "00ff00"),
('250b04a4-3596-11f0-9e9f-6e56b6962fb5', "Gelb", "ffff00"),
('250b04bc-3596-11f0-9e9f-6e56b6962fb5', "Orange", "FF5733"),
('250b04d4-3596-11f0-9e9f-6e56b6962fb5', "Schwarz", "000000"),
('250b04ea-3596-11f0-9e9f-6e56b6962fb5', "Chrom", "C7C4B9"),
('250b0502-3596-11f0-9e9f-6e56b6962fb5', "Grau", "888888"),
('250b0586-3596-11f0-9e9f-6e56b6962fb5', "Lila", "BF40BF"),
('250b05a1-3596-11f0-9e9f-6e56b6962fb5', "Pink", "AA336A"),
('250b05b7-3596-11f0-9e9f-6e56b6962fb5', "Silber", "C0C0C0"),
('250b05cb-3596-11f0-9e9f-6e56b6962fb5', "Weiss", "ffffff");

SET FOREIGN_KEY_CHECKS=1;

COMMIT;