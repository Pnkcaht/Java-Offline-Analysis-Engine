package engine.pipeline;

import engine.context.AnalysisContext;

/**
 * Single pipeline stage.
 *
 * <p>Stages must be deterministic and side-effect
 * only the provided AnalysisContext.</p>
 */
@FunctionalInterface
public interface Stage {

    StageResult execute(AnalysisContext context);
}
