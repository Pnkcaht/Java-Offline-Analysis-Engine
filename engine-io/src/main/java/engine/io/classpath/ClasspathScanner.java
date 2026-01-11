package engine.io.classpath;

import engine.context.AnalysisContext;
import engine.context.DiagnosticsCollector;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Scans an input path (directory or jar) and registers
 * classes into the AnalysisContext ClassIndex.
 *
 * <p>This is a minimal MVP implementation.</p>
 */
public final class ClasspathScanner {

    private final AnalysisContext context;
    private final DiagnosticsCollector diagnostics;

    public ClasspathScanner(AnalysisContext context) {
        this.context = Objects.requireNonNull(context);
        this.diagnostics = context.diagnostics();
    }

    public void scan(Path input) throws IOException {
        Objects.requireNonNull(input);

        if (!Files.exists(input)) {
            diagnostics.error("Input path does not exist: " + input);
            return;
        }

        if (Files.isDirectory(input)) {
            scanDirectory(input);
        } else if (input.toString().endsWith(".jar")) {
            diagnostics.warn("JAR scanning not implemented yet: " + input);
        } else {
            diagnostics.warn("Unsupported input type: " + input);
        }
    }

    private void scanDirectory(Path root) throws IOException {
        try (Stream<Path> paths = Files.walk(root)) {
            paths
                    .filter(p -> p.toString().endsWith(".class"))
                    .forEach(this::handleClassFile);
        }
    }

    private void handleClassFile(Path classFile) {
        // FUTURE:
        // - parse bytecode (ASM)
        // - extract class name
        // - register in ClassIndex

        diagnostics.info("Found class file: " + classFile);
    }
}
