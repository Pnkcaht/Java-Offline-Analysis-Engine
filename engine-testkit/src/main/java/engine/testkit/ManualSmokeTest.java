package engine.testkit;

import engine.model.graph.CallGraph;
import engine.report.Report;
import engine.testkit.assertions.CallGraphAssertions;
import engine.testkit.assertions.DiagnosticsAssertions;
import engine.testkit.bytecode.TestClasspath;
import engine.testkit.fixtures.TestProjects;
import engine.testkit.fixtures.TestProject;

public final class ManualSmokeTest {

    public static void main(String[] args) throws Exception {

        TestProject project =
                TestProjects.simpleProject(
                        """
                        package test;

                        public class A {
                            public void run() {
                                new B().foo();
                            }
                        }
                        """,
                        """
                        package test;

                        public class B {
                            public void foo() {}
                        }
                        """
                );

        Report report =
                EngineTestHarness.run(
                        TestClasspath.of(project.classesDir())
                );

        // Assertions reais
        CallGraph graph =
                report.section("Call Graph Metrics")
                        .as(CallGraph.class);

        CallGraphAssertions.assertNodeCount(graph, 2);
        CallGraphAssertions.assertEdgeCount(graph, 1);

        System.out.println("✔ ENGINE OK");
    }
}
