# Appium Android Test Automation Framework

[![Android Appium Tests](https://github.com/hkk032/appium-android-framework/actions/workflows/android-tests.yml/badge.svg)](https://github.com/hkk032/appium-android-framework/actions/workflows/android-tests.yml)

A mobile test automation framework for Android, built with **Java, Appium, TestNG and Maven**. It follows the **Page Object Model** and runs on an Android emulator in **GitHub Actions** on every push.

This is a learning project. It tests Appium's open-source sample app, **ApiDemos**.

## Tech stack

| Area | Tool |
|---|---|
| Language | Java 17 |
| Mobile automation | Appium 3 with the UiAutomator2 driver |
| Test runner | TestNG |
| Build tool | Apache Maven |
| CI/CD | GitHub Actions (Android emulator on Ubuntu) |

## Project structure

```
appium-android-framework/
├── apps/ApiDemos-debug.apk              # app under test
├── src/test/java/com/hemant/qa/
│   ├── base/BaseTest.java               # driver setup and teardown
│   ├── pages/ApiDemosPage.java          # Page Object: locators and actions
│   └── tests/ApiDemosTest.java          # test cases
├── .github/workflows/android-tests.yml  # CI pipeline
├── testng.xml                           # TestNG suite
└── pom.xml                              # Maven dependencies and plugins
```

## Test scenarios

| Test | What it checks |
|---|---|
| `viewsMenuShowsButtonsOption` | The Views menu lists the Buttons option |
| `alertDialogOpensAndCloses` | An alert dialog opens and can be dismissed |
| `canScrollToTabsOption` | A long list can be scrolled to reach a lower item |

## Design notes

- A new Appium session starts for every test, so tests stay independent.
- Locators and screen actions live in the Page Object, and tests read like user steps.
- Explicit waits (`WebDriverWait`) are used instead of fixed sleeps.
- Locator preference: accessibility ID, then resource ID, then UiAutomator selectors.

## Run locally

Prerequisites: JDK 17, Maven, Node.js 22, Android SDK with an emulator (API 34), and `ANDROID_HOME` set.

```bash
# 1. Install Appium and the Android driver (one time)
npm install -g appium
appium driver install uiautomator2

# 2. Start the emulator
emulator -avd <your_avd_name>

# 3. Start the Appium server (separate terminal)
appium

# 4. Run the tests
mvn clean test
```

## CI pipeline

On every push to `main`, GitHub Actions:

1. Sets up JDK 17 and Node.js
2. Installs Appium and the UiAutomator2 driver
3. Boots an Android emulator
4. Starts the Appium server and runs `mvn clean test`
5. Uploads the TestNG reports and the Appium log as build artifacts