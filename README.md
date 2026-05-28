# AutomationExercise API Test Automation Framework

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![RestAssured](https://img.shields.io/badge/RestAssured-5.5.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-red)
![Allure](https://img.shields.io/badge/Allure-2.29.1-blue)
![Maven](https://img.shields.io/badge/Maven-3.x-purple?logo=apache-maven)
![ExtentReports](https://img.shields.io/badge/ExtentReports-5.1.1-yellow)

A production-ready **REST API test automation framework** built with **Java**, **RestAssured**, and **TestNG**, targeting the [AutomationExercise](https://automationexercise.com/api_list) public API. Designed following industry best practices with layered architecture, dual reporting (Allure + ExtentReports), dynamic test data generation, and JSON schema validation.

---

## 📁 Project Structure

```
src/
└── test/
    ├── java/
    │   └── api/
    │       ├── base/              # BaseTest — suite-level RestAssured config
    │       ├── client/            # ApiClient — reusable HTTP method wrappers
    │       ├── constants/         # Endpoints — all API endpoint constants
    │       ├── models/
    │       │   ├── request/       # POJO models for request payloads
    │       │   └── response/      # POJO models for response deserialization
    │       ├── reports/           # ExtentReports setup, logging, TestNG listener
    │       ├── specifications/    # RestAssured RequestSpec / ResponseSpec builders
    │       ├── tests/             # Test classes (one per API feature domain)
    │       ├── utils/
    │       │   └── PayloadBuilder/ # Builder classes for each request model
    │       │   ├── FakerUtils.java # Faker-based random data generation
    │       │   ├── PojoConverter.java   # POJO → Map converter for form-data
    │       │   ├── ApiReportUtil.java   # Request/Response logging utility
    │       │   ├── AllureAttachments.java
    │       │   ├── ProductDataGenerator.java
    │       │   └── SchemaValidatorUtil.java
    │       └── validators/        # ResponseValidator — assertion helper methods
    └── resources/
        ├── config/               # (Reserved for environment config files)
        ├── schemas/              # JSON schema files for schema validation
        └── testdata/             # (Reserved for external test data files)
```

---

## 🚀 Tech Stack

| Tool / Library        | Version   | Purpose                                  |
|-----------------------|-----------|------------------------------------------|
| Java                  | 21        | Core language                            |
| Maven                 | 3.x       | Build and dependency management          |
| RestAssured           | 5.5.0     | HTTP client for API testing              |
| TestNG                | 7.11.0    | Test execution engine                    |
| Allure                | 2.29.1    | Advanced test reporting                  |
| ExtentReports         | 5.1.1     | HTML test reports with request/response logs |
| Jackson               | 2.18.2    | JSON serialization / deserialization     |
| JavaFaker             | 1.0.2     | Dynamic test data generation             |
| Lombok                | 1.18.34   | Boilerplate code reduction               |
| Log4j2                | 2.24.3    | Application logging                      |
| JSON Schema Validator | 5.5.0     | API response schema validation           |

---

## 🔌 APIs Under Test

| #  | API Endpoint                | Method | Test Class            |
|----|-----------------------------|--------|-----------------------|
| 1  | `/productsList`             | GET    | `ProductTests`        |
| 2  | `/brandsList`               | GET    | `BrandsTests`         |
| 3  | `/brandsList`               | POST   | `BrandsTests`         |
| 4  | `/searchProduct`            | POST   | `SearchProductTests`  |
| 5  | `/verifyLogin`              | POST   | `UserLoginTests`      |
| 6  | `/verifyLogin`              | DELETE | `UserLoginTests`      |
| 7  | `/createAccount`            | POST   | `UserAccountTests`    |
| 8  | `/updateAccount`            | PUT    | `UserAccountTests`    |
| 9  | `/deleteAccount`            | DELETE | `UserAccountTests`    |
| 10 | `/getUserDetailByEmail`     | GET    | `UserAccountTests`    |

---

## ⚙️ Prerequisites

- **Java 21** — [Download JDK](https://adoptium.net/)
- **Maven 3.6+** — [Download Maven](https://maven.apache.org/download.cgi)
- **Allure CLI** (optional, for HTML report generation) — [Install Allure](https://allurereport.org/docs/install/)
- An internet connection (tests run against the live `automationexercise.com` API)

---

## 🛠️ Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/automationexercise-api-framework.git
cd automationexercise-api-framework
```

### 2. Install dependencies

```bash
mvn clean install -DskipTests
```

---

## ▶️ Running Tests

### Run all tests

```bash
mvn clean test
```

### Run a specific test class

```bash
mvn clean test -Dtest=UserAccountTests
```

### Run with a specific TestNG XML suite

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## 📊 Test Reports

### Allure Report (Recommended)

After running tests, generate the Allure HTML report:

```bash
allure serve target/allure-results
```

Or generate a static report:

```bash
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
```

The Allure report includes:
- Test pass/fail/skip summary
- Severity levels and test owners
- Request and response attachments per test

### ExtentReports HTML Report

An auto-generated HTML report is saved after each run at:

```
target/API_Report_AutomationExcercise.html
```

Open directly in any browser — includes collapsible request/response logs per test.

---

## 🏗️ Framework Architecture

### Layer Diagram

```
┌─────────────────────────────────────────────┐
│               Test Layer                    │
│  (BrandsTests, UserAccountTests, ...)       │
└───────────────────┬─────────────────────────┘
                    │
┌───────────────────▼─────────────────────────┐
│           Payload Builder Layer             │
│  (UserPayloadBuilder, ProductPayloadBuilder)│
└───────────────────┬─────────────────────────┘
                    │
┌───────────────────▼─────────────────────────┐
│              Client Layer                   │
│      (ApiClient — HTTP method wrappers)     │
└───────────────────┬─────────────────────────┘
                    │
┌───────────────────▼─────────────────────────┐
│          Specification Layer                │
│   (RequestSpecs, ResponseSpecs — RestAssured│
│    spec builders)                           │
└───────────────────┬─────────────────────────┘
                    │
┌───────────────────▼─────────────────────────┐
│           Validation & Reporting            │
│  (ResponseValidator, ReportLogger,          │
│   AllureAttachments, SchemaValidatorUtil)   │
└─────────────────────────────────────────────┘
```

### Key Design Decisions

- **Single `ApiClient`** — all HTTP calls go through one class; switching the underlying library only requires changes here.
- **`PayloadBuilder` pattern** — test data construction is isolated from test logic, making tests concise and readable.
- **`PojoConverter`** — bridges POJO models to `Map<String, String>` for `form-urlencoded` requests without polluting the model layer.
- **`ResponseValidator`** — all assertions centralised, enabling consistent error messages across tests.
- **Dual reporting** — Allure for CI/pipeline integration; ExtentReports for local HTML browsing.
- **ThreadLocal `ExtentTest`** — ensures parallel test execution does not cause report data races.

---

## 🔧 Configuration

The base URL is configured in `BaseTest.java`:

```java
RestAssured.baseURI = "https://automationexercise.com/api";
```

To support multiple environments, move this into a `config.properties` file under `src/test/resources/config/` and load it via a `ConfigReader` utility (see the **Step-by-Step Improvements** section below).

---

## ✅ Test Data Strategy

All test data is generated dynamically at runtime using **JavaFaker** via `FakerUtils`:

```java
FakerUtils.getEmail()       // random unique email
FakerUtils.getPassword()    // secure random password
FakerUtils.getFirstName()   // realistic first name
```

This avoids test pollution from hardcoded data and ensures each test run is independent.

---

## 🔬 JSON Schema Validation

Schema validation is applied to product and brand list responses:

```
src/test/resources/schemas/brands-schema.json
src/test/resources/schemas/products-schema.json
```

To add a new schema, place a `.json` schema file in the `schemas/` directory and call:

```java
SchemaValidatorUtil.validateSchema(response, "schemas/my-new-schema.json");
```

---

## 📝 Adding New Tests — Checklist

Follow this pattern when adding a new API endpoint test:

1. **Add the endpoint constant** to `api/constants/Endpoints.java`
2. **Create a request POJO** in `api/models/request/` with `@Getter @Setter @Builder`
3. **Create a PayloadBuilder** in `api/utils/PayloadBuilder/`
4. **Add a converter** in `PojoConverter.java` (if the endpoint uses form-data)
5. **Create the test class** in `api/tests/`, extending `BaseTest`
6. **Add Allure annotations** (`@Epic`, `@Feature`, `@Story`, `@Severity`, `@Description`, `@Owner`)
7. **Register the class** in `testng.xml`

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/add-new-endpoint-tests`
3. Commit your changes: `git commit -m "feat: add search product negative test cases"`
4. Push to the branch: `git push origin feature/add-new-endpoint-tests`
5. Open a Pull Request

---

## 👨‍💻 Author

**Premdevi Kumawat**  
QA Automation Engineer

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?logo=linkedin)](https://www.linkedin.com/in/premdevikumawat21/)
[![GitHub](https://img.shields.io/badge/GitHub-black?logo=github)](https://github.com/Premdevi9091)
