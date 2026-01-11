package engine.bytecode.reader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Scans directories and jars for .class files.
 */
public final class ClasspathScanner {

    public List<InputStream> scanDirectory(Path root) throws IOException {
        List<InputStream> classes = new ArrayList<>();

        Files.walk(root)
                .filter(p -> p.toString().endsWith(".class"))
                .forEach(p -> {
                    try {
                        classes.add(Files.newInputStream(p));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        return classes;
    }
}
