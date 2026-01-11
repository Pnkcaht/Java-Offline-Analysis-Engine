package engine.model.symbol;

import java.util.Objects;

/**
 * Canonical key for symbol tables and indexes.
 *
 * <p>Provides a stable structural identity independent of object instance.</p>
 */
public final class SymbolTableKey {

    private final SymbolKind kind;
    private final String owner;
    private final String name;
    private final String descriptor;

    private SymbolTableKey(
            SymbolKind kind,
            String owner,
            String name,
            String descriptor
    ) {
        this.kind = kind;
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
    }

    public static SymbolTableKey of(Symbol symbol) {
        return new SymbolTableKey(
                symbol.kind(),
                symbol.ownerInternalName(),
                symbol.name(),
                symbol.descriptor()
        );
    }

    public SymbolKind kind() {
        return kind;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SymbolTableKey that)) return false;
        return kind == that.kind
                && Objects.equals(owner, that.owner)
                && Objects.equals(name, that.name)
                && Objects.equals(descriptor, that.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, owner, name, descriptor);
    }

    @Override
    public String toString() {
        return kind + ":" +
                (owner != null ? owner + "." : "") +
                name +
                (descriptor != null ? descriptor : "");
    }
}
