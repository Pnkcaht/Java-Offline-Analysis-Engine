package engine.testkit.fixtures;

import java.nio.file.Path;

public final class SimpleProject implements TestProject {

    private final Path root;
    private final Path classes;

    public SimpleProject(Path root, Path classes) {
        this.root = root;
        this.classes = classes;
    }

    @Override
    public Path root() {
        return root;
    }

    @Override
    public Path classesDir() {
        return classes;
    }
}
