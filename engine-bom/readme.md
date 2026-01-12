# Engine-bom

Bill of Materials (BOM) for the Java Offline Analysis Engine.

This module centralizes dependency versions used across all engine modules,
ensuring consistent and reproducible builds.

## Responsibilities

- Define versions for third-party libraries
- Avoid version drift between modules
- Provide a single source of truth for dependency management

## Notes

- This module contains no Java source code
- It is not meant to be published as a runtime artifact
- It is consumed via Gradle dependency constraints
