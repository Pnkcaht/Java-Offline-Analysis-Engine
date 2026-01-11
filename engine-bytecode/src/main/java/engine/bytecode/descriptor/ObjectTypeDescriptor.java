package engine.bytecode.descriptor;

/**
 * JVM object reference type.
 */
public final class ObjectTypeDescriptor implements TypeDescriptor {

    private final String internalName;

    public ObjectTypeDescriptor(String internalName) {
        if (internalName == null || internalName.isBlank()) {
            throw new IllegalArgumentException("internalName is required");
        }
        this.internalName = internalName;
    }

    public String internalName() {
        return internalName;
    }

    @Override
    public String descriptor() {
        return "L" + internalName + ";";
    }

    @Override
    public String toString() {
        return descriptor();
    }
}
