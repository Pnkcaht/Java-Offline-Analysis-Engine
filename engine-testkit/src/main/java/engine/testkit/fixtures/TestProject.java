package engine.testkit.fixtures;

import java.nio.file.Path;

/**
 * Represents a compiled Java test project.
 */
public interface TestProject {

    Path root();

    Path classesDir();
}
