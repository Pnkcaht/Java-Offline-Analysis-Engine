package engine.bytecode.descriptor;

/**
 * Parsed field descriptor.
 */
public final class FieldDescriptor {

    private final TypeDescriptor type;

    public FieldDescriptor(TypeDescriptor type) {
        this.type = type;
    }

    public TypeDescriptor type() {
        return type;
    }

    @Override
    public String toString() {
        return type.descriptor();
    }
}
