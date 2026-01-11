package engine.analysis.dependency;

import engine.model.element.ClassElement;
import engine.model.graph.DependencyGraph;
import engine.model.graph.Edge;

import java.util.HashMap;
import java.util.Map;

/**
 * Metrics derived from dependency graph.
 */
public final class DependencyMetrics {

    private final int nodeCount;
    private final int edgeCount;
    private final Map<ClassElement, Integer> outgoingDeps;

    private DependencyMetrics(
            int nodeCount,
            int edgeCount,
            Map<ClassElement, Integer> outgoingDeps
    ) {
        this.nodeCount = nodeCount;
        this.edgeCount = edgeCount;
        this.outgoingDeps = Map.copyOf(outgoingDeps);
    }

    public static DependencyMetrics compute(DependencyGraph graph) {
        Map<ClassElement, Integer> deps = new HashMap<>();

        for (Edge<ClassElement> edge : graph.getEdges()) {
            deps.merge(edge.getSource(), 1, Integer::sum);
        }

        return new DependencyMetrics(
                graph.getNodes().size(),
                graph.getEdges().size(),
                deps
        );
    }

    public int nodeCount() {
        return nodeCount;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public Map<ClassElement, Integer> outgoingDeps() {
        return outgoingDeps;
    }
}
