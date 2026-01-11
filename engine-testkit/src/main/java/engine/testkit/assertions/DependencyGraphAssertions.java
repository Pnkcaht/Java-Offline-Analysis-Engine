package engine.testkit.assertions;

import engine.model.graph.DependencyGraph;

public final class DependencyGraphAssertions {

    public static void assertNotEmpty(
            DependencyGraph graph
    ) {
        if (graph == null) {
            throw new AssertionError(
                    "DependencyGraph is null"
            );
        }

        if (graph.getNodes().isEmpty()) {
            throw new AssertionError(
                    "DependencyGraph has no nodes"
            );
        }
    }
}
