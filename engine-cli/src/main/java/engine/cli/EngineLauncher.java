package engine.cli;

import engine.context.AnalysisContext;
import engine.context.DiagnosticsCollector;
import engine.io.classpath.ClasspathScanner;
import engine.pipeline.ExecutionPlan;
import engine.pipeline.Pipeline;
import engine.pipeline.PipelineBuilder;
import engine.report.*;

import java.io.OutputStream;
import java.nio.file.Path;

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
                new AnalysisContext(diagnostics);

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
        ExecutionPlan plan = new ExecutionPlan();

        return PipelineBuilder.create()
                .withPlan(plan)
                .build();
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
