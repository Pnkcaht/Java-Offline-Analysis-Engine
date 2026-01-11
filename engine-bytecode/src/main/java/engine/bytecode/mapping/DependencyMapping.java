package engine.bytecode.mapping;

import engine.model.symbol.ClassSymbol;

/**
 * Raw class dependency mapping.
 */
public record DependencyMapping(
        ClassSymbol from,
        ClassSymbol to
) { }
