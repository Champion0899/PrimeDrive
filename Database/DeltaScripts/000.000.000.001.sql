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

CREATE TABLE IF NOT EXISTS vehicle_holding (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    founding DATE NOT NULL,
    logo VARCHAR(255) NOT NULL,
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_brands (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    logo VARCHAR(255) NOT NULL,
    holding_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (holding_id) REFERENCES holding(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_colors (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    hex_code VARCHAR(10) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_types (
    id VARCHAR(36) PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
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
    engine VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_fuel (
    id VARCHAR(36) PRIMARY KEY,
    fuel_type VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS vehicle_specs (
    id VARCHAR(36) PRIMARY KEY,
    power_kw INT NOT NULL,
    power_ps INT NOT NULL,
    length_millimeter INT NOT NULL,
    width_millimeter INT NOT NULL,
    height_millimeter INT NOT NULL,
    trunk_in_liter_min INT NOT NULL,
    trunk_in_liter_max INT NOT NULL,
    zero_to_hundred_in_seconds FLOAT NOT NULL,
    top_speed_in_kmh INT NOT NULL,
    consumption_hundred_in_x FLOAT NOT NULL,
    co_two_emission_in_g_per_km INT NOT NULL,
    cubic_capacity INT NOT NULL,
    foreign_key_vehicle_doors_id VARCHAR(36) NOT NULL,
    foreign_key_vehicle_seats_id VARCHAR(36) NOT NULL,
    foreign_key_vehicle_engine_id VARCHAR(36) NOT NULL,
    foreign_key_vehicle_fuel_id VARCHAR(36) NOT NULL,
    foreign key (foreign_key_vehicle_doors_id) references vehicle_doors(id)
    on update cascade 
    on delete set null,
    foreign key (foreign_key_vehicle_seats_id) references vehicle_seats(id)
    on update cascade
    on delete set null,
    foreign key (foreign_key_vehicle_engine_id) references vehicle_engine(id),
    on update cascade,
    on delete set null,
    foreign key (foreign_key_vehicle_fuel_id) references vehicle_fuel(id)
    on update cascade
    on delete set null
) ENGINE=InnoDB;



COMMIT;