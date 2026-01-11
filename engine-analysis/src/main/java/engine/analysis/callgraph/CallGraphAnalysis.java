package engine.analysis.callgraph;

import engine.analysis.Analysis;
import engine.context.AnalysisContext;
import engine.model.graph.CallGraph;

/**
 * Performs analysis on the call graph.
 */
public final class CallGraphAnalysis implements Analysis {

    @Override
    public void run(AnalysisContext context) {
        CallGraph graph = context.callGraph();
        if (graph == null) {
            context.diagnostics()
                    .info("CallGraphAnalysis skipped: no call graph");
            return;
        }

        CallGraphMetrics metrics = CallGraphMetrics.compute(graph);
        context.putResult(CallGraphMetrics.class, metrics);
    }
}
