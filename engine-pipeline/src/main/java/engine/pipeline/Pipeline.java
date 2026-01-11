package engine.pipeline;

import engine.context.AnalysisContext;
import engine.report.Report;

/**
 * Pipeline executor.
 */
public final class Pipeline {

    private final ExecutionPlan plan;

    public Pipeline(ExecutionPlan plan) {
        this.plan = plan;
    }

    public Report execute(AnalysisContext context) {
        for (Stage stage : plan.stages()) {
            StageResult result = stage.execute(context);

            if (result.isFailure()) {
                context.diagnostics()
                        .error("Pipeline stopped: " + result);
                break;
            }
        }

        Report report = context.report();
        if (report == null) {
            throw new IllegalStateException(
                    "Pipeline completed without producing a Report"
            );
        }

        return report;
    }
}
