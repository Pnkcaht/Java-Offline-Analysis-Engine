package engine.testkit.bytecode;

import java.nio.file.Path;
import java.util.List;

/**
 * Test classpath container.
 */
public final class TestClasspath {

    private final List<Path> entries;

    public TestClasspath(List<Path> entries) {
        this.entries = List.copyOf(entries);
    }

    public List<Path> entries() {
        return entries;
    }
}
