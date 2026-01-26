CREATE TABLE IF NOT EXISTS branch
(
    id                  SERIAL PRIMARY KEY,
    bookings_id           VARCHAR(255) NOT NULL UNIQUE,
    created_date        TIMESTAMP NOT NULL,
    last_modified_date  TIMESTAMP
    );