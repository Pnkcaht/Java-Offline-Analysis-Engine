package engine.testkit.assertions;

import engine.model.graph.DependencyGraph;

public final class DependencyGraphAssertions {

    public static void assertNotEmpty(
            DependencyGraph graph
    ) {
        if (graph.getNodes().isEmpty()) {
            throw new AssertionError(
                    "Dependency graph is empty"
            );
        }
    }
}
