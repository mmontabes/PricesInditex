This project is a microservice developed in Java (Spring Boot) that manages product prices for different brands and provides a REST access point for queries. It follows a hexagonal architecture and uses an in-memory H2 database for testing and temporary data storage.

## Description
The service allows querying product prices based on a date range, brand, and specific product. The prices include information such as priority, validity period, and the currency in which they are expressed.

Features:
- Data persistence in an in-memory H2 database to facilitate testing.
- REST APIs designed following hexagonal architecture principles.

- ## Technology stack
- Language: Java 17
- Framework: Spring Boot
- Database: H2 (in memory)
- Architecture: Hexagonal
- API Ceration: OpenAPI
- Testing: Junit

- ## Architecture
The project follows a hexagonal architecture where:
1. Domain: Contains entities, services, and business logic.
2. Application: Manages use cases and connects requests to the domain.
3. Infrastructure: Handles persistence and external adapters (e.g., REST controllers).

- ## Covered test cases:
Query at 10:00 on the 14th.
Query at 16:00 on the 14th.
Query at 21:00 on the 14th.
Query at 10:00 on the 15th.
Query at 21:00 on the 16th.
