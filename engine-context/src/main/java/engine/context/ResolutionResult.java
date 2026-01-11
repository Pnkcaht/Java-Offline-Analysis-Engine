package engine.context;

import java.util.Objects;
import java.util.Optional;

/**
 * Result of symbol resolution.
 */
public final class ResolutionResult<T> {

    private final T element;
    private final String failureReason;

    private ResolutionResult(T element, String failureReason) {
        this.element = element;
        this.failureReason = failureReason;
    }

    public static <T> ResolutionResult<T> resolved(T element) {
        return new ResolutionResult<>(
                Objects.requireNonNull(element),
                null
        );
    }

    public static <T> ResolutionResult<T> unresolved(String reason) {
        return new ResolutionResult<>(null, reason);
    }

    public boolean isResolved() {
        return element != null;
    }

    public Optional<T> element() {
        return Optional.ofNullable(element);
    }

    public Optional<String> failureReason() {
        return Optional.ofNullable(failureReason);
    }

    @Override
    public String toString() {
        return isResolved()
                ? "Resolved[" + element + "]"
                : "Unresolved[" + failureReason + "]";
    }
}
