package engine.analysis;

import engine.context.AnalysisContext;

/**
 * Base contract for all analyses.
 */
@FunctionalInterface
public interface Analysis {

    void run(AnalysisContext context);
}
