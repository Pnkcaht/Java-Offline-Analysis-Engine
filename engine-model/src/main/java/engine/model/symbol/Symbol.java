package engine.model.symbol;

/**
 * Symbolic reference extracted from bytecode.
 *
 * <p>Symbols represent references BEFORE resolution.
 * They do not imply existence or validity.</p>
 */
public sealed interface Symbol
        permits ClassSymbol, MethodSymbol, FieldSymbol {

    SymbolKind kind();

    /**
     * JVM internal owner name, if applicable.
     */
    String ownerInternalName();

    /**
     * Symbol name (class, field, or method).
     */
    String name();

    /**
     * JVM descriptor associated with this symbol.
     */
    String descriptor();
}
