package engine.bytecode.resolver;

import engine.context.ClassIndex;
import engine.model.element.ClassElement;
import engine.model.graph.DependencyGraph;
import engine.model.graph.Edge;

import java.util.HashSet;
import java.util.Set;

/**
 * Resolves structural dependencies between classes.
 */
public final class DependencyResolver {

    public DependencyGraph resolve(ClassIndex index) {
        Set<ClassElement> nodes = new HashSet<>();
        Set<Edge<ClassElement>> edges = new HashSet<>();

        for (ClassElement clazz : index.all()) {
            nodes.add(clazz);

            clazz.superClassInternalName()
                    .ifPresent(superName ->
                            index.find(superName).ifPresent(superClass ->
                                    edges.add(new Edge<>(
                                            clazz,
                                            superClass,
                                            Edge.EdgeType.EXTENDS
                                    ))
                            )
                    );
        }

        return new DependencyGraph(nodes, edges);
    }
}
