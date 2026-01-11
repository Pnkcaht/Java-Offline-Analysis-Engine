package engine.pipeline;

import engine.context.AnalysisContext;
import engine.report.Report;
import engine.report.ReportSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Final pipeline stage that builds the analysis report.
 */
public final class ReportStage implements Stage {

    @Override
    public StageResult execute(AnalysisContext context) {
        try {
            List<ReportSection> sections = new ArrayList<>();

            // === Call Graph Metrics ===
            context.getResult(engine.analysis.callgraph.CallGraphMetrics.class)
                    .ifPresent(metrics -> sections.add(
                            new ReportSection(
                                    "Call Graph Metrics",
                                    Map.of(
                                            "nodes", metrics.nodeCount(),
                                            "edges", metrics.edgeCount(),
                                            "fanOutMethods", metrics.fanOut().size()
                                    )
                            )
                    ));

            // === Dependency Graph ===
            if (context.dependencyGraph() != null) {
                sections.add(
                        new ReportSection(
                                "Dependency Graph",
                                Map.of(
                                        "nodes",
                                        context.dependencyGraph().getNodes().size(),
                                        "edges",
                                        context.dependencyGraph().getEdges().size()
                                )
                        )
                );
            }

            // === store final report ===
            context.setReport(new Report(sections));

            return StageResult.success();

        } catch (Exception e) {
            return StageResult.failure(
                    "ReportStage failed: " + e.getMessage()
            );
        }
    }
}
