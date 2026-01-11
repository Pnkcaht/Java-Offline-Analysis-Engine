package engine.model.graph;

import engine.model.element.MethodElement;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Immutable directed graph representing possible method invocations.
 *
 * <p>Edges indicate that a call MAY occur at runtime.
 * No ordering or frequency is implied.</p>
 */
public final class CallGraph {

    private final Set<MethodElement> nodes;
    private final Set<Edge<MethodElement>> edges;

    public CallGraph(
            Set<MethodElement> nodes,
            Set<Edge<MethodElement>> edges
    ) {
        this.nodes = immutableSet(nodes);
        this.edges = immutableSet(edges);
    }

    public Set<MethodElement> getNodes() {
        return nodes;
    }

    public Set<Edge<MethodElement>> getEdges() {
        return edges;
    }

    public Set<Edge<MethodElement>> outgoingEdges(MethodElement method) {
        Objects.requireNonNull(method, "method");
        return edges.stream()
                .filter(e -> e.getSource().equals(method))
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<Edge<MethodElement>> incomingEdges(MethodElement method) {
        Objects.requireNonNull(method, "method");
        return edges.stream()
                .filter(e -> e.getTarget().equals(method))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public String toString() {
        return "CallGraph[nodes=" + nodes.size() + ", edges=" + edges.size() + "]";
    }

    private static <T> Set<T> immutableSet(Set<T> input) {
        Objects.requireNonNull(input, "set");
        return Collections.unmodifiableSet(Set.copyOf(input));
    }
}
