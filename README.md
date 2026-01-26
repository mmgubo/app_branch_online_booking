## Overview
A Spring Boot microservice for managing appointment data and profiles in the Online Booking System.

This online booking service handles all branch appointment-related operations including:
- **Customer registration and profile management**
- **Retrieving customer information**
- **Updating customer details**
- **Appointment creation, updating and deleting**
- **Branch creation and deletion**
- **Notification communication**
## Architecture
## Tech Stack
- **Framework:** Spring Boot 4.x
- **Language:** Java 21
- **Database:** PostgreSQL and MongoDb
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven / Gradle

## Prerequisites
- Java 21
- Maven 3.9
- PostgreSQL 14+
- Docker

## Getting Started
### 1. Clone the Repository
    git clone https://github.com/mmgubo/app_branch_online_booking.git
### 2. Build and Run
**Using Maven:**
### Build the project
    mvn clean install
### Run the application
    mvn spring-boot:run
## Configuration
- cd **project directory**
- docker build -t **service name** .
- docker run -d -p **port number**:**port number** --name **service name** **service name**
- docker compose down
- docker compose up -d
## API Endpoints
### For Customer service:**
- **Get All Customers**
```http
GET /api/v1/getAllCustomers
```
- **Get Customer by ID**
```http
GET /api/v1/getCustomer/{customer-id
```
- **Create Customer**
```http
POST /api/v1/registerCustomer
```
- **Update Customer**
```http
PUT /api/v1/updateCustomer
```
- **Check if the Customer is existing**
```http
GET /api/v1/exist/{customer-id}
```
- **Delete Customer**
```http
DELETE /api/v1/{customer-id}
```


- **For Booking service:**
- **For Branch service:**
- **For Notification service:**

## Database Schema
### Customers Database
- **customer table**
### Bookings Database
- **customer_bookings table**
### Branch Database
- **branch table**
### Notification Database
- **notification table**
