START TRANSACTION;

USE PrimeDrive;

SET FOREIGN_KEY_CHECKS=0;

SET @pw := '$argon2id$v=19$m=65536,t=3,p=4$dGVzdHZhbHVlc2FsdA$QnvXy0pHYUvZ7z4fz5sCkxfZ9Yq7+Fj2Eqf51/4ToNo';

INSERT INTO users VALUES 
('b26ef89c-0161-47ef-b346-8f758d58d543', 'mia', @pw, 'SELLER', 'mia@example.com', '1988-12-14', 'mia', 'miller', '726 Example Street', '6665', 'Basel', 'Schweiz', '+41 79 5990814', 'system', '2025-05-20 20:35:20.161232', 'system', '2025-05-20 20:35:20.161232', '2025-05-10 20:35:20.161232', '192.168.1.46'),
('a7fd35b3-a997-4a17-9ccc-8536037d35ef', 'noah', @pw, 'SELLER', 'noah@example.com', '1988-12-15', 'noah', 'thomson', '405 Example Street', '2184', 'Luzern', 'Schweiz', '+41 79 5118988', 'system', '2025-05-20 20:35:20.161274', 'system', '2025-05-20 20:35:20.161274', '2025-05-16 20:35:20.161274', '192.168.1.239');

SET FOREIGN_KEY_CHECKS=1;

COMMIT;