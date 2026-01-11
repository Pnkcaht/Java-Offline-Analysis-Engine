package engine.model.element;

import java.util.Objects;

/**
 * Immutable representation of a JVM field.
 */
public final class FieldElement {

    private final ClassElement owner;
    private final String name;
    private final String descriptor;
    private final int accessFlags;
    private final String fieldType;

    public FieldElement(
            ClassElement owner,
            String name,
            String descriptor,
            int accessFlags,
            String fieldType
    ) {
        this.owner = Objects.requireNonNull(owner, "owner");
        this.name = requireNonEmpty(name, "name");
        this.descriptor = requireNonEmpty(descriptor, "descriptor");
        this.accessFlags = accessFlags;
        this.fieldType = requireNonEmpty(fieldType, "fieldType");
    }

    public ClassElement getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getFieldType() {
        return fieldType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldElement)) return false;
        FieldElement that = (FieldElement) o;
        return owner.equals(that.owner)
                && name.equals(that.name)
                && descriptor.equals(that.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, name, descriptor);
    }

    @Override
    public String toString() {
        return owner.getInternalName() + "." + name + ":" + descriptor;
    }

    private static String requireNonEmpty(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be null or empty");
        }
        return value;
    }
}
