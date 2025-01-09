# UI Testing with Selenide and Page Object Model

## Application under test

UI automation tests using Selenide and Page Object Model design pattern
on a demo application called [Swag Labs](https://www.saucedemo.com).

## Prerequisites

- **JDK 17** or higher.
- **Google Chrome** browser installed (as the default browser for testing).
- **Gradle** installed for build automation.

## Additional resources:
- [Selenide Quick Start](https://selenide.org/quick-start.html)
- [Selenide Documentation](https://selenide.org/documentation.html)
- [PageObject by Martin Fowler](https://martinfowler.com/bliki/PageObject.html)
- [Page Object Model (POM) | Design Pattern](https://medium.com/tech-tajawal/page-object-model-pom-design-pattern-f9588630800b)

## Some useful commands

Run all your tests:
```bash
./gradlew test
```

Run a single test file:
```bash
./gradlew test --tests <fully-qualified-test-class-name>#<method-name>
```

Run using continuous build (watches file and reruns tests after changes are saved):
```bash
./gradlew test --tests <fully-qualified-test-class-name> -t
```

Reference: [Gradle Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html)