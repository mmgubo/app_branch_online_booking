CREATE TABLE IF NOT EXISTS customer_bookings
(
    id                  SERIAL PRIMARY KEY,
    customer_id         VARCHAR(255),
    created_date        TIMESTAMP NOT NULL,
    time                VARCHAR(255),
    status              VARCHAR(255),
    branch              VARCHAR(255),
    notes               VARCHAR(255),
    service             VARCHAR(255),
    date                VARCHAR(255)
);

