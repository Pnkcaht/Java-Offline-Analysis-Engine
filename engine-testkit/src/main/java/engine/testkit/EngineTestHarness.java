package engine.testkit;

import engine.analysis.callgraph.CallGraphAnalysis;
import engine.context.*;
import engine.pipeline.*;
import engine.report.Report;
import engine.testkit.bytecode.BytecodeLoader;
import engine.testkit.bytecode.TestClasspath;

import java.util.List;

/**
 * Executes the analysis pipeline against a test classpath.
 */
public final class EngineTestHarness {

    public static Report run(TestClasspath classpath) throws Exception {

        DiagnosticsCollector diagnostics =
                new DiagnosticsCollector();

        AnalysisContext context =
                new AnalysisContext(
                        new ClassIndex(),
                        new SymbolTable(),
                        diagnostics
                );

        BytecodeLoader.load(classpath, context);

        Pipeline pipeline =
                new Pipeline(
                        new ExecutionPlan(List.of(
                                new AnalysisStage(
                                        "Call Graph",
                                        new CallGraphAnalysis()
                                ),
                                new ReportStage()
                        ))
                );

        Report report = pipeline.execute(context);

        if (diagnostics.hasErrors()) {
            throw new IllegalStateException(
                    "Engine produced diagnostics errors during test execution"
            );
        }

        return report;
    }
}
