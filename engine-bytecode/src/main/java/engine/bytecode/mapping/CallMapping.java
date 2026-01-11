package engine.bytecode.mapping;

import engine.model.symbol.MethodSymbol;

/**
 * Raw call mapping extracted from bytecode.
 */
public record CallMapping(
        MethodSymbol caller,
        MethodSymbol callee
) { }
