package engine.bytecode.resolver;

import engine.context.SymbolTable;
import engine.model.element.MethodElement;
import engine.model.graph.CallGraph;
import engine.model.graph.Edge;

import java.util.HashSet;
import java.util.Set;

/**
 * Builds a call graph from resolved method symbols.
 */
public final class CallGraphResolver {

    public CallGraph resolve(SymbolTable symbolTable) {
        Set<MethodElement> nodes = new HashSet<>();
        Set<Edge<MethodElement>> edges = new HashSet<>();

        symbolTable
                .entries()
                .forEach((key, result) -> {
                    if (result.isResolved()
                            && result.element().get() instanceof MethodElement method) {

                        nodes.add(method);
                        // edges são criadas via mapping (ver pacote mapping)
                    }
                });

        return new CallGraph(nodes, edges);
    }
}
