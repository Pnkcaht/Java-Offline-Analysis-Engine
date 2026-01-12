package engine.plugin;

import engine.analysis.Analysis;
import engine.platform.AnalysisProvider;
import engine.platform.EngineExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Collects and exposes contributions from loaded plugins.
 */
public final class PluginRegistry {

    private final List<Analysis> analyses = new ArrayList<>();

    public PluginRegistry(Iterable<EngineExtension> extensions) {
        for (EngineExtension extension : extensions) {
            if (extension instanceof AnalysisProvider provider) {
                analyses.addAll(provider.analyses());
            }
        }
    }

    public Collection<Analysis> analyses() {
        return List.copyOf(analyses);
    }
}
