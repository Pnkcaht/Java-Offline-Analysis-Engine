package engine.pipeline;

import engine.context.AnalysisContext;

/**
 * Pipeline executor.
 */
public final class Pipeline {

    private final ExecutionPlan plan;

    public Pipeline(ExecutionPlan plan) {
        this.plan = plan;
    }

    public void run(AnalysisContext context) {
        for (Stage stage : plan.stages()) {
            StageResult result = stage.execute(context);

            if (result.isFailure()) {
                context.diagnostics()
                        .report("Pipeline stopped: " + result);
                break;
            }
        }
    }
}
