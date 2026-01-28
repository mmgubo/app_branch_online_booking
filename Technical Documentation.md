# Online Booking System - Technical Documentation


## 1. Executive Summary


The Online Booking System is a cloud-native, microservices-based platform designed for managing appointments, customers, branches, and notifications. The system follows a domain driven architecture with service bookings, branch, customer and notification operating independently but exchanging information. The system also follows a distributed pattern with service discovery, centralized configuration, and API gateway for routing and load balancing.


---


## 2. System Architecture


### 2.1 Architecture Overview


```
                                   +------------------+
                                   |   Frontend App   |
                                   |   (Next.js)      |
                                   +--------+---------+
                                            |
                                            v
+------------------+              +----------+---------+
|  Eureka Server   |<----------->|    API Gateway     |
|  (Discovery)     |              |    (Port 8222)     |
+------------------+              +----------+---------+
        ^                                   |
        |                    +--------------+--------------+
        |                    |              |              |
+--------+--------+           v              v              v
|  Config Server  |    +------+------+ +-----+------+ +----+-------+
|  (Port 8888)    |    |  Bookings   | |  Customer  | |   Branch   |
+-----------------+    | (Port 8070) | | (Port 8090)| | (Port 8060)|
                      +------+------+ +-----+------+ +----+-------+
                             |              |              |
                             v              v              v
                      +------+------+ +-----+------+ +----+-------+
                      | PostgreSQL  | |  MongoDB   | | PostgreSQL |
                      +-------------+ +------------+ +------------+
                             |
                             v
                      +------+------+
                      |    Kafka    |
                      | (Messaging) |
                      +------+------+
                             |
                             v
                      +------+------+
                      |Notification |
                      | (Port 8040) |
                      +-------------+
```


### 2.2 Architecture Patterns


| Pattern | Implementation | Purpose |
|---------|---------------|---------|
| Microservices | Spring Boot services | Scalability, independent deployment |
| Service Discovery | Netflix Eureka | Dynamic service registration and lookup |
| API Gateway | Spring Cloud Gateway | Single entry point, routing, load balancing |
| Centralized Config | Spring Cloud Config | Externalized configuration management |
| Event-Driven | Apache Kafka | Asynchronous communication between services |
| Database per Service | PostgreSQL/MongoDB | Data isolation and service autonomy |


---


## 3. Technology Stack


### 3.1 Backend Services


| Component | Technology | Version |
|-----------|------------|---------|
| Runtime | Java | 21 (LTS) |
| Framework | Spring Boot | 3.5.x / 4.0.x |
| Service Discovery | Spring Cloud Netflix Eureka | 2024.x |
| API Gateway | Spring Cloud Gateway | 2024.x |
| Configuration | Spring Cloud Config | 2024.x |
| Database (Relational) | PostgreSQL | 15 |
| Database (Document) | MongoDB | 7.0 |
| Message Broker | Apache Kafka | 3.x |
| Build Tool | Maven | 3.9.x |
| Containerization | Docker | 24.x |


### 3.2 Frontend Application


| Component | Technology | Version |
|-----------|------------|---------|
| Framework | Next.js | 16.x |
| Language | TypeScript | 5.x |
| Styling | Tailwind CSS | 4.x |
| UI Components | shadcn/ui | Latest |
| State Management | React Context / SWR | Latest |


---


## 4. Service Specifications


### 4.1 Discovery Service (Eureka Server)


| Attribute | Value |
|-----------|-------|
| Port | 8761 |
| Artifact ID | discovery |
| Purpose | Service registry and discovery |
| Dashboard | http://localhost:8761 |


**Responsibilities:**
- Maintain registry of all microservices
- Provide service health monitoring
- Enable client-side load balancing
- Support service instance failover


### 4.2 Config Server


| Attribute | Value |
|-----------|-------|
| Port | 8888 |
| Artifact ID | config-server |
| Purpose | Centralized configuration management |
| Config Location | Native filesystem / Git repository |


**Responsibilities:**
- Serve configuration for all services
- Support environment-specific configurations (dev, docker, prod)
- Enable runtime configuration refresh
- Encrypt sensitive configuration values


### 4.3 API Gateway


| Attribute | Value                          |
|-----------|--------------------------------|
| Port | 8222                           |
| Artifact ID | gateway                        |
| Purpose | API routing and load balancing |


**Responsibilities:**
- Route requests to appropriate microservices
- Load balancing across service instances
- Cross-cutting concerns (CORS, rate limiting)
- Request/response logging
- Circuit breaker pattern implementation


**Route Configuration:**


| Route | Target Service | Path Pattern |
|-------|---------------|--------------|
| Bookings | BOOKINGS | /api/v1/bookings/** |
| Customers | CUSTOMER | /api/v1/customers/** |
| Branches | BRANCH | /api/v1/branches/** |
| Notifications | NOTIFICATION | /api/v1/notifications/** |


### 4.4 Bookings Service


| Attribute | Value |
|-----------|-------|
| Port | 8070 |
| Artifact ID | bookings |
| Database | PostgreSQL (booking_db) |
| Messaging | Kafka Producer |


**Responsibilities:**
- CRUD operations for bookings/appointments
- Booking validation and availability checking
- Publish booking events to Kafka
- Integration with Branch and Customer services


**API Endpoints:**


| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/v1/bookings | Get all bookings |
| GET | /api/v1/bookings/{id} | Get booking by ID |
| POST | /api/v1/bookings | Create new booking |
| PUT | /api/v1/bookings/{id} | Update booking |
| DELETE | /api/v1/bookings/{id} | Delete booking |
| PATCH | /api/v1/bookings/{id}/status | Update booking status |


### 4.5 Customer Service


| Attribute | Value |
|-----------|-------|
| Port | 8090 |
| Artifact ID | customer |
| Database | MongoDB (customer_db) |


**Responsibilities:**
- Customer profile management
- Customer authentication and authorization
- Customer search and filtering
- Customer preferences management


**API Endpoints:**


| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/v1/customers/getAllCustomers | Get all customers |
| GET | /api/v1/customers/{id} | Get customer by ID |
| POST | /api/v1/customers | Create new customer |
| PUT | /api/v1/customers/{id} | Update customer |
| DELETE | /api/v1/customers/{id} | Delete customer |


### 4.6 Branch Service


| Attribute | Value |
|-----------|-------|
| Port | 8060 |
| Artifact ID | branch |
| Database | PostgreSQL (branch_db) |
| Messaging | Kafka Producer/Consumer |


**Responsibilities:**
- Branch location management
- Operating hours and availability
- Service offerings per branch
- Branch capacity management


**API Endpoints:**


| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/v1/branches | Get all branches |
| GET | /api/v1/branches/{id} | Get branch by ID |
| POST | /api/v1/branches | Create new branch |
| PUT | /api/v1/branches/{id} | Update branch |
| DELETE | /api/v1/branches/{id} | Delete branch |


### 4.7 Notification Service


| Attribute | Value                     |
|-----------|---------------------------|
| Port | 8040                      |
| Artifact ID | notification              |
| Database | MongoDB (notification_db) |
| Messaging | Kafka Consumer            |


**Responsibilities:**
- Email notification delivery
- SMS notification delivery (future)
- Push notification delivery (future)
- Notification template management
- Notification history and tracking


**Supported Notification Types:**
- Booking confirmation
- Booking reminder
- Booking cancellation
- Booking update
- Welcome email
- Password reset


---


## 5. Data Architecture


### 5.1 PostgreSQL Databases


**booking_db Schema:**


```sql
-- Bookings Table
CREATE TABLE bookings (
   id BIGSERIAL PRIMARY KEY,
   booking_reference VARCHAR(50) UNIQUE NOT NULL,
   customer_id BIGINT NOT NULL,
   branch_id BIGINT,
   service_type VARCHAR(100),
   booking_date DATE NOT NULL,
   booking_time TIME NOT NULL,
   status VARCHAR(50) DEFAULT 'pending',
   notes TEXT,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```


**branch_db Schema:**


```sql
-- Branches Table
CREATE TABLE branches (
   id BIGSERIAL PRIMARY KEY,
   branch_code VARCHAR(50) UNIQUE NOT NULL,
   name VARCHAR(255) NOT NULL,
   address VARCHAR(500),
   city VARCHAR(100),
   phone VARCHAR(50),
   email VARCHAR(255),
   opening_time TIME DEFAULT '09:00:00',
   closing_time TIME DEFAULT '18:00:00',
   is_active BOOLEAN DEFAULT true,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```


### 5.2 MongoDB Collections


**customer_db Collections:**


```javascript
// customers collection
{
   _id: ObjectId,
   customerCode: String,
   firstName: String,
   lastName: String,
   email: String,
   phone: String,
   address: {
       street: String,
       city: String,
       state: String,
       zipCode: String
   },
   preferences: {
       preferredBranch: String,
       notificationPreferences: Array
   },
   createdAt: Date,
   updatedAt: Date
}
```


**notification_db Collections:**


```javascript
// notifications collection
{
   _id: ObjectId,
   notificationType: String,
   recipientId: String,
   recipientEmail: String,
   subject: String,
   message: String,
   status: String,
   sentAt: Date,
   createdAt: Date
}


// notification_templates collection
{
   _id: ObjectId,
   templateCode: String,
   notificationType: String,
   channel: String,
   subjectTemplate: String,
   bodyTemplate: String,
   isActive: Boolean
}
```


---


## 6. Communication Patterns


### 6.1 Synchronous Communication


- **Protocol:** HTTP/REST
- **Format:** JSON
- **Service Discovery:** Eureka client-side load balancing
- **Error Handling:** Circuit breaker with Resilience4j


### 6.2 Asynchronous Communication


- **Message Broker:** Apache Kafka
- **Serialization:** JSON


**Kafka Topics:**


| Topic | Producer | Consumer | Purpose |
|-------|----------|----------|---------|
| booking-created | Bookings | Notification | Trigger confirmation email |
| booking-updated | Bookings | Notification | Trigger update notification |
| booking-cancelled | Bookings | Notification | Trigger cancellation email |
| booking-reminder | Scheduler | Notification | Trigger reminder email |


---


## 7. Security Architecture


### 7.1 Authentication & Authorization


- **Method:** JWT-based authentication (planned)
- **Token Issuer:** Auth service (planned)
- **Token Validation:** Gateway-level validation


### 7.2 Network Security


- **Internal Communication:** Docker network isolation
- **External Access:** Through API Gateway only
- **CORS:** Configured at Gateway level


### 7.3 Data Security


- **Database Credentials:** Environment variables
- **Sensitive Data:** Encrypted at rest (planned)
- **API Keys:** Stored in Config Server with encryption


---


## 8. Deployment Architecture


### 8.1 Docker Compose (Development/Testing)


```yaml
Services:
 - discovery (Eureka)
 - config-server
 - gateway
 - postgres
 - mongodb
 - zookeeper
 - kafka
 - bookings
 - branch
 - customer
 - notification
```


### 8.2 Service Dependencies


```
Level 1: zookeeper, postgres, mongodb
Level 2: kafka, discovery
Level 3: config-server
Level 4: gateway
Level 5: bookings, branch, customer, notification
```


### 8.3 Health Checks


All services expose health endpoints via Spring Boot Actuator:


| Service | Health Endpoint |
|---------|----------------|
| Discovery | http://localhost:8761/actuator/health |
| Config Server | http://localhost:8888/actuator/health |
| Gateway | http://localhost:8000/actuator/health |
| Bookings | http://localhost:8070/actuator/health |
| Branch | http://localhost:8060/actuator/health |
| Customer | http://localhost:8090/actuator/health |
| Notification | http://localhost:8080/actuator/health |


---


## 9. Frontend Application


### 9.1 Application Structure


```
/app
 /page.tsx              # Home page
 /book/page.tsx         # Booking flow
 /appointments/page.tsx # User appointments
 /services/page.tsx     # Services listing
 /login/page.tsx        # Authentication
 /register/page.tsx     # Registration
 /admin/page.tsx        # Admin dashboard
 /dashboard/page.tsx    # User dashboard


/components
 /booking/              # Booking flow components
 /admin/                # Admin components
 /ui/                   # Shared UI components


/lib
 /api.ts                # API client
 /auth-context.tsx      # Authentication context
 /store.ts              # Application state
```


### 9.2 Key Features


- Multi-step booking wizard
- Real-time time slot availability
- Past time slot auto-disable
- Appointment management (reschedule, update, cancel, delete)
- Admin dashboard for booking management
- Responsive design for mobile and desktop


### 9.3 API Integration


The frontend communicates with backend services through:
- **Development:** Direct service URLs or mock data
- **Production:** API Gateway (http://localhost:8000/api/v1/*)


---


## 10. Monitoring & Observability


### 10.1 Logging


- **Framework:** SLF4J with Logback
- **Format:** JSON (for production)
- **Correlation:** Request ID propagation via headers


### 10.2 Metrics


- **Endpoint:** /actuator/metrics
- **Metrics Provider:** Micrometer
- **Dashboards:** Grafana (planned)


### 10.3 Tracing


- **Protocol:** OpenTelemetry (planned)
- **Backend:** Jaeger/Zipkin (planned)


---


## 11. Development Guidelines


### 11.1 Local Development Setup


```bash
# 1. Start infrastructure
docker-compose up -d postgres mongodb zookeeper kafka


# 2. Start discovery service
cd services/discovery && mvn spring-boot:run


# 3. Start config server
cd services/config-server && mvn spring-boot:run


# 4. Start application services
cd services/bookings && mvn spring-boot:run -Dspring.profiles.active=local
cd services/customer && mvn spring-boot:run -Dspring.profiles.active=local
cd services/branch && mvn spring-boot:run -Dspring.profiles.active=local


# 5. Start frontend
npm run dev
```


### 11.2 Docker Development


```bash
# Full stack startup
docker-compose up --build


# Rebuild specific service
docker-compose up --build <service-name>


# View logs
docker-compose logs -f <service-name>


# Clean restart
docker-compose down -v && docker-compose up --build
```


### 11.3 Configuration Profiles


| Profile | Purpose | Activation |
|---------|---------|------------|
| default | Local development | No profile specified |
| docker | Docker environment | SPRING_PROFILES_ACTIVE=docker |
| prod | Production | SPRING_PROFILES_ACTIVE=prod |


---


## 12. API Reference


### 12.1 Common Response Formats


**Success Response:**
```json
{
   "status": "success",
   "data": { ... },
   "timestamp": "2026-01-28T00:00:00.000Z"
}
```


**Error Response:**
```json
{
   "status": "error",
   "error": {
       "code": "ERR_001",
       "message": "Error description"
   },
   "timestamp": "2026-01-28T00:00:00.000Z"
}
```


### 12.2 HTTP Status Codes


| Code | Meaning |
|------|---------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 500 | Internal Server Error |
| 503 | Service Unavailable |


---


## 13. Future Enhancements


### Phase 2
- [ ] JWT Authentication implementation
- [ ] Role-based access control (RBAC)
- [ ] SMS notification channel
- [ ] Push notification support


### Phase 3
- [ ] Kubernetes deployment manifests
- [ ] Helm charts for deployment
- [ ] Distributed tracing with Jaeger
- [ ] Centralized logging with ELK stack


### Phase 4
- [ ] Multi-tenancy support
- [ ] Payment integration
- [ ] Reporting and analytics dashboard
- [ ] Mobile application (React Native)


---


## 14. Contact & Support


| Role              | Contact      |
|-------------------|--------------|
| Software Engineer | Mfundo Mgubo | |


---


**Document Version:** 1.0
**Last Updated:** January 2026
**Author:** Mfundo Mgubo




