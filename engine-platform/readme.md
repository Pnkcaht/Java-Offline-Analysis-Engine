# engine-platform

Stable extension contracts for the Java Offline Analysis Engine.

This module defines Service Provider Interfaces (SPI) that allow
extensions and plugins to integrate with the engine without
depending on internal implementation details.

## Goals

- Provide long-term stable APIs
- Decouple plugins from engine internals
- Enable future plugin and extension mechanisms

## Design principles

- Interfaces only
- No execution logic
- No framework dependencies
