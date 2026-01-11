package engine.analysis.callgraph;

import engine.model.graph.CallGraph;
import engine.model.graph.Edge;
import engine.model.element.MethodElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Metrics derived from a call graph.
 */
public final class CallGraphMetrics {

    private final int nodeCount;
    private final int edgeCount;
    private final Map<MethodElement, Integer> fanOut;

    private CallGraphMetrics(
            int nodeCount,
            int edgeCount,
            Map<MethodElement, Integer> fanOut
    ) {
        this.nodeCount = nodeCount;
        this.edgeCount = edgeCount;
        this.fanOut = Map.copyOf(fanOut);
    }

    public static CallGraphMetrics compute(CallGraph graph) {
        Map<MethodElement, Integer> fanOut = new HashMap<>();

        for (Edge<MethodElement> edge : graph.getEdges()) {
            fanOut.merge(edge.getSource(), 1, Integer::sum);
        }

        return new CallGraphMetrics(
                graph.getNodes().size(),
                graph.getEdges().size(),
                fanOut
        );
    }

    public int nodeCount() {
        return nodeCount;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public Map<MethodElement, Integer> fanOut() {
        return fanOut;
    }
}
