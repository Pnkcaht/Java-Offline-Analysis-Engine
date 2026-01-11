package engine.cli;

import engine.analysis.callgraph.CallGraphAnalysis;
import engine.analysis.dependency.DependencyAnalysis;
import engine.analysis.surface.SurfaceAnalysis;
import engine.context.AnalysisContext;
import engine.context.ClassIndex;
import engine.context.DiagnosticsCollector;
import engine.context.SymbolTable;
import engine.io.classpath.ClasspathScanner;
import engine.pipeline.*;
import engine.report.*;

import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Wires the engine together and executes analysis.
 */
public final class EngineLauncher {

    private final CommandLineOptions options;

    public EngineLauncher(CommandLineOptions options) {
        this.options = options;
    }

    public void run() throws Exception {
        DiagnosticsCollector diagnostics =
                new DiagnosticsCollector();

        AnalysisContext context =
                new AnalysisContext(
                        new ClassIndex(),
                        new SymbolTable(),
                        diagnostics
                );

        scanClasspath(options.inputPath(), context);

        Pipeline pipeline = buildPipeline();

        Report report = pipeline.execute(context);

        writeReport(report);

        if (options.failOnDiagnostics()
                && diagnostics.hasErrors()) {
            throw new IllegalStateException(
                    "Analysis completed with errors"
            );
        }
    }

    private void scanClasspath(
            Path input,
            AnalysisContext context
    ) throws Exception {

        ClasspathScanner scanner =
                new ClasspathScanner(context);

        scanner.scan(input);
    }

    private Pipeline buildPipeline() {
        return new Pipeline(
                new ExecutionPlan(List.of(
                        new AnalysisStage(
                                "Call Graph Analysis",
                                new CallGraphAnalysis()
                        ),
                        new AnalysisStage(
                                "Dependency Analysis",
                                new DependencyAnalysis()
                        ),
                        new AnalysisStage(
                                "Surface Analysis",
                                new SurfaceAnalysis()
                        ),
                        new ReportStage()
                ))
        );
    }

    private void writeReport(Report report) throws Exception {
        ReportWriter writer =
                switch (options.reportFormat()) {
                    case TEXT -> new TextReportWriter();
                    case JSON -> new JsonReportWriter();
                };

        try (OutputStream out = System.out) {
            writer.write(report, out);
        }
    }
}
