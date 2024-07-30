DROP TABLE IF EXISTS realestate.reservation;
DROP TABLE IF EXISTS realestate.lot;

CREATE TABLE realestate.lot (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(255),
                                city VARCHAR(255),
                                address VARCHAR(255),
                                area DOUBLE PRECISION,
                                floor INT,
                                rooms INT
);

CREATE TABLE realestate.reservation (
                                        id SERIAL PRIMARY KEY,
                                        client_name VARCHAR(255),
                                        reservation_time TIMESTAMP,
                                        lot_id INT REFERENCES realestate.lot(id),
                                        contact_phone VARCHAR(255)
);
