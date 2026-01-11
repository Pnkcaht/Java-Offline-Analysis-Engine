package engine.analysis.dependency;

import engine.analysis.Analysis;
import engine.context.AnalysisContext;
import engine.model.graph.DependencyGraph;

/**
 * Analyzes structural dependencies between classes.
 */
public final class DependencyAnalysis implements Analysis {

    @Override
    public void run(AnalysisContext context) {
        DependencyGraph graph = context.dependencyGraph();
        if (graph == null) {
            context.diagnostics()
                    .report("DependencyAnalysis skipped: no dependency graph");
            return;
        }

        DependencyMetrics.compute(graph);
    }
}
