# ADR-0001: Bytecode Analysis over AST Analysis

## Status

Accepted

## Context

The Java Offline Analysis Engine must operate without access to source code and without executing target programs. The engine is intended to analyze compiled artifacts such as `.class` files, `.jar`s, and modular runtime images.

A fundamental architectural decision is whether analysis should be performed on:

* Abstract Syntax Trees (AST) derived from source code, or
* JVM bytecode derived from compiled artifacts

## Decision

The engine SHALL operate exclusively on **JVM bytecode**, not on source-level ASTs.

Bytecode is treated as the canonical input representation for all analyses.

## Rationale

The decision is based on the following considerations:

* **Availability**: Bytecode is always present in compiled artifacts, while source code often is not
* **Determinism**: Bytecode eliminates variability introduced by source formatting, compiler flags, or preprocessing
* **Completeness**: Bytecode reflects the actual executed program, including compiler-generated constructs
* **Offline Operation**: No build tool or source dependency resolution is required
* **Uniformity**: A single representation regardless of language features or syntax sugar

## Consequences

### Positive

* The engine can analyze third-party libraries and closed-source systems
* Analysis results are consistent across environments
* No dependency on build systems, compilers, or source parsers

### Negative / Trade-offs

* Loss of high-level semantic information (comments, variable names in some cases)
* More complex analysis logic compared to AST-based approaches
* Greater reliance on accurate bytecode interpretation

## Notes

This decision directly influences:

* The choice of ASM as the bytecode library
* The internal model design
* The exclusion of source-based features
