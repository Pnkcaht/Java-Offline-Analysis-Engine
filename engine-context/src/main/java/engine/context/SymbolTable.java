package engine.context;

import engine.model.symbol.Symbol;
import engine.model.symbol.SymbolTableKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Central symbol resolution table.
 */
public final class SymbolTable {

    private final Map<SymbolTableKey, ResolutionResult<?>> resolved = new HashMap<>();

    public <T> void register(Symbol symbol, ResolutionResult<T> result) {
        resolved.put(SymbolTableKey.of(symbol), result);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<ResolutionResult<T>> resolve(Symbol symbol) {
        return Optional.ofNullable(
                (ResolutionResult<T>) resolved.get(SymbolTableKey.of(symbol))
        );
    }

    @Override
    public String toString() {
        return "SymbolTable[size=" + resolved.size() + "]";
    }
}
