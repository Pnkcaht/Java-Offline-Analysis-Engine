# 🕵️‍♂️ Java Offline Analysis Engine 

> **Offline, deterministic, Java static analysis engine focused on CI enforcement and architectural rules.**

## Table of Contents

- [Overview](#overview)

- [What this project is (today)](#what-this-project-is-today)
- [What already works (end to end)](#what-already-works-end-to-end)

- [Architecture overview](#architecture-overview)

- [Module-by-module: what exists today](#module-by-module-what-exists-today)
  - [engine-model](#-engine-model)
  - [engine-context](#-engine-context)
  - [engine-io](#-engine-io)
  - [engine-analysis](#-engine-analysis)
  - [engine-pipeline](#-engine-pipeline)
  - [engine-report](#-engine-report)
  - [engine-cli](#-engine-cli)

- [What happens when you run it today](#what-happens-when-you-run-it-today)

- [What is intentionally incomplete](#what-is-intentionally-incomplete)

- [Current maturity level](#current-maturity-level)

- [Next planned milestone](#next-planned-milestone)

- [Final note](#final-note)


## What this project is (today)

The **Java Offline Analysis Engine** is a **pure Java, offline static analysis engine** designed to:

* Run **without HTTP, APIs, databases, or frameworks**
* Be **deterministic and CI-friendly**
* Analyze **compiled Java artifacts (.class files)**
* Enforce **engineering and architectural rules**

At its current stage, the project already forms a **complete end‑to‑end analysis pipeline**, even though some modules are intentionally minimal or stubbed.

This is **not a library demo** — it is an engine skeleton designed to grow safely.

## What already works (end to end)

Right now, the engine successfully:

1. **Starts from a CLI entrypoint**
2. **Parses arguments** (input path, report type)
3. **Scans the filesystem for `.class` files**
4. **Executes an analysis pipeline**
5. **Collects diagnostics (info / warnings / errors)**
6. **Generates a report**
7. **Exits cleanly (ready for CI integration)**

Even with placeholder logic, the **full lifecycle is implemented and wired correctly**.

## Architecture overview

The engine is split into **explicit, decoupled modules**, each with a single responsibility.

```
engine-model     → Immutable domain structures
engine-context   → Shared execution state
engine-io        → Input scanning (filesystem / classpath)
engine-analysis  → Static analysis rules
engine-pipeline  → Analysis orchestration
engine-report    → Output formatting
engine-cli       → Command-line interface
```

This separation is **intentional and senior‑level** — no cyclic dependencies, no god modules.

## Module-by-module: what exists today

### 🔹 engine-model

**Purpose:**
Pure domain layer.

**Status:**

* Compiles
* Contains immutable model types
* No business logic

**Why it matters:**
This ensures analyses and reports are **data-driven**, not tightly coupled to IO or execution flow.

### 🔹 engine-context

**Purpose:**
Shared runtime context across the engine.

**Key components:**

* `AnalysisContext`
* `DiagnosticsCollector`

**What it already does:**

* Centralizes diagnostics collection
* Allows all engine layers to emit info / warnings / errors
* Tracks whether execution should fail

This is the **control plane** of the engine.

### 🔹 engine-io

**Purpose:**
Input discovery and classpath scanning.

**Current implementation:**

* `ClasspathScanner`

  * Scans directories recursively
  * Detects `.class` files
  * Emits diagnostics for:

    * Missing paths
    * Unsupported inputs
    * Future JAR handling

**What it does NOT do yet (by design):**

* Bytecode parsing
* JAR scanning
* Class indexing

Those are planned, not missing.

### 🔹 engine-analysis

**Purpose:**
Static analysis rules.

**Current state:**

* Analysis abstractions exist
* Example analyses are wired into the pipeline
* Analyses can:

  * Run
  * Skip execution safely
  * Emit diagnostics

**Important:**
Even when an analysis does nothing meaningful yet, **the execution contract is already correct**.

This is critical for CI safety.

### 🔹 engine-pipeline

**Purpose:**
Deterministic orchestration of analyses.

**What it already guarantees:**

* Analyses run in order
* Pipeline stops on failure
* Diagnostics determine the final result

This layer is what makes the engine **automation‑ready**.

### 🔹 engine-report

**Purpose:**
Convert diagnostics and results into output formats.

**Current support:**

* Text report

**Design intent:**

* Reports are decoupled from analyses
* Multiple formats can be added (JSON, SARIF, etc.)

### 🔹 engine-cli

**Purpose:**
User-facing entrypoint.

**What works today:**

* Argument parsing
* Engine bootstrap
* Wiring IO → pipeline → report

**Example usage:**

```bash
java -cp out engine.cli.Main --input . --report text
```

This already behaves like a real CLI tool.

## What happens when you run it today

When executed:

1. The CLI initializes the engine
2. The filesystem is scanned for `.class` files
3. Each discovered file is logged as a diagnostic
4. Analyses execute (even if minimal)
5. A report is generated
6. The process exits cleanly

➡️ **This proves the engine lifecycle is complete.**

## What is intentionally incomplete

These gaps are **planned expansion points**, not flaws:

* No bytecode parsing (ASM not integrated yet)
* No class index yet
* No dependency graph yet
* No call graph yet
* Some packages exist only as placeholders

Senior systems **reserve space before filling it**.

## Current maturity level

**Engineering maturity:**

* Modular
* Deterministic
* CI-oriented
* No hidden coupling

**Analysis depth:**

 Early (by design)

**Overall level:**

> **Strong mid → senior architecture foundation**

What’s missing is **depth of rules**, not structure.

## Next planned milestone

The next concrete upgrade:

> **First real enforcement rule**

Example:

* Detect new public classes
* Fail the build when public surface grows

That single rule will immediately elevate this project to **clear senior-level signal**.

## Final note

This repository already demonstrates:

* Systems thinking
* CI awareness
* Long-term extensibility
* Clean separation of concerns

What comes next is **not refactoring** — it’s **adding power**.

---

**Status:** Stable foundation. Ready for rule implementation.
