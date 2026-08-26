# Secure Resource Booking System

A secure RESTful Resource Booking System built using **Spring Boot, Java
17, Spring Security, JWT, PostgreSQL, JPA/Hibernate, and Maven**.

The system provides role-based access for **ADMIN** and **USER** users.
Users can view resources and manage their own reservations, while
administrators have management access.

## Features

-   JWT-based authentication
-   BCrypt password encryption
-   Role-Based Access Control (RBAC)
-   ADMIN and USER roles
-   Stateless Spring Security
-   Resource CRUD operations
-   Reservation management
-   USER-specific reservation access
-   JWT-based user identification
-   PostgreSQL database integration
-   Spring Data JPA / Hibernate
-   RESTful APIs
-   Swagger / OpenAPI support
-   Postman API testing

## Technology Stack

  Technology          Usage
  ------------------- ----------------------------------
  Java 17             Programming language
  Spring Boot         Backend framework
  Spring Security     Authentication and authorization
  JWT                 Token-based authentication
  BCrypt              Password hashing
  Spring Data JPA     Database access
  Hibernate           ORM
  PostgreSQL          Database
  Maven               Dependency management
  Postman             API testing
  Swagger / OpenAPI   API documentation

## Project Structure

``` text
src/main/java/com/exelynt/resourcebookingsystem
├── controller
├── entity
├── enums
├── repository
├── service
└── security
```

## Authentication Flow

``` text
Client
  |
  | POST /api/auth/login
  v
Spring Security
  |
  v
CustomUserDetailsService
  |
  v
PostgreSQL
  |
  v
BCrypt password verification
  |
  v
JWT generated
  |
  v
Client
  |
  | Authorization: Bearer <JWT>
  v
JwtAuthenticationFilter
  |
  v
SecurityContext
  |
  v
Protected REST API
```

## User Roles

### ADMIN

ADMIN users have full access to resources and reservations:

-   Create resources
-   View resources
-   Update resources
-   Delete resources
-   View reservations
-   Create/manage reservations
-   Update reservations
-   Delete reservations

### USER

USER users can:

-   View resources
-   Create reservations
-   View their own reservations

USER users cannot:

-   Create resources
-   Update resources
-   Delete resources
-   Access another user's reservations
-   Perform administrator operations

## Important Security Rule

The reservation owner is **not taken from the request body**.

When a USER creates a reservation, the application obtains the
authenticated user's email from the JWT/Spring Security context:

``` java
Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

String email = authentication.getName();
```

The corresponding user is loaded from the database and assigned to the
reservation. This prevents a user from submitting another user's ID and
creating a reservation on behalf of that user.

## API Endpoints

### Authentication

#### Login

``` http
POST /api/auth/login
```

Example:

``` text
http://localhost:8080/api/auth/login?email=user@example.com&password=123
```

A successful login returns a JWT token.

Use the returned token for protected endpoints:

``` http
Authorization: Bearer <JWT_TOKEN>
```

### Resources

  Method   Endpoint                Access
  -------- ----------------------- -------------
  POST     `/api/resources`        ADMIN
  GET      `/api/resources`        ADMIN, USER
  GET      `/api/resources/{id}`   ADMIN, USER
  PUT      `/api/resources/{id}`   ADMIN
  DELETE   `/api/resources/{id}`   ADMIN

### Reservations

  Method   Endpoint                   Access
  -------- -------------------------- -----------------------
  POST     `/api/reservations`        ADMIN, USER
  GET      `/api/reservations`        ADMIN: all, USER: own
  GET      `/api/reservations/{id}`   ADMIN: any, USER: own
  PUT      `/api/reservations/{id}`   ADMIN
  DELETE   `/api/reservations/{id}`   ADMIN

## Example Resource Request

``` http
POST http://localhost:8080/api/resources
Authorization: Bearer <ADMIN_JWT>
Content-Type: application/json
```

``` json
{
  "name": "Conference Room",
  "description": "Main meeting room",
  "price": 1000,
  "type": "ROOM",
  "available": true
}
```

## Example Reservation Request

``` http
POST http://localhost:8080/api/reservations
Authorization: Bearer <USER_JWT>
Content-Type: application/json
```

The authenticated user is determined from the JWT. The client should not
specify or override the reservation owner.

## HTTP Status Codes

  Status   Meaning
  -------- ------------------------------------
  200      Request successful
  201      Resource created
  401      Authentication required or invalid
  403      Authenticated but not authorized
  404      Resource not found
  500      Server error

## Database

The project uses PostgreSQL.

Example configuration:

``` properties
spring.datasource.url=jdbc:postgresql://localhost:5432/resource_booking_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
```

**Do not commit real passwords, JWT secrets, or other credentials to
GitHub.**

## Running the Project

### 1. Clone the repository

``` bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
cd resource-booking-system
```

### 2. Configure PostgreSQL

Create the database:

``` sql
CREATE DATABASE resource_booking_db;
```

Update the database credentials in `application.properties` or
environment configuration.

### 3. Build the project

``` bash
mvn clean install
```

### 4. Run the application

``` bash
mvn spring-boot:run
```

Application URL:

``` text
http://localhost:8080
```

## API Documentation

If SpringDoc is enabled:

``` text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

``` text
http://localhost:8080/v3/api-docs
```

## Testing

The APIs were tested using **Postman**.

Important security scenarios:

-   ADMIN login returns a JWT
-   USER login returns a JWT
-   ADMIN can perform resource CRUD
-   USER can read resources
-   USER receives `403 Forbidden` for resource POST/PUT/DELETE
-   USER can create a reservation
-   USER can view only their own reservations
-   ADMIN can view all reservations
-   Protected endpoints reject requests without a valid JWT

## Security Configuration

The application uses:

-   BCrypt password hashing
-   JWT stateless authentication
-   `SessionCreationPolicy.STATELESS`
-   JWT authentication filter
-   Spring Security `SecurityContext`
-   Role-based authorization
-   CSRF disabled for the stateless REST API

## Notes

This project is a **backend REST API**. A separate frontend is not
required for the API implementation. Postman and Swagger can be used to
test the endpoints.

## Future Improvements

-   Refresh tokens
-   Global exception handling
-   DTOs instead of exposing entities directly
-   Bean Validation
-   Centralized API error responses
-   Pagination and sorting
-   Reservation conflict checking
-   Flyway or Liquibase database migrations
-   Environment variables for secrets
-   Frontend client

## Author

**Vinay Lakade**

Spring Boot REST API project demonstrating JWT authentication, Spring
Security, RBAC, JPA/Hibernate, PostgreSQL, and RESTful API development.
