# ADR-0002: Immutable Internal Model

## Status

Accepted

## Context

The Java Offline Analysis Engine performs static analysis across potentially large codebases and must support:

* Deterministic execution
* Predictable behavior under concurrency
* Reuse of analysis results across stages
* Safe parallel execution where applicable

The engine defines an internal representation (`engine-model`) that captures program structure, relationships, and metadata derived from bytecode.

A critical architectural decision is whether this internal model should be mutable or immutable.

## Decision

All domain objects in `engine-model` SHALL be **strictly immutable**.

Once created, model objects:

* Must not change state
* Must not expose mutators
* Must not allow partial initialization

Immutability is enforced by design, not convention.

## Rationale

The decision is driven by the following factors:

* **Thread Safety**: Immutable objects are inherently thread-safe
* **Determinism**: Eliminates hidden state changes across stages
* **Reasoning Simplicity**: Reduces cognitive load when understanding data flow
* **Cache Safety**: Enables safe reuse and memoization
* **Pipeline Isolation**: Prevents stages from influencing each other implicitly

## Design Implications

To support immutability:

* All fields are `final`
* Construction is explicit and complete
* Collections are immutable or defensively copied
* No lazy initialization is permitted

Derived data must be represented as new objects rather than mutations.

## Consequences

### Positive

* Safe concurrent access to model objects
* Simplified pipeline execution semantics
* Easier testing and reproducibility
* Clear ownership and lifecycle boundaries

### Negative / Trade-offs

* Increased object allocation
* Potential memory overhead
* Need for builder or factory patterns

These trade-offs are accepted in favor of correctness and clarity.

## Non-Goals

* The model is not optimized for in-place mutation
* The model is not designed for incremental updates

Incremental or interactive analysis is explicitly out of scope.

## Notes

This decision directly influences:

* Concurrency guarantees
* Memory model design
* Analysis implementation strategies
* Plugin interaction rules
