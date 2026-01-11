package engine.model.metadata;

/**
 * Source-level information attached to a model element.
 */
public record SourceInfo(
        String sourceFile,
        int lineStart,
        int lineEnd
) { }
