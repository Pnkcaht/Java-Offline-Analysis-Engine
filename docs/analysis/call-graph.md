# Call Graph Analysis

## Purpose

The Call Graph analysis identifies **method-to-method invocation relationships** within the analyzed bytecode set.

It answers the question:

> *Which methods may call which other methods?*

This analysis forms the foundation for higher-level analyses such as reachability, dead code detection, and impact analysis.

---

## Scope

The call graph operates on:

* Methods defined in analyzed classes
* Invocation instructions present in bytecode
* Resolved symbolic references where possible

The analysis is **intra- and inter-class**, but strictly limited to the provided analysis scope.

---

## Definitions

* **Node**: A method (identified by owner, name, and descriptor)
* **Edge**: A directed invocation from caller to callee

Edges represent *potential* calls, not guaranteed runtime execution.

---

## Resolution Model

The call graph distinguishes between:

* **Resolved calls**: Target method is known and present in the model
* **Unresolved calls**: Target cannot be resolved (external or missing bytecode)

Unresolved calls are explicitly represented and reported as diagnostics.

---

## Limitations

The analysis:

* Does not execute code
* Does not perform dynamic dispatch resolution beyond bytecode semantics
* Does not infer runtime types beyond static information

Precision is bounded by static bytecode visibility.

---

## Determinism Guarantees

Given the same input artifacts, the call graph:

* Produces identical nodes and edges
* Is independent of execution order
* Is unaffected by analysis parallelism
