# Surface Mapping Analysis

## Purpose

Surface Mapping identifies the **externally visible surface area** of the analyzed codebase.

It answers the question:

> *What parts of the system are exposed to external consumers?*

---

## Scope

The surface includes:

* Public and protected classes
* Public and protected methods
* Public fields
* Exported modules and packages

Visibility is determined strictly by bytecode-level access flags.

---

## Definitions

* **Surface Element**: Any program element accessible outside its defining boundary
* **Boundary**: Package or module limit depending on context

---

## Analysis Rules

* Only declared visibility is considered
* No runtime access checks are inferred
* Synthetic and compiler-generated members may be filtered

---

## Use Cases

Surface mapping supports:

* API stability analysis
* Attack surface estimation
* Encapsulation and layering checks

---

## Guarantees

The surface mapping:

* Is deterministic
* Reflects declared visibility only
* Is independent of call graph or dependency analysis
