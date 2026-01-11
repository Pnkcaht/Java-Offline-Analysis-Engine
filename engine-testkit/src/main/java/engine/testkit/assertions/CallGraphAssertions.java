package engine.testkit.assertions;

import engine.model.graph.CallGraph;

public final class CallGraphAssertions {

    public static void assertNodeCount(
            CallGraph graph,
            int expected
    ) {
        if (graph.getNodes().size() != expected) {
            throw new AssertionError(
                    "Expected " + expected +
                            " nodes but got " +
                            graph.getNodes().size()
            );
        }
    }

    public static void assertEdgeCount(
            CallGraph graph,
            int expected
    ) {
        if (graph.getEdges().size() != expected) {
            throw new AssertionError(
                    "Expected " + expected +
                            " edges but got " +
                            graph.getEdges().size()
            );
        }
    }
}
