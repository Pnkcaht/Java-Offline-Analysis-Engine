# engine-plugin

Plugin loading infrastructure for the Java Offline Analysis Engine.

This module provides a deterministic, offline mechanism for discovering
and registering engine extensions using Java's ServiceLoader SPI.

## Responsibilities

- Load EngineExtension implementations
- Register analysis providers
- Expose plugin contributions to the core pipeline

## Non-goals

- No runtime classpath modification
- No remote plugin loading
- No framework integration
