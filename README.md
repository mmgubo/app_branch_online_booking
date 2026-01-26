## Overview
A Spring Boot microservice for managing appointment data and profiles in the Online Booking System.

This online booking service handles all branch-appointment-related operations, including:
- **Customer registration and profile management**
- **Retrieving customer information**
- **Updating customer details**
- **Creating, updating and deleting the Appointments to branch**
- **Notifying branch about the newly created appointment**
- **Sending Booking confirmations to the clients via emails**
## Architecture
### These services are using Eureka server for registering and deregistering the services on Eureka for the visibility of the port number the service is running on.
### These services are not registering to the Eureka server if the services are running as docker containers. 
### NOTE: Please run the services one by one from the terminal by pointing to the service directory and using Maven commands to run them. 
### Start running the config-server first and after that run the discovery service.
### You can run any other service after config-server and discovery services are up and running locally.
### The development tools used by these services are running well as Docker containers, and the docker configurations are saved in a docker-compose.yml file
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
- kafka 2.13

## Getting Started
### 1. Clone the Repository
    git clone https://github.com/mmgubo/app_branch_online_booking.git
### 2. Build and Run
**Using Maven:**
### Build the project
    mvn clean install
### Run the application
    mvn spring-boot:run
## Configurations/Running the services as docker containers
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

### For Booking service:**
- **Create Booking**
- **Get All Bookings**
- **Get Booking by ID**
- **Update Booking**
- **Update Booking status**
- **Delete Booking**
### For Branch service:**
- **Create Branch**
- **Delete Branch**


## Database Schema
### Customers Database
- **customer table**
### Bookings Database
- **customer_bookings table**
### Branch Database
- **branch table**

