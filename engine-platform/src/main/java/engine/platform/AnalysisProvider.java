package engine.platform;

import engine.analysis.Analysis;

import java.util.Collection;

/**
 * Provides analysis units to the engine.
 *
 * Implementations are discovered and registered by the core.
 */
public interface AnalysisProvider {

    Collection<Analysis> analyses();
}
