package engine.pipeline;

import engine.analysis.Analysis;
import engine.context.AnalysisContext;

import java.util.Objects;

/**
 * Pipeline stage that wraps an Analysis.
 */
public final class AnalysisStage implements Stage {

    private final Analysis analysis;
    private final String name;

    public AnalysisStage(String name, Analysis analysis) {
        this.name = Objects.requireNonNull(name);
        this.analysis = Objects.requireNonNull(analysis);
    }

    @Override
    public StageResult execute(AnalysisContext context) {
        try {
            analysis.run(context);
            return StageResult.success();
        } catch (Exception e) {
            return StageResult.failure(
                    "Analysis failed [" + name + "]: " + e.getMessage()
            );
        }
    }

    @Override
    public String toString() {
        return "AnalysisStage[" + name + "]";
    }
}
