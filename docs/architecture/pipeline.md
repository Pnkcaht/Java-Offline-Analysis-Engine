# Pipeline Architecture

## Concept

The pipeline defines **what runs**, **in which order**, and **under what constraints**. It is the backbone of engine execution.

A pipeline consists of ordered **Stages**, each representing a deterministic transformation or computation step.

## Stage Characteristics

Each Stage:

* Has a single responsibility
* Declares explicit inputs and outputs
* Is deterministic
* Declares its side effects

Stages must not:

* Access global mutable state
* Perform hidden IO
* Depend on implicit execution order

## Execution Model

* Pipelines are fully defined before execution
* Execution order is immutable
* Parallelism is explicit and opt-in
* The pipeline contains no domain logic

## Stage Results

A Stage produces a `StageResult` containing:

* Output data
* Diagnostics
* Execution metadata

Failures are explicit and configurable.

## Invariants

* Stages do not communicate directly
* All data exchange is explicit
* Stages are reusable
* The pipeline enforces boundaries
