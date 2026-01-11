# ADR-0004: No Reflection in Core Architecture

## Status

Accepted

## Context

Reflection in Java enables dynamic inspection and invocation of classes, methods, and fields at runtime. While powerful, reflection introduces implicit behavior, weakens static guarantees, and obscures control flow.

The Java Offline Analysis Engine prioritizes:

* Deterministic behavior
* Explicit data flow
* Predictable performance
* Clear architectural boundaries

Given these priorities, the use of reflection as a structural mechanism must be evaluated.

## Decision

The core architecture of the engine SHALL NOT use Java reflection as a mechanism for:

* Discovery of core components
* Invocation of analysis logic
* Access to internal state
* Lifecycle management

All interactions between components must be expressed through **explicit interfaces and contracts**.

## Scope

This restriction applies to:

* Core engine modules
* Pipeline execution
* Analysis stages
* Plugin interaction with the engine

Limited reflection MAY be permitted only at strict boundaries (e.g. JVM bootstrapping or tooling), and must be explicitly documented and isolated.

## Rationale

The decision is motivated by the following concerns:

* **Predictability**: Reflection hides control flow and dependencies
* **Safety**: Bypasses compile-time checks
* **Performance**: Prevents static optimization and increases runtime overhead
* **Debuggability**: Makes execution paths harder to trace
* **Maintainability**: Encourages implicit coupling

By avoiding reflection, the engine enforces architectural clarity and explicitness.

## Design Implications

As a consequence:

* Component wiring is explicit
* Extension points are defined via SPI interfaces
* Discovery mechanisms favor configuration or service registration
* Errors surface at compile-time whenever possible

## Consequences

### Positive

* Transparent execution flow
* Easier reasoning and debugging
* Stronger static guarantees
* Improved long-term maintainability

### Negative / Trade-offs

* Less flexibility for dynamic behavior
* More upfront design effort

These trade-offs are accepted to preserve architectural integrity.

## Alternatives Considered

* Partial reflection with conventions
* Annotation-driven discovery

These alternatives were rejected due to implicit coupling and reduced clarity.

## Notes

This decision reinforces:

* Plugin boundary isolation
* Pipeline determinism
* Model immutability guarantees
