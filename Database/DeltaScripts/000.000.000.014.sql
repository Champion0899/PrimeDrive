START TRANSACTION;

SET @pw := '$argon2id$v=19$m=65536,t=3,p=4$dGVzdHZhbHVlc2FsdA$QnvXy0pHYUvZ7z4fz5sCkxfZ9Yq7+Fj2Eqf51/4ToNo';

INSERT INTO users VALUES 
('f3a3b4d5-0001-4cde-b111-aaaabbbb0001', 'lena', @pw, 'BUYER', 'lena@example.com', '1990-05-12', 'Lena', 'Huber', 'Musterweg 1', '8000', 'Zürich', 'Schweiz', '+41 79 1234567', 'system', NOW(), 'system', NOW(), NOW(), '192.168.1.101'),
('f3a3b4d5-0002-4cde-b222-aaaabbbb0002', 'tim', @pw, 'BUYER', 'tim@example.com', '1989-11-23', 'Tim', 'Meier', 'Beispielstrasse 45', '3012', 'Bern', 'Schweiz', '+41 79 7654321', 'system', NOW(), 'system', NOW(), NOW(), '192.168.1.102');

INSERT INTO purchases VALUES 
('77777777-aaaa-4bbb-cccc-111111111111', 'acc6f848-35a5-11f0-9e9f-6e56b6962fb5', 'f3a3b4d5-0001-4cde-b111-aaaabbbb0001', 'b26ef89c-0161-47ef-b346-8f758d58d543', 'system', NOW(), 'system', NOW()),
('88888888-bbbb-4ccc-dddd-222222222222', 'acc6fca4-35a5-11f0-9e9f-6e56b6962fb5', 'f3a3b4d5-0002-4cde-b222-aaaabbbb0002', 'a7fd35b3-a997-4a17-9ccc-8536037d35ef', 'system', NOW(), 'system', NOW());

COMMIT;
