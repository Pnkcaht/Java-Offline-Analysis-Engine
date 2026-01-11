package engine.bytecode.descriptor;

/**
 * JVM array type descriptor.
 */
public final class ArrayTypeDescriptor implements TypeDescriptor {

    private final TypeDescriptor componentType;
    private final int dimensions;

    public ArrayTypeDescriptor(TypeDescriptor componentType, int dimensions) {
        if (dimensions < 1) {
            throw new IllegalArgumentException("dimensions must be >= 1");
        }
        this.componentType = componentType;
        this.dimensions = dimensions;
    }

    public TypeDescriptor componentType() {
        return componentType;
    }

    public int dimensions() {
        return dimensions;
    }

    @Override
    public String descriptor() {
        return "[".repeat(dimensions) + componentType.descriptor();
    }

    @Override
    public String toString() {
        return descriptor();
    }
}
