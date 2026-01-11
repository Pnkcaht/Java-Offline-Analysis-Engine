# Architecture Overview

## Purpose

The **Java Offline Analysis Engine** is a static analysis engine designed to inspect Java bytecode **offline**, without requiring source code, runtime instrumentation, network access, or external services.

The engine operates exclusively on compiled artifacts (e.g. `.class` files, `.jar`s, modular images) and produces deterministic analysis results based solely on the provided inputs.

The primary goals are:

* Perform deep structural analysis of Java programs
* Operate fully offline and deterministically
* Scale to large codebases without JVM instrumentation
* Provide extensible analysis capabilities via a plugin system
* Maintain strict architectural boundaries between concerns

## Explicit Non-Goals

The engine **does not**:

* Execute or simulate Java code
* Perform dynamic or runtime analysis
* Modify bytecode
* Depend on reflection, agents, or JVMTI
* Persist data to databases or external systems
* Act as a build tool or runtime framework

These exclusions are deliberate to preserve determinism, performance, and architectural clarity.

## High-Level Architecture

The engine is organized as a multi-module system with strict dependency direction:

```
IO → Bytecode → Model → Analysis → Report
              ↑
           Pipeline
```

### Module Responsibilities

* **engine-io**: Filesystem, JARs, classpaths, raw resources
* **engine-bytecode**: Bytecode interpretation via ASM
* **engine-model**: Immutable internal representation
* **engine-analysis**: Concrete static analyses
* **engine-report**: Human and machine-readable output
* **engine-pipeline**: Execution orchestration
* **engine-plugin**: Extension points
* **engine-common**: Shared infrastructure

## Data Flow Principles

* Data flows strictly forward
* No backward dependencies
* All domain data is immutable
* Side effects are isolated to IO and reporting layers
