package engine.bytecode.mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects raw bytecode mappings before resolution.
 */
public final class MappingCollector {

    private final List<CallMapping> callMappings = new ArrayList<>();
    private final List<DependencyMapping> dependencyMappings = new ArrayList<>();

    public void recordCall(CallMapping mapping) {
        callMappings.add(mapping);
    }

    public void recordDependency(DependencyMapping mapping) {
        dependencyMappings.add(mapping);
    }

    public List<CallMapping> calls() {
        return List.copyOf(callMappings);
    }

    public List<DependencyMapping> dependencies() {
        return List.copyOf(dependencyMappings);
    }
}
