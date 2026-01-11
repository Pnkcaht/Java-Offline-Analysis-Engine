package engine.model.symbol;

import java.util.Objects;

/**
 * Symbolic reference to a JVM method.
 */
public final class MethodSymbol implements Symbol {

    private final String ownerInternalName;
    private final String name;
    private final String descriptor;

    public MethodSymbol(String ownerInternalName, String name, String descriptor) {
        this.ownerInternalName = require(ownerInternalName, "ownerInternalName");
        this.name = require(name, "name");
        this.descriptor = require(descriptor, "descriptor");
    }

    @Override
    public SymbolKind kind() {
        return SymbolKind.METHOD;
    }

    @Override
    public String ownerInternalName() {
        return ownerInternalName;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String descriptor() {
        return descriptor;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof MethodSymbol that)
                && ownerInternalName.equals(that.ownerInternalName)
                && name.equals(that.name)
                && descriptor.equals(that.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerInternalName, name, descriptor);
    }

    @Override
    public String toString() {
        return ownerInternalName + "." + name + descriptor;
    }

    private static String require(String v, String n) {
        if (v == null || v.isBlank()) {
            throw new IllegalArgumentException(n + " must not be null or empty");
        }
        return v;
    }
}
