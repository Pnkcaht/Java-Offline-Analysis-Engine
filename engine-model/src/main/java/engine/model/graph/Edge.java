package engine.model.graph;

import java.util.Objects;

/**
 * Immutable, typed, directed edge between two nodes.
 *
 * <p>Edges represent structural relationships only.
 * They do not imply execution order or runtime behavior.</p>
 */
public final class Edge<N> {

    private final N source;
    private final N target;
    private final EdgeType type;

    public Edge(N source, N target, EdgeType type) {
        this.source = Objects.requireNonNull(source, "source");
        this.target = Objects.requireNonNull(target, "target");
        this.type = Objects.requireNonNull(type, "type");
    }

    public N getSource() {
        return source;
    }

    public N getTarget() {
        return target;
    }

    public EdgeType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge<?> edge)) return false;
        return source.equals(edge.source)
                && target.equals(edge.target)
                && type == edge.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, type);
    }

    @Override
    public String toString() {
        return source + " -" + type + "-> " + target;
    }

    /**
     * Canonical edge classification.
     */
    public enum EdgeType {
        CALL,
        EXTENDS,
        IMPLEMENTS,
        USES,
        REFERENCES
    }
}
