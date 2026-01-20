# Maybank Java Backend Assignment

## Overview

This project is a Java Spring Boot REST application developed as part of a backend technical assignment.

The application demonstrates core backend concepts including REST API design, database integration, transaction management, request and response logging, pagination, and third-party API integration.

All APIs can be tested using Postman, and a Postman collection is provided.

---

## Technology Stack

- Java 17  
- Spring Boot 4.0.1  
- Spring Web MVC  
- Spring Data JPA (Hibernate)  
- MSSQL Database  
- Spring WebClient  
- Maven  
- Lombok  
- Postman  

---

## Base Package

```
com.example.maybankassignment
```

---

## Project Structure

```
com.example.maybankassignment
│
├── MaybankApplication.java
│
├── config
│   └── WebClientConfig.java
│
├── controller
│   ├── CustomerController.java
│   └── ExternalProxyController.java
│
├── dto
│   ├── CustomerRequest.java
│   ├── CustomerResponse.java
│   ├── ExternalPostDto.java
│   └── PagedResponse.java
│
├── entity
│   └── Customer.java
│
├── exception
│   └── ApiExceptionHandler.java
│
├── filter
│   └── RequestResponseLoggingFilter.java
│
├── repository
│   └── CustomerRepository.java
│
└── service
    ├── CustomerService.java
    └── ExternalProxyService.java
```

---

## Features

### REST API
- JSON-based REST endpoints
- Tested using Postman
- Clean separation of layers

---

### Database Integration

- Database: MSSQL
- Database name: `DEMODB`
- ORM: Hibernate (JPA)

Hibernate automatically manages schema creation using:

```
spring.jpa.hibernate.ddl-auto=update
```

---

### Transaction Management

All database operations are managed using `@Transactional`.

- INSERT: transactional
- UPDATE: transactional
- GET: transactional with `readOnly = true`

This ensures:

- Data consistency
- Automatic rollback on failure
- Proper transaction boundaries

---

### Pagination

Pagination is implemented using Spring Data Pageable.

- Page size: 10 records per page
- Sorting: descending by ID

Example:

```
GET /api/customers?page=0
```

---

### Request and Response Logging

A global servlet filter logs:

- HTTP method
- Request URI
- Request body
- Response body
- HTTP status
- Execution time
- Correlation ID

Logs are written to:

```
logs/app.log
```

---

### External API Integration

The application demonstrates nested API calling:

```
Client (Postman)
   → Maybank API
       → Third-party API
```

Third-party service used:

```
https://jsonplaceholder.typicode.com
```

Endpoint:

```
GET /api/external/posts/{id}
```

---

### Exception Handling

Global exception handling is implemented using:

```
@RestControllerAdvice
```

---

## API Endpoints

### Customer APIs

| Method | Endpoint | Description |
|------|------|------|
| POST | /api/customers | Create customer |
| PUT | /api/customers/{id} | Update customer |
| GET | /api/customers/{id} | Get customer by ID |
| GET | /api/customers?page=0 | Get customers with pagination |

---

### External API

| Method | Endpoint | Description |
|------|------|------|
| GET | /api/external/posts/{id} | Call third-party API |

---

## How to Run

### 1. Create database

```
CREATE DATABASE TESTDB;
```

---

### 2. Configure database credentials

Update `application.yml`:

```
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=DEMODB
    username: sa
    password: your_password
```

---

### 3. Run the application

```
mvn spring-boot:run
```

---

## Author

Hadri Halim

Java Backend Developer
