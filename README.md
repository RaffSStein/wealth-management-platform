# Wealth Management Platform

This repository contains a modular, event-driven microservices platform for wealth management. Each module represents a business domain and is implemented as an independent Spring Boot microservice.

## Architecture
- **Event-based**: Services communicate primarily via asynchronous events using Apache Kafka as the event broker.
- **Database**: Each service uses PostgreSQL as its persistent storage.
- **Local Development**: Docker containers are provided for all infrastructure components (Kafka, PostgreSQL, etc.) to simplify local development and testing.

## Modules
- proposal-service
- portfolio-service
- customer-service
- product-service
- order-service
- advisor-service
- reporting-service
- notification-service

## How to Run
1. Clone the repository.
2. Start the infrastructure with Docker Compose (see the provided docker-compose.yml).
3. Build all modules with Maven:
   ```
   mvn clean install
   ```
4. Start each microservice individually (from its module directory):
   ```
   mvn spring-boot:run
   ```

## Event-Driven Approach
All business events (e.g., proposal created, order executed) are published to Kafka topics. Other services subscribe to relevant topics to react to these events, ensuring loose coupling and scalability.

## Technologies
- Java 17
- Spring Boot 3
- Apache Kafka
- PostgreSQL
- Docker

## Notes
- Each service has its own README with more details.
- For local development, use the provided Docker setup for Kafka and PostgreSQL.

---

This project is a starting point for a scalable, event-driven wealth management platform. Contributions and suggestions are welcome!

