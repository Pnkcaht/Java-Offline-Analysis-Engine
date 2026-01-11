package engine.context;

import engine.model.graph.CallGraph;
import engine.model.graph.DependencyGraph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    // === analysis results ===
    private final Map<Class<?>, Object> results = new HashMap<>();

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

    // === graphs ===

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

    // === analysis results ===

    public <T> void putResult(Class<T> key, T value) {
        results.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getResult(Class<T> key) {
        return Optional.ofNullable((T) results.get(key));
    }
}
