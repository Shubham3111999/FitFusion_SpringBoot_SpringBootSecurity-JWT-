# FitFusion

FitFusion is a fitness management application that enables trainers to assign workouts and diets to customers through an online platform. Customers can securely log in using JWT authentication, access their exercise routines, and follow them accordingly.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Features](#features)
- [APIs](#apis)
- [Service Layer](#service-layer)
- [Repository Layer](#repository-layer)
- [Exception Handling](#exception-handling)
- [JWT Authentication](#jwt-authentication)
- [Security Configuration](#security-configuration)
- [Setup Instructions](#setup-instructions)
- [License](#license)

## Technologies Used

- Java 17
- Spring Boot 2.7.x
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (JSON Web Token)
- Lombok
- MySQL Database

## Project Structure

The project follows a typical layered architecture:

- **Model Layer:** Contains entity classes for User, Diet, and Exercise.
- **DTO Layer:** Data Transfer Objects for API communication.
- **Repository Layer:** JPA repositories for database operations.
- **Service Layer:** Business logic implementation.
- **Controller Layer:** REST APIs for handling requests.
- **Exception Layer:** Custom exceptions for error handling.
- **Config Layer:** Security configuration with JWT.

## Features

- Trainer APIs for managing diets and exercises.
- Admin APIs for managing users.
- Customer APIs for fetching assigned diets and exercises.
- Public APIs for user registration and authentication.
- Role-based access control using Spring Security.
- JWT-based authentication and authorization.

## APIs

### AuthController

**Public API**:

- **POST** `/auth/login`: Allows users to log in with their credentials.

### DietController

**Trainer APIs**:

- **GET** `/diet/all`: Fetch all diets.
- **GET** `/diet/{id}`: Fetch a diet by its ID.
- **POST** `/diet/create/{userId}`: Create a diet for a user using their ID.
- **PUT** `/diet/{id}`: Update a diet by its ID.
- **DELETE** `/diet/{id}`: Delete a diet by its ID.

### ExerciseController

**Trainer APIs**:

- **GET** `/exercise/all`: Fetch all exercises.
- **GET** `/exercise/{id}`: Fetch an exercise by its ID.
- **POST** `/exercise/create/{userId}`: Create an exercise for a user using their ID.
- **PUT** `/exercise/{id}`: Update an exercise by its ID.
- **DELETE** `/exercise/{id}`: Delete an exercise by its ID.

### UserController

**Admin APIs**:

- **GET** `/user/all`: Fetch all users.
- **GET** `/user/{id}`: Fetch a user by their ID.
- **PUT** `/user/{id}`: Update a user by their ID.
- **DELETE** `/user/{id}`: Delete a user by their ID.

**Customer APIs**:

- **GET** `/user/exercise/{id}`: Fetch all exercises for the logged-in customer.
- **GET** `/user/diet/{id}`: Fetch all diets for the logged-in customer.

**Public APIs**:

- **POST** `/user/register`: Register a new user and assign a role.

## Service Layer

The following service classes handle the business logic:

- `DietService`
- `ExerciseService`
- `UserService`

## Repository Layer

The following JPA repositories are defined:

- `DietRepository`
- `ExerciseRepository`
- `UserRepository`

## Exception Handling

Custom exceptions are defined for handling scenarios where a resource is not found:

- `DietNotFoundException`
- `ExerciseNotFoundException`
- `UserNotFoundException`

## JWT Authentication

The JWT functionality is implemented using:

- `JwtAuthenticationHelper`: Generates and validates JWT tokens.
- `JwtAuthenticationFilter`: Filters and validates JWT tokens in requests.

## Security Configuration

The security configuration is handled in the `FitFusionSecurityConfig` class, which includes:

- Password encoding using `PasswordEncoder`.
- `AuthenticationManager` setup.
- Configuration of `securityFilterChain` to allow `/auth/login` and `/user/register` as open endpoints.

## Setup Instructions

1. **Clone the Repository:**

```bash
git clone https://github.com/yourusername/FitFusion.git
cd FitFusion
```
2. **Configure the Database:**

    Update the database settings in `src/main/resources/application.yml` to match your local environment.

3. **Build the Project:**

    ```bash
    mvn clean install
    ```

4. **Run the Application:**

    ```bash
    mvn spring-boot:run
    ```
