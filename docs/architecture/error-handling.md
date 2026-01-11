# Error Handling

## Error Taxonomy

The engine distinguishes between:

* **Errors**: Fatal conditions preventing meaningful analysis
* **Diagnostics**: Recoverable issues and informational messages

## Propagation Rules

* Errors are explicit and typed
* No silent failures
* Exceptions are not used for control flow

## Checked vs Unchecked

* Domain and pipeline errors are modeled explicitly
* Programming errors may throw unchecked exceptions

## Fail-Fast vs Best-Effort

* Structural corruption triggers fail-fast
* Missing symbols may be handled best-effort
* Abort thresholds are configurable

## Diagnostics Collection

Diagnostics are centrally collected, immutable, and location-aware.
