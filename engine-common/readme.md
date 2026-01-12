# engine-common

Shared, framework-free utilities for the Java Offline Analysis Engine.

This module provides small, stable abstractions used across the engine,
without introducing coupling between core components.

## Design principles

- No external dependencies
- No business logic
- No runtime behavior
- Deterministic and test-friendly

## Typical usage

- Lifecycle contracts
- Logging abstractions
- Execution modes
- Time control
- Preconditions
