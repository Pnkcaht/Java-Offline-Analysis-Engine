package engine.testkit.bytecode;

import engine.context.AnalysisContext;
import engine.io.classpath.ClasspathScanner;

public final class BytecodeLoader {

    public static void load(
            TestClasspath classpath,
            AnalysisContext context
    ) throws Exception {

        ClasspathScanner scanner =
                new ClasspathScanner(context);

        for (var entry : classpath.entries()) {
            scanner.scan(entry);
        }
    }
}
