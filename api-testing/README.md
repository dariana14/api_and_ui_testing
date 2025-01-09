# API Testing with REST Assured

API testing with REST Assured, the software under test is  
an Open API [Restful Booker](https://restful-booker.herokuapp.com/apidoc/index.html).

## Prerequisites

- **JDK 17** or higher.
- **Gradle** installed for build automation.

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
