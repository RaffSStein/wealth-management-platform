# Profiler Service

This microservice is responsible for managing user permissions, roles, and application sitemap within the Wealth Management Platform (WMP).

## Features
- Exposes APIs to retrieve the sitemap of an application.
- Exposes APIs to retrieve and modify the sitemap and permissions for a specific user.
- Manages configuration tables for sections, features, and permissions.
- Supports event-based communication via Kafka.
- Integrates with PostgreSQL for persistent storage.
- Logging is compatible with Splunk and follows the platform's log pattern.

## Architecture
- Spring Boot based microservice.
- Event-driven, using Kafka topics for inter-service communication.
- Database: PostgreSQL (each microservice has its own DB instance).
- Docker Compose is used for local development, including Kafka, Kafka UI, and PostgreSQL containers.

## API Endpoints
- `/sitemap/{application}`: Retrieve the sitemap for a given application (e.g., B2C, Back Office).
- `/sitemap/{application}/user/{userId}`: Retrieve or modify the sitemap and permissions for a specific user.

## Development
- All DTOs for API and event payloads are defined in dedicated submodules (`profiler-api-data`, `profiler-event-data`).
- OpenAPI specifications are used to generate DTOs and API interfaces.
- Logging is centralized and parametrized via logback configuration.

## Work in Progress
This module is under active development. See the main repository README for global architecture and development guidelines.

