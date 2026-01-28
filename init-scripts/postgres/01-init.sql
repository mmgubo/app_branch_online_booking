-- PostgreSQL initialization script for booking system
-- Creates databases for bookings and branch services
-- PostgreSQL initialization script for booking system
-- Creates all required databases

-- Create extension for UUID in default database
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create branch database
CREATE DATABASE branch;

-- Create bookings database (if different from booking_db)
CREATE DATABASE bookings;

-- Grant permissions
GRANT ALL PRIVILEGES ON DATABASE booking_db TO postgres;
GRANT ALL PRIVILEGES ON DATABASE branch TO postgres;
GRANT ALL PRIVILEGES ON DATABASE bookings TO postgres;

-- Connect to branch and create extensions
\c branch
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Connect to bookings and create extensions
\c bookings
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
