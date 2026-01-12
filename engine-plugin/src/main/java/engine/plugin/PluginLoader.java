package engine.plugin;

import engine.platform.EngineExtension;

import java.util.ServiceLoader;
import java.util.stream.Stream;

/**
 * Loads engine extensions using Java ServiceLoader.
 */
public final class PluginLoader {

    public Stream<EngineExtension> load() {
        ServiceLoader<EngineExtension> loader =
                ServiceLoader.load(EngineExtension.class);

        return loader.stream()
                .map(ServiceLoader.Provider::get);
    }
}
