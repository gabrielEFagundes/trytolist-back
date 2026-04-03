<div align="center">
  <h1>TryToList Backend</h1>
  A Spring Boot REST API backend for the TryToList task management application.
</div>

## About

**TryToList** is a backend service built with Spring Boot, providing API endpoints for managing tasks and user lists. This is the server-side component of the TryToList application.

## Tech Stack

- **Java 21** - Programming language
- **Spring Boot 3.5.12** - Application framework
- **Spring Data JPA** - ORM and data persistence
- **MySQL** - Database
- **Maven** - Build and dependency management
- **Lombok** - Annotation-based code generation

## Features

- RESTful API for task management
- JPA-based data access layer
- MySQL database integration
- Hot reload support during development (via Spring DevTools)
- Unit testing support (Spring Boot Test)

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- MySQL 5.7 or higher

## Getting Started

### Installation

1. Clone the repository:
```bash
git clone https://github.com/gabrielEFagundes/trytolist-back.git
cd trytolist-back
```

2. Configure MySQL database in application.properties:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/trytolist
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```

3. Build the project:
```bash
./mvnw clean install
```

## Running the app

### Using Maven:
```bash
./mvnw spring-boot:run
```

The API will be available on `http://localhost:8081`

## Project Structure
```code
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/gundes/trytolist/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Dependencies

Key dependencies included in the project:

- spring-boot-starter-web - REST endpoint development
- spring-boot-starter-data-jpa - Data persistence with JPA/Hibernate
- mysql-connector-j - MySQL database driver
- lombok - Reduces boilerplate code
- spring-boot-devtools - Development tools for faster iteration

## Authors:

<div align="center">
  <a href="https://github.com/gabrielEFagundes">Gabriel Ehrat Fagundes</a>
  &
  <a href="https://github.com/Jose7764">José Azarías Pérez Torres</a>
</div>
