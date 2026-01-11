package engine.context;

import engine.model.graph.CallGraph;
import engine.model.graph.DependencyGraph;
import engine.model.metadata.MetadataContainer;

import java.util.Objects;

/**
 * Mutable execution context shared across pipeline stages.
 *
 * <p>All mutations are explicit and stage-bound.</p>
 */
public final class AnalysisContext {

    private final ClassIndex classIndex;
    private final SymbolTable symbolTable;
    private final DiagnosticsCollector diagnostics;

    private CallGraph callGraph;
    private DependencyGraph dependencyGraph;

    public AnalysisContext(
            ClassIndex classIndex,
            SymbolTable symbolTable,
            DiagnosticsCollector diagnostics
    ) {
        this.classIndex = Objects.requireNonNull(classIndex);
        this.symbolTable = Objects.requireNonNull(symbolTable);
        this.diagnostics = Objects.requireNonNull(diagnostics);
    }

    public ClassIndex classIndex() {
        return classIndex;
    }

    public SymbolTable symbolTable() {
        return symbolTable;
    }

    public DiagnosticsCollector diagnostics() {
        return diagnostics;
    }

    public void setCallGraph(CallGraph callGraph) {
        this.callGraph = callGraph;
    }

    public void setDependencyGraph(DependencyGraph dependencyGraph) {
        this.dependencyGraph = dependencyGraph;
    }

    public CallGraph callGraph() {
        return callGraph;
    }

    public DependencyGraph dependencyGraph() {
        return dependencyGraph;
    }
}
