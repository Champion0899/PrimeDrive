START TRANSACTION;

USE PrimeDrive;

SET FOREIGN_KEY_CHECKS=0; 

CREATE TABLE IF NOT EXISTS purchases (
    id VARCHAR(36) PRIMARY KEY,
    vehicle_id VARCHAR(36) NOT NULL,
    buyer_id VARCHAR(36) NOT NULL,
    seller_id VARCHAR(36) NOT NULL,
    created_user VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    modified_user VARCHAR(255) NOT NULL,
    modified_date TIMESTAMP NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    FOREIGN KEY (buyer_id) REFERENCES users(id),
    FOREIGN KEY (seller_id) REFERENCES users(id)
) ENGINE=InnoDB;

COMMIT;