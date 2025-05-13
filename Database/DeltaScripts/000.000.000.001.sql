START TRANSACTION;

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL,
    e_mail VARCHAR(255) UNIQUE NOT NULL,
    birthdate DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    created_user VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    modified_user VARCHAR(255) NOT NULL,
    modified_date TIMESTAMP NOT NULL,
    last_login_date TIMESTAMP NOT NULL,
    last_login_ip VARCHAR(45) NOT NULL
) ENGINE=InnoDB;


COMMIT;