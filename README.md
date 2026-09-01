# JournalApp

JournalApp is a backend REST API built using **Spring Boot** that allows users to securely manage their personal journal entries.

The application uses **MongoDB** for database management and **Spring Security** for authentication and authorization. Each user can manage their own journal entries through RESTful APIs.

## Features

* User registration and management
* User authentication using Spring Security
* Secure journal entry management
* Create journal entries
* View journal entries
* Update journal entries
* Delete journal entries
* MongoDB database integration
* RESTful API architecture

## Technologies Used

* Java
* Spring Boot
* Spring MVC
* Spring Data MongoDB
* MongoDB
* Spring Security
* Maven
* Lombok
* REST API

## Project Structure

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MongoDB
```

The project follows a layered architecture where controllers handle HTTP requests, services contain business logic, repositories communicate with MongoDB, and Spring Security handles authentication.

## Purpose

This project was developed to practice building a real-world backend application using **Spring Boot, REST APIs, MongoDB, and Spring Security**.
