package engine.pipeline;

import java.util.List;
import java.util.Objects;

/**
 * Immutable ordered execution plan.
 */
public final class ExecutionPlan {

    private final List<Stage> stages;

    public ExecutionPlan(List<Stage> stages) {
        this.stages = List.copyOf(
                Objects.requireNonNull(stages)
        );
    }

    public List<Stage> stages() {
        return stages;
    }

    @Override
    public String toString() {
        return "ExecutionPlan[stages=" + stages.size() + "]";
    }
}
