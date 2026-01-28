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
The Online Booking System is a cloud-native, microservices-based platform designed for managing appointments, customers, branches, and notifications. The system follows a domain driven architecture with service bookings, branch, customer and notification operating independently but exchanging information.
The system also follows a distributed pattern with service discovery, centralized configuration, and API gateway for routing and load balancing.

### 1. Project structure

```
app_branch_online_booking/
├── discovery/           # Eureka Server (port 8761)
├── config-server/       # Spring Cloud Config (port 8222)
├── gateway/             # API Gateway (port 8000)
├── bookings/            # Bookings Service (port 8070) - PostgreSQL + Kafka
├── branch/              # Branch Service (port 8060) - PostgreSQL + Kafka
├── customer/            # Customer Service (port 8090) - MongoDB
├── notification/        # Notification Service (port 8040) - MongoDB + Kafka
└── docker-compose       # Docker configuration 
```
## Tech Stack
- **Framework:** Spring Boot 4.x
- **Language:** Java 21
- **Database:** PostgreSQL and MongoDb
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven / Gradle

## Prerequisites
- Java 21
- Maven 3.9
- PostgreSQL 15+
- Docker
- kafka 2.13
- Docker and Docker Compose installed
- At least 8GB RAM allocated to Docker

## Getting Started
### 1. Clone the Repository
    git clone https://github.com/mmgubo/app_branch_online_booking.git
### 2. Run the services
**Using Docker:**
````
# Build and start all services
    docker-compose up --build
    
# Or run in detached mode
    docker-compose up -d --build
    
# View logs
    docker-compose logs -f

# View specific service logs    
    docker-compose logs -f bookings
````

**Common Commands:**
````
# Stop all services
docker-compose down
````

## API Endpoints
### For Customer service:
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

