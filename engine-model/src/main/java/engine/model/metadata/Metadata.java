package engine.model.metadata;

/**
 * Immutable metadata entry.
 */
public record Metadata<T>(MetadataKey<T> key, T value) { }
