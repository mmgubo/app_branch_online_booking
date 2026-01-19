CREATE TABLE IF NOT EXISTS customer_bookings
(
    id                  SERIAL PRIMARY KEY,
    reference           VARCHAR(255) NOT NULL UNIQUE,
    customer_id         VARCHAR(255),
    created_date        TIMESTAMP NOT NULL,
    last_modified_date  TIMESTAMP
);

