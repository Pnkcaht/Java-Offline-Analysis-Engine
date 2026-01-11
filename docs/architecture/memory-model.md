# Memory Model

## Immutability

All domain entities are immutable by design.

Once created, objects:

* Never change state
* Are thread-safe
* May be cached or shared freely

## Object Lifetime

* Bytecode readers are short-lived
* Model objects are long-lived
* Analysis results live for the pipeline duration
* Reports are transient

## Ownership Rules

* Creators own initialization
* Consumers receive read-only access
* No shared ownership assumptions

## Trade-offs

The engine trades increased object creation for predictability, safety, and simplicity.
