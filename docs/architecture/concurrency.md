# Concurrency Model

## Philosophy

Concurrency is treated as an optimization, not a requirement. Correctness and determinism take precedence.

## Thread Safety Rules

* All objects in `engine-model` are immutable
* Stages may run concurrently only if declared safe
* Shared infrastructure must be immutable or synchronized

## Allowed Concurrency

* Independent bytecode reads
* Independent analysis stages
* Isolated report writers

## Forbidden Concurrency

* Mutation of shared context
* Symbol table modification
* Uncoordinated diagnostics writes

## Context Confinement

`AnalysisContext` is thread-confined unless explicitly documented.
