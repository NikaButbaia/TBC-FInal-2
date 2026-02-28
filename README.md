# TBC Digital — Automation Framework
**Final Project 2** &nbsp;·&nbsp; Java + Playwright + BackstopJS + Rest Assured

---

Rewrites every Project 1 scenario from scratch. Runs on three browsers in parallel. Hits the real API and cross-checks it against the live UI. Spoofs GPS coordinates via SQL. Takes pixel-level screenshots and diffs them. Does all of this on desktop and mobile simultaneously.

---

## 📦 Dependencies

```
Java 21              Playwright 1.52       TestNG 7.11
Maven 3.8+           Rest Assured 5.4      Jackson 2.17
MyBatis 3.5.16       SQL Server 12.4.2     BackstopJS 6.3.25
Allure 2.27          JavaFaker 1.0.2       Node.js 16+
```

Browsers (Chrome, Firefox, WebKit) are pulled automatically by Playwright on first run.
SQL Server needs to be running locally with the schema below applied before any test executes.

---

## 🚀 Before You Run Anything

**Apply the database schema**

```sql
DROP TABLE IF EXISTS location_cases;

CREATE TABLE location_cases (
    id              INT,
    city_name       VARCHAR(50),
    latitude        FLOAT,
    longitude       FLOAT,
    max_distance_km FLOAT
);

INSERT INTO location_cases VALUES
(1, 'Tbilisi', 41.6938, 44.8015, 90.0),
(2, 'Batumi',  41.6417, 41.6367, 80.0),
(3, 'Kutaisi', 42.2679, 42.7181, 70.0);
```


## 📁 Layout

```
src/
├── main/java/ge/tbc/testautomation/
│   ├── api/
│   │   ├── pojo/
│   │   │   ├── ListItem.java
│   │   │   ├── PageResponse.java
│   │   │   ├── SectionComponent.java
│   │   │   └── SectionInputs.java
│   │   └── ApiClient.java
│   ├── data/
│   │   ├── Constants.java
│   │   ├── FakerDataGenerator.java
│   │   └── TestDataGenerator.java
│   ├── dataproviders/
│   │   ├── AbroadTransferDataProvider.java
│   │   ├── ApiDataProvider.java
│   │   ├── BranchDataProvider.java
│   │   └── LoanDataProvider.java
│   ├── db/
│   │   ├── mapper/BranchMapper.java
│   │   ├── model/Branch.java
│   │   └── session/MyBatisSessionProvider.java
│   ├── pages/                          # locators only, zero logic
│   │   ├── AbroadTransferPage.java
│   │   ├── AnonymousTipPage.java
│   │   ├── BranchesPage.java
│   │   ├── ChatBotPage.java
│   │   ├── LoansPage.java
│   │   └── MainPage.java
│   ├── steps/                          # fluent actions + assertions
│   │   ├── AbroadTransferSteps.java
│   │   ├── AnonymousTipSteps.java
│   │   ├── ApiSteps.java
│   │   ├── BranchesSteps.java
│   │   ├── ChatBotSteps.java
│   │   ├── LoansSteps.java
│   │   └── MainSteps.java
│   └── utils/
│       ├── MapUtils.java
│       ├── MobileDevice.java
│       ├── UiExtractorUtils.java
│       └── Utils.java
├── main/resources/
│   └── mybatis-config.xml
└── test/java/ge/tbc/testautomation/
    ├── apitests/
    │   ├── ApiBaseTest.java
    │   ├── ApiTest.java
    │   └── ApiUiConsistencyTest.java
    └── uitests/
        ├── BaseTest.java               # browser lifecycle lives here
        ├── AbroadTransferTest.java
        ├── AnonymousTipTest.java
        ├── BranchesTest.java
        ├── ChatBotTest.java
        └── LoanTest.java

visual-regression/
├── backstop_data/
│   ├── bitmaps_reference/
│   ├── bitmaps_test/
│   └── engine_scripts/
│       ├── pages/
│       │   ├── anonymousTipPage.js
│       │   ├── loanByAmountPage.js
│       │   └── loanByIncomePage.js
│       ├── onReady.js
│       ├── onReadyLoan.js
│       └── onReadyLoanByIncome.js
├── backstop.json
└── backstop_anon.json

testng-desktop.xml
testng-mobile.xml
pom.xml
```

---

## Parallelism

Each `<test>` block in the XML gets its own `Playwright → Browser → BrowserContext → Page`. Nothing is shared across threads. Ever.

```
testng-desktop.xml   parallel="tests"   thread-count=3   Chrome / Firefox / WebKit
testng-mobile.xml    parallel="tests"   thread-count=2   Chrome / WebKit
```

Mobile context: `isMobile: true` · `deviceScaleFactor: 3` · `390×844` · touch enabled · geolocation injected.

All test logic is identical between desktop and mobile. The `isMobile` flag flows from `BaseTest` into steps and page objects where locator resolution or navigation differs (burger menu vs hover dropdowns).

---

## ⚠️ The WebKit Geolocation Problem

WebKit blocks `context.setGeolocation()`. Full stop. `BranchesTest` depends entirely on GPS spoofing — it can't run on WebKit, so it doesn't. Everything else passes on all three browsers.

| | Chrome | Firefox | WebKit |
|--|:--:|:--:|:--:|
| `AbroadTransferTest` | ✅ | ✅ | ✅ |
| `LoanTest` | ✅ | ✅ | ✅ |
| `AnonymousTipTest` | ✅ | ✅ | ✅ |
| `ChatBotTest` | ✅ | ✅ | ✅ |
| `BranchesTest` | ✅ | ✅ | ❌ |

---

## Configuration

| | |
|--|--|
| Base URL | `Constants.TBC_URL` |
| Desktop viewport | 1920 × 1080 |
| Mobile viewport | 390 × 844 (iPhone 12 Pro) |
| Default timeout | 30 000 ms |
| Geolocation seed | Tbilisi — 41.698, 44.800 |
| Allure output | `allure-results/` |

---

## 🎯 What Gets Tested

### UI

| Test | What it does | Data |
|------|-------------|------|
| `AbroadTransferTest` | Validates the currency conversion calculator end-to-end | `AbroadTransferDataProvider` |
| `LoanTest` | Runs the loan calculator both by amount and by income | `LoanDataProvider` |
| `BranchesTest` | Spoofs GPS per city, checks every map marker is within the expected radius | SQL via MyBatis |
| `AnonymousTipTest` | Fills and submits the anonymous tip form with generated data | `FakerDataGenerator` |
| `ChatBotTest` | Sends a message to the chatbot and validates the response | `Constants` |

### API — `GET /api/v1/sites/pages/{pageId}?locale=ka-GE`

| Test | Type | What it checks |
|------|:----:|----------------|
| `testConsumerLoanApiHappyPath` | happy path | 200 · title · slug · sections present |
| `testConsumerLoanDeserializationIntegrity` | happy path | every POJO field maps correctly |
| `testInvalidPageIdReturnsError` | negative | error body for bad slug IDs *(data-driven)* |
| `testRandomPageIdReturns404` | negative | 404 on random UUID IDs |
| `extractApiData` | integration | pulls title + list labels out of the response |
| `validateUiMatchesApi` | integration | live UI must match what the API returned |

The consistency check treats the API as ground truth. It calls the endpoint, deserializes into `PageResponse`, extracts the title and first list label, navigates to the consumer loan page, and asserts both fields match.

### Visual — BackstopJS · Playwright engine · 0.5% mismatch threshold

| Scenario | Config |
|----------|--------|
| Loan calculator by amount | `backstop.json` → `onReadyLoan.js` |
| Loan calculator by income | `backstop.json` → `onReadyLoanByIncome.js` |
| Anonymous tip filled form | `backstop_anon.json` → `onReady.js` |

---

## Checklist

- [x] Playwright rewrite of all Project 1 scenarios
- [x] Chrome + Firefox + WebKit
- [x] Parallel execution
- [x] Mobile emulation
- [x] SQL parametrization via MyBatis
- [x] TestNG DataProviders
- [x] Visual regression
- [x] API happy path + negatives
- [x] POJO deserialization
- [x] API → UI consistency
- [x] Allure — `@Step` `@Feature` `@Story` `@Severity`