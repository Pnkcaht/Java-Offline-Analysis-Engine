package engine.testkit.assertions;

import engine.model.graph.CallGraph;

public final class CallGraphAssertions {

    public static void assertNodeCount(
            CallGraph graph,
            int expected
    ) {
        requireGraph(graph);

        int actual = graph.getNodes().size();
        if (actual != expected) {
            throw new AssertionError(
                    "CallGraph node count mismatch: expected "
                            + expected + " but got " + actual
            );
        }
    }

    public static void assertEdgeCount(
            CallGraph graph,
            int expected
    ) {
        requireGraph(graph);

        int actual = graph.getEdges().size();
        if (actual != expected) {
            throw new AssertionError(
                    "CallGraph edge count mismatch: expected "
                            + expected + " but got " + actual
            );
        }
    }

    private static void requireGraph(CallGraph graph) {
        if (graph == null) {
            throw new AssertionError("CallGraph is null");
        }
    }
}
