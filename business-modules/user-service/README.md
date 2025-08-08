# User Service

This microservice manages user accounts for the Wealth Management Platform (WMP).

## Features
- Exposes synchronous CRUD APIs for user management (create, update, enable/disable users).
- Publishes user events to Kafka topics for asynchronous processing.
- Consumes events from Kafka to perform asynchronous CRUD operations on users.
- Integrates with PostgreSQL for persistent storage.
- Supports customizable user settings (e.g., theme, language, preferences).
- Logging is compatible with Splunk and follows the platform's log pattern.

## Architecture
- Spring Boot based microservice.
- Event-driven, using Kafka topics for inter-service communication.
- Database: PostgreSQL (dedicated instance per microservice).
- Docker Compose is used for local development, including Kafka, Kafka UI, and PostgreSQL containers.

## API Endpoints
- `/users`: Create a new user.
- `/users/{id}`: Update user data.
- `/users/{id}/enable`: Enable a user.
- `/users/{id}/disable`: Disable a user.
- `/me`: Retrieve the currently logged-in user's data.

## Development
- DTOs for API and event payloads are defined in dedicated submodules (`user-api-data`, `user-event-data`).
- OpenAPI specifications are used to generate DTOs and API interfaces.
- Logging is centralized and parametrized via logback configuration.

## Work in Progress
This module is under active development. See the main repository README for global architecture and development guidelines.

