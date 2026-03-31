# Quantity Measurement App

## 📋 Project Description

A robust Spring Boot application designed to handle arithmetic operations (addition, subtraction, division) and conversions between various quantity measurements including length (feet, inches, yards), weight, volume, and temperature. The application implements a generic quantity system with unit interfaces to support multi-category measurements, ensuring type safety and extensibility.

---

## ✨ Features

- **Equality Checks**: Compare quantities across different units within the same category
- **Unit Conversions**: Convert between compatible units (e.g., feet to inches, kilograms to grams)
- **Arithmetic Operations**: Perform addition, subtraction, and division on quantities
- **Multi-Category Support**: Handle length, weight, volume, and temperature measurements
- **Generic Quantity System**: Extensible architecture using interfaces for different unit types
- **Database Integration**: Persistent storage with JPA and H2/MySQL support
- **Authentication**: JWT-based authentication with OAuth Google login
- **RESTful API**: Clean REST endpoints for all operations
- **N-Tier Architecture**: Separation of concerns with service, repository, and controller layers

---

## 🛠 Tech Stack

### Backend
- **Java**: 21
- **Spring Boot**: 3.2.5
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database operations
- **JWT**: Token-based authentication
- **OAuth 2.0**: Google authentication integration

### Database
- **H2**: In-memory database for testing
- **MySQL**: Production database

### Tools & Build
- **Maven**: Dependency management and build tool
- **Lombok**: Code generation for boilerplate

---

## 📁 Project Structure

```
quantitymeasurement/
├── src/main/java/com/app/quantitymeasurement/
│   ├── Application.java                 # Main Spring Boot application class
│   ├── config/                          # Configuration classes
│   ├── controller/                      # REST controllers (Auth, QuantityMeasurement)
│   ├── dto/                             # Data Transfer Objects
│   ├── entity/                          # JPA entities
│   ├── exception/                       # Custom exceptions
│   ├── model/                           # Domain models and units
│   ├── repository/                      # Data access layer
│   ├── security/                        # Security configuration and JWT utilities
│   ├── service/                         # Business logic layer
│   └── util/                            # Utility classes
├── src/main/resources/
│   ├── application.properties           # Application configuration
│   └── db/schema.sql                    # Database schema
└── src/test/java/com/app/quantitymeasurement/
    ├── ApplicationTests.java            # Main test class
    └── [feature-specific test packages] # Comprehensive test suites

```

---

## 🚀 Installation & Setup Instructions

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- MySQL (optional, H2 for development)

### Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd quantitymeasurement
   ```

2. **Configure Database** (Optional)
   - For MySQL: Update `application.properties` with your database credentials
   - For H2: No configuration needed (default)

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Verify installation**
   - Application will start on `http://localhost:8080`
   - H2 Console (if using H2): `http://localhost:8080/h2-console`

---

## 📖 Usage Instructions

### Authentication
- **Login**: POST `/api/auth/login` with username/password
- **Google OAuth**: GET `/api/auth/google-success` (after OAuth flow)

### Quantity Operations
Use tools like Postman or curl to interact with the REST API:

```bash
# Compare two quantities
curl -X POST http://localhost:8080/api/quantity/compare \
  -H "Content-Type: application/json" \
  -d '{"q1":{"value":1,"unit":"FEET"},"q2":{"value":12,"unit":"INCH"}}'

# Add quantities
curl -X POST http://localhost:8080/api/quantity/add \
  -H "Content-Type: application/json" \
  -d '{"q1":{"value":5,"unit":"FEET"},"q2":{"value":6,"unit":"INCH"}}'
```

## 🔗 API Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/auth/login` | User authentication | `username`, `password` (form data) |
| GET | `/api/auth/google-success` | Google OAuth callback | - |
| POST | `/api/quantity/compare` | Compare two quantities | `QuantityRequest` |
| POST | `/api/quantity/add` | Add two quantities | `QuantityRequest` |
| POST | `/api/quantity/subtract` | Subtract quantities | `QuantityRequest` |
| POST | `/api/quantity/divide` | Divide quantities | `QuantityRequest` |
| POST | `/api/quantity/convert` | Convert between units | `ConvertRequest` |
| GET | `/api/quantity/all` | Get all stored measurements | - |
| GET | `/api/quantity/{id}` | Get measurement by ID | - |
| DELETE | `/api/quantity/{id}` | Delete measurement by ID | - |

### Request/Response Examples

**QuantityRequest**:
```json
{
  "q1": {"value": 5.0, "unit": "FEET"},
  "q2": {"value": 60.0, "unit": "INCH"}
}
```

**ConvertRequest**:
```json
{
  "value": 100.0,
  "fromUnit": "CENTIMETER",
  "toUnit": "METER"
}
```

---

## 📸 Screenshots / Demo

*Coming soon - Screenshots of the API responses and database interactions will be added here.*

---

## 🏗 Development Timeline

- **Feb 17, 2026**: Project initialization and first commit
- **Feb 18-19, 2026**: Implemented feet equality measurement (UC1)
- **Feb 20, 2026**: Added inch equality (UC2) and generic length support (UC3)
- **Feb 23, 2026**: Extended units with yard equality (UC4) and unit-to-unit conversion (UC5)
- **Feb 26-27, 2026**: Implemented unit addition (UC6), target unit addition (UC7), and standalone units (UC8)
- **Mar 5, 2026**: Added weight measurements (UC9), generic quantity class (UC10), volume measurements (UC11), subtraction/division operations (UC12), and centralized arithmetic logic (UC13)
- **Mar 10, 2026**: Temperature measurements with selective arithmetic support (UC14)
- **Mar 11, 2026**: N-Tier architecture implementation (UC15)
- **Mar 16, 2026**: Database integration (UC16)
- **Mar 17-18, 2026**: Spring Framework integration (UC17)
- **Mar 25, 2026**: OAuth authentication with Google (UC18)
- **Mar 30, 2026**: Final integration and README completion

---


## 🔍 Challenges Faced

- **Generic Quantity System**: Designing a flexible interface-based system to support multiple measurement categories while maintaining type safety
- **Unit Conversion Logic**: Implementing accurate conversion ratios and handling edge cases for different unit types
- **Arithmetic Operations**: Ensuring operations only work on compatible units within the same category
- **N-Tier Architecture**: Properly separating concerns between controllers, services, and repositories
- **Database Integration**: Mapping complex quantity objects to relational database structures
- **Authentication Flow**: Integrating JWT with OAuth for seamless user authentication
- **Branch Management**: Managing 18 feature branches and resolving merge conflicts

## 📚 Learnings

- **Test-Driven Development (TDD)**: Extensive use of unit tests drove clean, reliable code implementation
- **Spring Boot Ecosystem**: Deep understanding of Spring Security, JPA, and RESTful API development
- **Generic Programming**: Using interfaces and generics for extensible, type-safe quantity systems
- **Git Branching Strategy**: Effective feature branch workflow for large-scale development
- **OAuth Integration**: Implementing third-party authentication flows
- **Database Design**: Proper entity relationships and schema design for measurement data
- **API Design**: RESTful principles and proper HTTP method usage

## 🚀 Future Enhancements

- **Web UI**: React/Angular frontend for user-friendly quantity operations
- **Additional Units**: Support for more measurement categories (area, time, currency)
- **Advanced Operations**: Multiplication, complex calculations, and formula support
- **Caching**: Redis integration for performance optimization
- **Microservices**: Split into separate services for scalability
- **API Documentation**: Swagger/OpenAPI integration
- **Containerization**: Docker support for easy deployment
- **Monitoring**: Application metrics and health checks

## 🤝 Contributing Guidelines

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/<AmazingFeature>`)
3. Commit your changes (`git commit -m 'Add some <AmazingFeature>'`)
4. Push to the branch (`git push origin feature/<AmazingFeature>`)
5. Open a Pull Request

### Development Guidelines
- Follow TDD principles - write tests before implementation
- Maintain code coverage above 80%
- Use meaningful commit messages
- Update documentation for API changes
- Ensure all tests pass before submitting PR


---
