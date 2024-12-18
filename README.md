# Blog Platform API

This is a REST API project for a Blog Platform that allows users to register, login, create blog posts, and comment on those posts. The API implements role-based access control and supports CRUD operations for blog posts and comments. Additionally, each blog post must have at least one thumbnail image.

## Features

1. **User Registration & Login**:
    - Role-based access control (admin, user, etc.)
    - JWT authentication for login and secure endpoints
    - User should have permissions assigned based on roles

2. **Blog Post Management**:
    - Create, read, update, and delete blog posts
    - Each blog post must have at least one thumbnail image

3. **Commenting on Blog Posts**:
    - Users can comment on blog posts
    - Each comment is associated with a user and a blog post

4. **Exception Handling**:
    - Global exception handling for consistent error responses

5. **Database**:
    - MySQL used for storing user and blog post data
    - CRUD operations performed via SQL queries

6. **Security**:
    - Spring Security for authentication and authorization
    - JWT token-based security for protected routes

## Technologies Used

- **Spring Boot** (Version 3)
- **Spring Security** for authentication and authorization
- **JWT** for token-based authentication
- **MySQL** for database
- **Lombok** for reducing boilerplate code
- **Postman** for API testing

## Requirements

- Java 17 or later
- MySQL Database
- Maven or Gradle for dependency management

## Setup Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/blog-platform-api.git
