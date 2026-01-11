# Dependency Graph Analysis

## Purpose

The Dependency Graph analysis identifies **structural dependencies** between program elements such as classes, packages, and modules.

It answers the question:

> *What depends on what, and in which direction?*

---

## Scope

Dependencies may arise from:

* Field types
* Method parameter and return types
* Inheritance and interface implementation
* Constant pool references
* Annotations and metadata

The analysis is structural, not behavioral.

---

## Definitions

* **Node**: A program element (class, package, or module)
* **Edge**: A directed dependency from consumer to provider

Dependencies are always directional.

---

## Dependency Categories

Dependencies are classified by kind:

* Type dependency
* Inheritance dependency
* Annotation dependency
* Constant dependency

Each category is explicitly labeled in the model.

---

## External Dependencies

Dependencies targeting elements outside the analysis scope are:

* Explicitly represented
* Marked as external
* Included in diagnostics and reports

---

## Guarantees

The dependency graph:

* Is deterministic
* Is complete with respect to visible bytecode
* Does not infer transitive closure unless explicitly requested
