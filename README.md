# Wealth Management Platform

This repository contains a modular, event-driven microservices platform for wealth management. 
Each module represents a business domain and is implemented as an independent Spring Boot microservice.

> **Note:** This repository is a work in progress. The platform is under active development and subject to frequent changes.

## Architecture
- **Event-based**: Services communicate primarily via asynchronous events using Apache Kafka as the event broker.
- **Database**: Each service uses PostgreSQL as its persistent storage. Each microservice has its own database instance, following the microservices best practice of database per service, even if all use PostgreSQL.
- **Local Development**: Docker containers are provided for all infrastructure components (Kafka, Kafka UI, PostgreSQL, Splunk, Splunk Forwarder, etc.) to simplify local development and testing. Each service and infrastructure component is configured to avoid port conflicts.
- **Logging & Monitoring**: All microservices log in JSON format to files under `/logs`, with log patterns including correlation IDs and user context. Logs are collected by a Splunk Universal Forwarder and made available in Splunk for centralized analysis. Logback configuration is shared and parametrized via Spring properties.

## Modules
- [proposal-service](business-modules/proposal-service/README.md)
- [portfolio-service](business-modules/portfolio-service/README.md)
- [customer-service](business-modules/customer-service/README.md)
- [product-service](business-modules/product-service/README.md)
- [order-service](business-modules/order-service/README.md)
- [advisor-service](business-modules/advisor-service/README.md)
- [reporting-service](business-modules/reporting-service/README.md)
- [notification-service](business-modules/notification-service/README.md)
- [user-service](business-modules/user-service/README.md)
- [profiler-service](business-modules/profiler-service/README.md)
- [core/platform-core](core/platform-core/README.md) (shared core logic)
- [integration](integration/README.md) (external service integrations)

Each business module may contain submodules for API data models (OpenAPI-generated), event data models, and core logic.


## How to Run
1. Clone the repository.
2. Start the infrastructure with Docker Compose (see the provided docker-compose.yml). This will start Kafka, Kafka UI, PostgreSQL, Splunk, and Splunk Forwarder.
3. Build all modules with Maven:
   ```
   mvn clean install
   ```
4. Start each microservice individually (from its module directory):
   ```
   mvn spring-boot:run
   ```

## Event-Driven Approach
All business events (e.g., proposal created, order executed) are published to Kafka topics. Other services subscribe to relevant topics to react to these events, ensuring loose coupling and scalability. Event payloads are defined in dedicated submodules (e.g., proposal-event-data) and shared via dependencies.

## Technologies
- Java 17
- Spring Boot 3
- Apache Kafka
- PostgreSQL
- Docker Compose
- Splunk (for log aggregation)
- MapStruct (for mapping between entities and DTOs)
- OpenAPI (for API and event model generation)
- Lombok, Jakarta (for code simplification and modern Java EE)

## API & Data Model
- API and event payloads are defined in OpenAPI YAML files in dedicated submodules (e.g., proposal-api-data, proposal-event-data).
- Code generation is managed via Maven plugins, and shared models are imported as dependencies in other modules.

## Logging
- All logs are written in JSON format to `/logs/{app-name}.json`.
- Logback configuration is shared and parametrized via Spring properties.
- Correlation IDs, user IDs, and other context are included in each log entry via MDC.
- Splunk Universal Forwarder monitors all JSON log files and forwards them to Splunk for centralized analysis.

## Local Development Notes
- Each microservice and infrastructure component is configured to use unique ports to avoid conflicts.
- All infrastructure services (Kafka, Kafka UI, PostgreSQL, Splunk, Splunk Forwarder) are started via Docker Compose.
- For local development, you can access Kafka UI at [http://localhost:8080](http://localhost:8080) (or the port configured in docker-compose.yml), and Splunk at [http://localhost:8000](http://localhost:8000).

## Extending the Platform
- To add a new business domain, create a new module following the existing structure.
- Define API and event models in OpenAPI YAML files in dedicated submodules.
- Use the shared core and integration modules for common logic and integration tests.

## Security
- JWT-based authentication is supported and integrated with Spring Security.
- Correlation IDs and user context are propagated and logged for traceability.

## Example Use Cases
- Portfolio management, order execution, customer onboarding, product catalog, advisor management, reporting, notifications, user and permission management.

## Contact
For questions or contributions, please open an issue or submit a pull request.
