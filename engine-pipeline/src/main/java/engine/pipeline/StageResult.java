package engine.pipeline;

import java.util.Objects;
import java.util.Optional;

/**
 * Result of a pipeline stage execution.
 */
public final class StageResult {

    public enum Status {
        SUCCESS,
        FAILURE,
        SKIPPED
    }

    private final Status status;
    private final String message;

    private StageResult(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public static StageResult success() {
        return new StageResult(Status.SUCCESS, null);
    }

    public static StageResult skipped(String reason) {
        return new StageResult(Status.SKIPPED, reason);
    }

    public static StageResult failure(String reason) {
        Objects.requireNonNull(reason);
        return new StageResult(Status.FAILURE, reason);
    }

    public Status status() {
        return status;
    }

    public Optional<String> message() {
        return Optional.ofNullable(message);
    }

    public boolean isFailure() {
        return status == Status.FAILURE;
    }

    @Override
    public String toString() {
        return status + (message != null ? ": " + message : "");
    }
}
