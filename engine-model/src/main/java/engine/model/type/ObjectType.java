package engine.model.type;

import java.util.Objects;

/**
 * JVM object type (class or interface).
 */
public final class ObjectType implements JvmType {

    private final String internalName;

    public ObjectType(String internalName) {
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
    public TypeKind kind() {
        return TypeKind.OBJECT;
    }

    @Override
    public String descriptor() {
        return "L" + internalName + ";";
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof ObjectType that)
                && internalName.equals(that.internalName);
    }

    @Override
    public int hashCode() {
        return internalName.hashCode();
    }

    @Override
    public String toString() {
        return descriptor();
    }
}
