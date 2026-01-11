# ADR-0003: Plugin Boundaries and Isolation

## Status

Accepted

## Context

The Java Offline Analysis Engine is designed to support extensibility through a plugin mechanism. Plugins enable:

* Addition of new analyses
* Custom reporting logic
* Experimental or domain-specific features

Without strict boundaries, plugins risk becoming shortcuts that bypass architectural constraints, leading to:

* Hidden coupling between modules
* Violation of pipeline guarantees
* Uncontrolled access to internal state
* Long-term architectural erosion

A clear definition of **what plugins can and cannot do** is required.

## Decision

Plugins SHALL be strictly isolated from core engine internals and SHALL interact with the engine only through **explicit, stable contracts**.

Plugins are consumers of the engine, not extensions of its internal architecture.

## Boundary Rules

Plugins:

* MAY implement analysis logic using public SPI interfaces
* MAY consume immutable model objects
* MAY emit diagnostics and reports through approved channels

Plugins MUST NOT:

* Mutate engine internal state
* Access `engine-context` internals directly
* Depend on concrete implementations of core modules
* Bypass the pipeline or stage system
* Perform hidden IO or persistence

## Dependency Constraints

Allowed dependencies for plugins:

* `engine-plugin` (SPI)
* `engine-model`
* `engine-common`

Forbidden dependencies:

* `engine-bytecode`
* `engine-io`
* `engine-context`
* `engine-pipeline` internals

This ensures plugins remain analysis-focused and side-effect constrained.

## Lifecycle Model

Plugins follow a well-defined lifecycle:

1. Discovery
2. Registration
3. Initialization
4. Execution
5. Disposal

Plugins do not control engine startup or shutdown.

## Rationale

This isolation provides:

* Architectural stability
* Safe extensibility
* Clear reasoning about execution flow
* Prevention of privilege escalation by plugins

## Consequences

### Positive

* Plugins cannot corrupt engine state
* Core engine remains evolvable
* Easier debugging and testing

### Negative / Trade-offs

* Reduced plugin power
* Need for richer SPI design

These trade-offs are accepted to preserve long-term maintainability.

## Notes

This decision constrains:

* Plugin API design
* Classloader behavior
* Future extension mechanisms
