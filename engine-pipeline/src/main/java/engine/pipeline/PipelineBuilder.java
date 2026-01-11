package engine.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Fluent builder for execution pipelines.
 */
public final class PipelineBuilder {

    private final List<Stage> stages = new ArrayList<>();

    public PipelineBuilder add(Stage stage) {
        stages.add(Objects.requireNonNull(stage));
        return this;
    }

    public ExecutionPlan build() {
        if (stages.isEmpty()) {
            throw new IllegalStateException("Pipeline must have at least one stage");
        }
        return new ExecutionPlan(stages);
    }
}
