# Contributing to Java Offline Analysis Engine

First of all, thank you for your interest in contributing.

This project is intentionally designed as a **low-level, deterministic static analysis engine**.  
Contributions are welcome, but **architecture and consistency matter more than speed**.

Please read this document carefully before opening a pull request.

## Project philosophy

This engine follows a few strict principles:

- **Offline only**
  - No HTTP
  - No APIs
  - No databases
  - No cloud dependencies

- **Deterministic execution**
  - Same input → same output
  - No non-deterministic behavior
  - CI-safe by design

- **Explicit architecture**
  - Clear module boundaries
  - No hidden coupling
  - No “magic” frameworks

- **Engineering-first**
  - Correctness > features
  - Structure > shortcuts
  - Long-term maintainability > quick wins

If a contribution violates any of these principles, it will not be accepted.

## Repository structure

Each module has a **single responsibility**:

