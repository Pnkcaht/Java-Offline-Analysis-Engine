package engine.model.symbol;

import java.util.Objects;

/**
 * Symbolic reference to a JVM class or interface.
 */
public final class ClassSymbol implements Symbol {

    private final String internalName;

    public ClassSymbol(String internalName) {
        if (internalName == null || internalName.isBlank()) {
            throw new IllegalArgumentException("internalName must not be null or empty");
        }
        if (internalName.contains(".")) {
            throw new IllegalArgumentException("Use JVM internal name with '/' separators");
        }
        this.internalName = internalName;
    }

    public String internalName() {
        return internalName;
    }

    @Override
    public SymbolKind kind() {
        return SymbolKind.CLASS;
    }

    @Override
    public String ownerInternalName() {
        return null;
    }

    @Override
    public String name() {
        return internalName;
    }

    @Override
    public String descriptor() {
        return "L" + internalName + ";";
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof ClassSymbol that)
                && internalName.equals(that.internalName);
    }

    @Override
    public int hashCode() {
        return internalName.hashCode();
    }

    @Override
    public String toString() {
        return "ClassSymbol[" + internalName + "]";
    }
}
