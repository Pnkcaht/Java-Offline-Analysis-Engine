# Glossary

This glossary defines the **canonical and non-negotiable vocabulary** used throughout the Java Offline Analysis Engine.

All terms defined here have a **single, precise meaning** within the context of this project. These definitions apply to:

* Documentation
* Source code
* API design
* Plugin contracts
* Error messages and diagnostics

If a term appears in code or documentation and is defined here, it **must** conform to the definition below.

---

## Analysis

A deterministic, side-effect-free computation performed over the internal model in order to derive structural or semantic information from bytecode.

An analysis:

* Does not mutate input data
* Does not perform IO
* Does not depend on execution order
* Produces explicit outputs and diagnostics

---

## Analysis Scope

The complete and explicit set of bytecode artifacts provided as input to the engine for a single execution.

The analysis scope defines:

* Which classes are considered internal
* Which references are considered external
* The upper bound of resolution

Anything outside the analysis scope is treated as **external by definition**.

---

## Artifact

A physical input unit provided to the engine, such as:

* A `.class` file
* A `.jar` archive
* A directory containing compiled classes

Artifacts are sources of bytecode, not model elements.

---

## Bytecode

The JVM instruction-level representation of compiled programs, as defined by the Java Virtual Machine Specification.

Bytecode is the **single source of truth** for all analyses. No assumptions beyond what is encoded in bytecode are permitted.

---

## Classpath

The ordered set of locations used to resolve symbolic references during analysis.

Classpath resolution affects:

* Type resolution
* Method linkage
* Dependency classification

Classpath does not imply execution semantics.

---

## Diagnostic

A structured, immutable message emitted during analysis describing an error, warning, or informational condition.

Diagnostics:

* Are never silent
* Are associated with a precise location when possible
* Do not alter analysis results

---

## Element

An immutable domain object representing a program construct extracted from bytecode.

Examples include:

* Class elements
* Method elements
* Field elements
* Module elements

Elements are uniquely identified within an analysis scope.

---

## External Element

An element that is referenced by bytecode but not defined within the analysis scope.

External elements:

* Are represented explicitly
* May contain partial or missing structural data
* Are never mutated or resolved implicitly

---

## Graph

An immutable directed structure representing relationships between elements.

Graphs:

* Contain nodes and edges
* Are deterministic
* Do not change after construction

---

## Node

A vertex within a graph, typically corresponding to a model element.

Nodes do not contain behavior; they only participate in relationships.

---

## Edge

A directed relationship between two nodes in a graph.

Edges are typed and ordered and may represent:

* Calls
* Dependencies
* Visibility relationships

---

## Call Graph

A directed graph where nodes are methods and edges represent potential invocation relationships derived from bytecode.

Call graphs model *possibility*, not guaranteed runtime execution.

---

## Dependency Graph

A directed graph representing structural dependencies between program elements.

Dependency graphs are strictly structural and do not imply control flow.

---

## Surface

The externally visible set of program elements as determined by bytecode-level access flags.

Surface definition:

* Ignores runtime behavior
* Ignores reflection
* Is strictly declarative

---

## Pipeline

An explicit, ordered execution plan describing how analyses are executed.

The pipeline:

* Enforces isolation
* Controls concurrency
* Owns execution order

---

## Stage

A single execution unit within a pipeline.

A stage:

* Has a single responsibility
* Declares inputs and outputs
* Produces explicit results

---

## Stage Result

The immutable output of a stage execution.

A stage result may contain:

* Output data
* Diagnostics
* Execution metadata

---

## Plugin

An extension component that contributes analysis or reporting logic through stable, explicit interfaces.

Plugins:

* Do not mutate engine state
* Do not bypass the pipeline
* Are consumers, not owners, of the model

---

## Report

A read-only representation of analysis results intended for external consumption.

Reports do not affect pipeline execution or analysis semantics.

---

## Resolution

The process of mapping symbolic references in bytecode to concrete model elements.

Resolution may be:

* Complete
* Partial
* Unresolved

Resolution never invents information.

---

## Determinism

The guarantee that identical inputs always produce identical outputs.

Determinism applies regardless of:

* Execution order
* Concurrency level
* Plugin configuration

---

## Immutability

The property of an object whose state cannot change after construction.

Immutability is enforced structurally and is not optional.

---

## Best-Effort Analysis

An execution mode in which recoverable issues produce diagnostics while allowing analysis to continue.

---

## Fail-Fast

An execution strategy where unrecoverable structural errors immediately abort analysis.

---

## Side Effect

Any observable interaction with the external environment, including:

* File IO
* Network access
* Process execution

Side effects are forbidden outside explicitly designated layers.

---

## Canonical Model

The authoritative internal representation derived from bytecode.

The canonical model is immutable, deterministic, and shared across all analyses.

---
