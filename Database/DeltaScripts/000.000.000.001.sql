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

CREATE TABLE IF NOT EXISTS vehicle_holdings (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    founding INT NOT NULL,
    logo VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_brands (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    founding INT,
    logo VARCHAR(255) NOT NULL,
    foreign_key_vehicle_holding_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (foreign_key_vehicle_holding_id) REFERENCES vehicle_holdings(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_colors (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    hex_code VARCHAR(10) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_types (
    id VARCHAR(36) PRIMARY KEY,
    type VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_doors (
    id VARCHAR(36) PRIMARY KEY,
    quantity INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_seats (
    id VARCHAR(36) PRIMARY KEY,
    quantity INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_engine (
    id VARCHAR(36) PRIMARY KEY,
    engine_type VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_fuels (
    id VARCHAR(36) PRIMARY KEY,
    fuel_type VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_specs (
    id VARCHAR(36) PRIMARY KEY,
    power_kw INT,
    power_ps INT,
    length_millimeter INT,
    width_millimeter INT,
    height_millimeter INT,
    trunk_in_liter_min INT,
    trunk_in_liter_max INT,
    zero_to_hundred_in_seconds FLOAT,
    top_speed_in_kmh INT,
    consumption_hundred_in_x FLOAT,
    co_two_emission_in_g_per_km INT,
    cubic_capacity INT,
    foreign_key_vehicle_doors_id VARCHAR(36),
    foreign_key_vehicle_seats_id VARCHAR(36),
    foreign_key_vehicle_engine_id VARCHAR(36),
    foreign_key_vehicle_fuels_id VARCHAR(36),
    FOREIGN KEY (foreign_key_vehicle_doors_id) REFERENCES vehicle_doors(id)
    ON UPDATE cascade 
    ON DELETE SET NULL,
    FOREIGN KEY (foreign_key_vehicle_seats_id) REFERENCES vehicle_seats(id)
    ON UPDATE cascade
    ON DELETE SET NULL,
    FOREIGN KEY (foreign_key_vehicle_engine_id) REFERENCES vehicle_engine(id)
    ON UPDATE cascade
    ON DELETE SET NULL,
    FOREIGN KEY (foreign_key_vehicle_fuels_id) REFERENCES vehicle_fuels(id)
    ON UPDATE cascade
    ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    year INT NOT NULL,
    image VARCHAR(255) NOT NULL,
    mileage INT NOT NULL,
    `condition` VARCHAR(255) NOT NULL,
    vehicle_history VARCHAR(255) NOT NULL,
    foreign_key_vehicle_brands_id VARCHAR(36),
    foreign_key_vehicle_specs_id VARCHAR(36),
    foreign_key_vehicle_types_id VARCHAR(36),
    foreign_key_vehicle_colors_id VARCHAR(36),
    foreign_key_vehicle_seller_id VARCHAR(36),
    FOREIGN KEY (foreign_key_vehicle_brands_id) REFERENCES vehicle_brands(id)
    ON UPDATE cascade
    ON DELETE SET NULL,
    FOREIGN KEY (foreign_key_vehicle_specs_id) REFERENCES vehicle_specs(id)
    ON UPDATE cascade
    ON DELETE SET NULL,
    FOREIGN KEY (foreign_key_vehicle_types_id) REFERENCES vehicle_types(id)
    ON UPDATE cascade
    ON DELETE SET NULL,
    FOREIGN KEY (foreign_key_vehicle_colors_id) REFERENCES vehicle_colors(id)
    ON UPDATE cascade
    ON DELETE SET NULL,
    FOREIGN KEY (foreign_key_vehicle_seller_id) REFERENCES users(id)
    ON UPDATE cascade
    ON DELETE SET NULL
) ENGINE=InnoDB;

COMMIT;