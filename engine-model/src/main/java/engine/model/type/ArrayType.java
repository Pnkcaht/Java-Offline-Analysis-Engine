package engine.model.type;

import java.util.Objects;

/**
 * JVM array type.
 */
public final class ArrayType implements JvmType {

    private final JvmType componentType;
    private final int dimensions;

    public ArrayType(JvmType componentType, int dimensions) {
        this.componentType = Objects.requireNonNull(componentType, "componentType");
        if (componentType.kind() == TypeKind.METHOD) {
            throw new IllegalArgumentException("Array of method type is invalid");
        }
        if (dimensions <= 0) {
            throw new IllegalArgumentException("dimensions must be > 0");
        }
        this.dimensions = dimensions;
    }

    public JvmType componentType() {
        return componentType;
    }

    public int dimensions() {
        return dimensions;
    }

    @Override
    public TypeKind kind() {
        return TypeKind.ARRAY;
    }

    @Override
    public String descriptor() {
        return "[".repeat(dimensions) + componentType.descriptor();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof ArrayType that)
                && dimensions == that.dimensions
                && componentType.equals(that.componentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentType, dimensions);
    }

    @Override
    public String toString() {
        return descriptor();
    }
}
