package engine.testkit.fixtures;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class ProjectCompiler {

    public static TestProject compile(Path sourceDir) throws Exception {
        Path out = Files.createTempDirectory("engine-test-classes");

        JavaCompiler compiler =
                ToolProvider.getSystemJavaCompiler();

        if (compiler == null)
            throw new IllegalStateException(
                    "No Java compiler available"
            );

        List<String> args = List.of(
                "-d", out.toString(),
                sourceDir.resolve("Main.java").toString()
        );

        int result = compiler.run(
                null, null, null,
                args.toArray(String[]::new)
        );

        if (result != 0)
            throw new IllegalStateException(
                    "Compilation failed"
            );

        return new SimpleProject(sourceDir, out);
    }
}
