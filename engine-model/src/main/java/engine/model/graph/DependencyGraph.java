package engine.model.graph;

import engine.model.element.ClassElement;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Immutable graph describing structural dependencies between classes.
 *
 * <p>Dependencies are purely declarative and derived from bytecode structure.</p>
 */
public final class DependencyGraph {

    private final Set<ClassElement> nodes;
    private final Set<Edge<ClassElement>> edges;

    public DependencyGraph(
            Set<ClassElement> nodes,
            Set<Edge<ClassElement>> edges
    ) {
        this.nodes = immutableSet(nodes);
        this.edges = immutableSet(edges);
    }

    public Set<ClassElement> getNodes() {
        return nodes;
    }

    public Set<Edge<ClassElement>> getEdges() {
        return edges;
    }

    public Set<Edge<ClassElement>> outgoingEdges(ClassElement clazz) {
        Objects.requireNonNull(clazz, "clazz");
        return edges.stream()
                .filter(e -> e.getSource().equals(clazz))
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<Edge<ClassElement>> incomingEdges(ClassElement clazz) {
        Objects.requireNonNull(clazz, "clazz");
        return edges.stream()
                .filter(e -> e.getTarget().equals(clazz))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public String toString() {
        return "DependencyGraph[nodes=" + nodes.size() + ", edges=" + edges.size() + "]";
    }

    private static <T> Set<T> immutableSet(Set<T> input) {
        Objects.requireNonNull(input, "set");
        return Collections.unmodifiableSet(Set.copyOf(input));
    }
}
