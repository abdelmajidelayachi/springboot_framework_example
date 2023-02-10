# Spring Boot Example with Spring Security, Docker, Data JPA, and Exception Handling

[![Build Status](https://travis-ci.org/{abdelmajidelayachi}/{repo}.svg?branch=master)](https://travis-ci.org/{abdelmajidelayachi}/{repo})

This project is an example of a Spring Boot application that demonstrates the use of Spring Security, Docker, Data JPA, and handling exceptions.

## Prerequisites
- Java 8 or later
- Docker

## Getting Started

### Clone the repository
```
https://github.com/abdelmajidelayachi/springboot_framework_example.git
```

### run docker compose

```
$ cd springboot_framework_example
$ docker-compose up --build
```

### run tomcat

### Access the application

Access the application at http://localhost:8080/

### Endpoint 
  
  #### Register
  ```
     POST http://localhost:8080/api/v1/auth/register
  ```
   #### Login
  ```
     POST http://localhost:8080/api/v1/auth/login
  ```
After successful login, an authorization token will be returned.

### Authorization
Set the authorization token in the request header:

Authorization: Bearer {token}

### Users
  ```
     GET http://localhost:8080/api/v1/users 
  ```
  

## Features
- Spring Security for authentication and authorization
- Docker for containerization
- Data JPA for database access
- Exception handling for a better user experience

## Tools used
- Spring Boot
- Spring Security
- Docker
- Data JPA
- Hibernate

## Contributing

Please feel free to contribute to this project by creating a pull request or by opening an issue.

## License

This project is open for editing and usage.

## Acknowledgements
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Docker](https://www.docker.com)
- [Data JPA](https://spring.io/projects/spring-data-jpa)
