package engine.model.element;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Immutable representation of a JVM method.
 *
 * <p>Methods are uniquely identified by owner + name + descriptor.</p>
 */
public final class MethodElement {

    private final ClassElement owner;
    private final String name;
    private final String descriptor;
    private final int accessFlags;

    private final String returnType;
    private final List<String> parameterTypes;
    private final List<String> thrownTypes;

    public MethodElement(
            ClassElement owner,
            String name,
            String descriptor,
            int accessFlags,
            String returnType,
            List<String> parameterTypes,
            List<String> thrownTypes
    ) {
        this.owner = Objects.requireNonNull(owner, "owner");
        this.name = requireNonEmpty(name, "name");
        this.descriptor = requireNonEmpty(descriptor, "descriptor");
        this.accessFlags = accessFlags;

        this.returnType = requireNonEmpty(returnType, "returnType");
        this.parameterTypes = immutableList(parameterTypes);
        this.thrownTypes = immutableList(thrownTypes);
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

    public String getReturnType() {
        return returnType;
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public List<String> getThrownTypes() {
        return thrownTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MethodElement)) return false;
        MethodElement that = (MethodElement) o;
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
        return owner.getInternalName() + "#" + name + descriptor;
    }

    private static <T> List<T> immutableList(List<T> input) {
        Objects.requireNonNull(input, "list");
        return Collections.unmodifiableList(List.copyOf(input));
    }

    private static String requireNonEmpty(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be null or empty");
        }
        return value;
    }
}
