# Contributing to Java Offline Analysis Engine

First of all, thank you for your interest in contributing.

This project is intentionally designed as a **low-level, deterministic static analysis engine**.  
Contributions are welcome, but **architecture and consistency matter more than speed**.

Please read this document carefully before opening a pull request.

## Project philosophy

This engine follows a few strict principles:

- **Offline only**
  - No HTTP
  - No APIs
  - No databases
  - No cloud dependencies

- **Deterministic execution**
  - Same input → same output
  - No non-deterministic behavior
  - CI-safe by design

- **Explicit architecture**
  - Clear module boundaries
  - No hidden coupling
  - No “magic” frameworks

- **Engineering-first**
  - Correctness > features
  - Structure > shortcuts
  - Long-term maintainability > quick wins

If a contribution violates any of these principles, it will not be accepted.

## Repository structure

Each module has a **single responsibility**:

engine-model → Immutable domain objects
engine-context → Shared execution state and diagnostics
engine-io → Filesystem / classpath scanning
engine-analysis → Static analysis rules
engine-pipeline → Deterministic orchestration
engine-report → Output formatting
engine-cli → Command-line interface


Do **not** introduce cross-module dependencies unless absolutely necessary.

---

## What kind of contributions are welcome

### ✔ Good contributions

- New **analysis rules**
- Improvements to **diagnostics quality**
- Enhancements to **report formats**
- Better **classpath scanning**
- Performance improvements with measurable impact
- Tests (when applicable)
- Documentation improvements that reflect reality

### ✖ Not accepted

- Adding frameworks (Spring, Guice, Lombok, etc.)
- Introducing runtime configuration systems
- Mixing IO logic into analysis logic
- Making analyses depend on CLI or report code
- Adding features without a clear architectural fit
- Cosmetic refactors with no technical justification

---

## Coding standards

### Language level

- Java **21**
- No preview features unless explicitly agreed

### Style guidelines

- Prefer **immutability**
- Prefer **composition over inheritance**
- Keep methods small and explicit
- Avoid hidden side effects
- Use clear, descriptive names (no abbreviations)

### Error handling

- Do **not** throw unchecked exceptions for analysis results
- Use `DiagnosticsCollector` to report:
  - info
  - warnings
  - errors
- Let the pipeline decide whether execution should fail

---

## Diagnostics rules

All user-visible feedback must go through:

```java
DiagnosticsCollector
```

Do not:

- Print directly to stdout/stderr
- Swallow errors silently
- Terminate the JVM manually
- The engine must remain CI-controllable.

## Adding a new analysis

When adding a new analysis:

1) Implement it under engine-analysis

2) Ensure it:

  - Can run safely with partial context
  
  - Can skip execution gracefully

3) Emit diagnostics instead of failing hard

4) Wire it explicitly into the pipeline

5) Document what it does and what it does not do

Analyses should be idempotent and deterministic.

## Placeholder modules and empty packages

Some packages currently exist as intentional placeholders.

Do not remove them unless:

- The responsibility is clearly obsolete

- A better structure replaces it

Empty packages are allowed when they communicate planned architectural intent.

## Build and run

The project is built using plain javac, no build tools.

Example:

```java
rm -rf out && mkdir out

javac -d out $(find engine-model/src/main/java -name "*.java")
javac -cp out -d out $(find engine-report/src/main/java -name "*.java")
javac -cp out -d out $(find engine-context/src/main/java -name "*.java")
javac -cp out -d out $(find engine-analysis/src/main/java -name "*.java")
javac -cp out -d out $(find engine-pipeline/src/main/java -name "*.java")
javac -cp out -d out $(find engine-io/src/main/java -name "*.java")
javac -cp out -d out $(find engine-cli/src/main/java -name "*.java")
```

Run:

```java
java -cp out engine.cli.Main --input . --report text
```

If you break this flow, the contribution will be rejected.

## Commit guidelines

- Small, focused commits

- Clear commit messages

- One logical change per commit

- No drive-by formatting changes

Examples:
Add basic class indexing stub
Improve diagnostics for missing input paths
Introduce surface growth analysis skeleton

## Pull request expectations

A pull request should include:

- Clear description of the change

- Motivation and reasoning

- What problem it solves

- What it intentionally does NOT solve

Large changes without discussion may be closed.

## Final note

This project is meant to look and behave like real internal tooling, not a demo.

If your contribution improves:

- determinism

- clarity

- correctness

- architectural consistency

It will be welcome.

If it adds noise or shortcuts, it will not.

Thanks for contributing.
