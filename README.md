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
│  (BrandsTests, UserAccountTests, ...)        │
└───────────────────┬─────────────────────────┘
                    │
┌───────────────────▼─────────────────────────┐
│           Payload Builder Layer             │
│  (UserPayloadBuilder, ProductPayloadBuilder) │
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

## 📋 Step-by-Step Improvement Guide

The following improvements are recommended to bring the framework to full industry standard. Each section tells you **what** to change, **where** to change it, and **why**.

---

### Step 1 — Use `@Setter` + `@Builder` on model POJOs (Quick Win)

**File:** `src/test/java/api/models/request/Users.java` (and all models)

**Problem:** `Users.java` uses `@Getter` from Lombok but manually writes all setters — 17 lines of boilerplate.

**Fix:** Replace manual setters with Lombok `@Setter` and `@Builder`:

```java
// Before
@Getter
public class Users {
    private String name;
    // ... 16 more fields
    public void setName(String name) { this.name = name; }
    // ... 16 more setters
}

// After
@Getter
@Setter
@Builder
public class Users {
    private String name;
    private String email;
    private String password;
    // ... rest of fields (no setters needed)
}
```

Then update `UserPayloadBuilder.java` to use the builder pattern:

```java
Users user = Users.builder()
    .name(firstName)
    .email(FakerUtils.getEmail())
    .password(FakerUtils.getPassword())
    .title("Mr")
    // ...
    .build();
```

Apply the same `@Getter @Setter @Builder` pattern to `Brands.java`, `Product.java`, `Category.java`, `SearchProductRequest.java`.

---

### Step 2 — Add `@NoArgsConstructor` + `@AllArgsConstructor` to models

**File:** Same model files above.

**Why:** Lombok's `@Builder` requires a private no-args constructor by default. Adding `@NoArgsConstructor` and `@AllArgsConstructor` alongside `@Builder` supports both construction styles:

```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users { ... }
```

---

### Step 3 — Move `baseURI` to a config properties file

**File:** Create `src/test/resources/config/config.properties`:

```properties
base.url=https://automationexercise.com/api
env=prod
```

**File:** Create `src/test/java/api/utils/ConfigReader.java`:

```java
public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("config.properties not found", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
```

**File:** Update `BaseTest.java`:

```java
@BeforeSuite
public void setup() {
    RestAssured.baseURI = ConfigReader.get("base.url");
    RestAssured.defaultParser = Parser.JSON;
}
```

---

### Step 4 — Fix `BrandsTests` missing `extends BaseTest`

**File:** `src/test/java/api/tests/BrandsTests.java`

**Problem:** `BrandsTests` does **not** extend `BaseTest`, so `RestAssured.baseURI` is never configured for this class. It only works now because `RequestSpecs` reads the static field, which gets set when another test class runs first.

**Fix:**

```java
// Before
public class BrandsTests {

// After
public class BrandsTests extends BaseTest {
```

Do the same audit for `ProductTests.java` and `SearchProductTests.java`.

---

### Step 5 — Replace `PojoConverter` duplicate methods with a generic `Map` builder

**File:** `src/test/java/api/utils/PojoConverter.java`

**Problem:** `userLoginConvertToMap` and `userLoginInvalidConvertToMap` are identical methods — they both just put `email` + `password` into a map.

**Fix:** Delete the duplicate. Use the single `userLoginConvertToMap` for both cases:

```java
// Remove userLoginInvalidConvertToMap entirely.
// In UserLoginTests.invalidUserLogin(), change:
Map<String, String> loginFormData = PojoConverter.userLoginConvertToMap(request);
// (same method — the "invalid" comes from the payload content, not a different converter)
```

---

### Step 6 — Add `@Epic`, `@Feature`, `@Story` Allure annotations to test classes

**Files:** All `*Tests.java` files

**Why:** Groups tests meaningfully in the Allure report dashboard.

```java
// Add at class level:
@Epic("AutomationExercise API")
@Feature("User Account Management")
public class UserAccountTests extends BaseTest {

    @Test
    @Story("Create Account")
    @Description("Verify user account creation with valid data")
    public void createUserAccount() { ... }
```

```java
@Epic("AutomationExercise API")
@Feature("User Authentication")
public class UserLoginTests extends BaseTest { ... }

@Epic("AutomationExercise API")
@Feature("Product Catalogue")
public class ProductTests extends BaseTest { ... }
```

---

### Step 7 — Add the Allure Maven plugin to `pom.xml`

**File:** `pom.xml` — inside the `<plugins>` block:

```xml
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.13.0</version>
    <configuration>
        <reportVersion>2.29.1</reportVersion>
    </configuration>
</plugin>
```

This lets you run `mvn allure:serve` directly instead of needing the Allure CLI installed separately.

---

### Step 8 — Add a `testng.xml` parallel execution configuration

**File:** `testng.xml`

Update to run test classes in parallel, which speeds up execution:

```xml
<suite name="API Automation Suite" parallel="classes" thread-count="3">

    <listeners>
        <listener class-name="api.reports.TestListener"/>
    </listeners>

    <test name="User Tests">
        <classes>
            <class name="api.tests.UserAccountTests"/>
            <class name="api.tests.UserLoginTests"/>
        </classes>
    </test>

    <test name="Product Tests">
        <classes>
            <class name="api.tests.ProductTests"/>
            <class name="api.tests.BrandsTests"/>
            <class name="api.tests.SearchProductTests"/>
        </classes>
    </test>

</suite>
```

> ⚠️ Verify `ApiClient` is instantiated per-test (not as a static field) before enabling parallel execution, to avoid shared state issues.

---

### Step 9 — Add a GitHub Actions CI workflow

**File:** Create `.github/workflows/api-tests.yml`:

```yaml
name: API Test Suite

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]
  schedule:
    - cron: '0 6 * * *'   # Daily at 6 AM UTC

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Set up Java 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
          cache: maven

      - name: Run API tests
        run: mvn clean test

      - name: Upload Allure results
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: allure-results
          path: target/allure-results

      - name: Upload ExtentReport
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: extent-report
          path: target/API_Report_AutomationExcercise.html
```

---

### Step 10 — Update `.gitignore`

**File:** `.gitignore`

Ensure the `target/` directory (compiled classes, test results) is not committed:

```gitignore
# Maven
target/
*.class

# IDE
.idea/
*.iml
.vscode/

# OS
.DS_Store
Thumbs.db

# Logs
*.log
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

## 👤 Author

**Premdevi**
- Framework: RestAssured + TestNG + Allure + ExtentReports
- Target API: [https://automationexercise.com/api_list](https://automationexercise.com/api_list)

---

## 📄 License

This project is for educational and portfolio demonstration purposes.
