package engine.context;

import engine.model.graph.CallGraph;
import engine.model.graph.DependencyGraph;
import engine.report.Report;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Mutable execution context shared across pipeline stages.
 *
 * <p>Holds analysis state, intermediate results,
 * and the final Report produced by the pipeline.</p>
 */
public final class AnalysisContext {

    private final ClassIndex classIndex;
    private final SymbolTable symbolTable;
    private final DiagnosticsCollector diagnostics;

    // === core graphs ===
    private CallGraph callGraph;
    private DependencyGraph dependencyGraph;

    // === final output ===
    private Report report;

    // === analysis results (metrics, surfaces, etc.) ===
    private final Map<Class<?>, Object> results = new HashMap<>();

    public AnalysisContext(
            ClassIndex classIndex,
            SymbolTable symbolTable,
            DiagnosticsCollector diagnostics
    ) {
        this.classIndex = Objects.requireNonNull(classIndex, "classIndex");
        this.symbolTable = Objects.requireNonNull(symbolTable, "symbolTable");
        this.diagnostics = Objects.requireNonNull(diagnostics, "diagnostics");
    }

    // === core services ===

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
        this.callGraph = Objects.requireNonNull(callGraph, "callGraph");
    }

    public CallGraph callGraph() {
        return callGraph;
    }

    public void setDependencyGraph(DependencyGraph dependencyGraph) {
        this.dependencyGraph = Objects.requireNonNull(dependencyGraph, "dependencyGraph");
    }

    public DependencyGraph dependencyGraph() {
        return dependencyGraph;
    }

    // === report ===

    public Report report() {
        return report;
    }

    public void setReport(Report report) {
        this.report = Objects.requireNonNull(report, "report");
    }

    // === analysis results ===

    public <T> void putResult(Class<T> key, T value) {
        results.put(
                Objects.requireNonNull(key, "key"),
                Objects.requireNonNull(value, "value")
        );
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getResult(Class<T> key) {
        return Optional.ofNullable((T) results.get(key));
    }
}
