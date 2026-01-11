package engine.model.element;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Immutable representation of a JVM class or interface.
 *
 * <p>This is a pure structural model extracted from bytecode.
 * It contains no behavior, no resolution logic, and no IO.</p>
 *
 * <p>Identity is defined by the internal JVM name and analysis scope.</p>
 */
public final class ClassElement {

    private final String internalName;
    private final String binaryName;
    private final String packageName;
    private final int accessFlags;

    private final ClassElement superClass;
    private final Set<ClassElement> interfaces;

    private final Set<MethodElement> methods;
    private final Set<FieldElement> fields;

    public ClassElement(
            String internalName,
            String binaryName,
            String packageName,
            int accessFlags,
            ClassElement superClass,
            Set<ClassElement> interfaces,
            Set<MethodElement> methods,
            Set<FieldElement> fields
    ) {
        this.internalName = requireNonEmpty(internalName, "internalName");
        this.binaryName = requireNonEmpty(binaryName, "binaryName");
        this.packageName = Objects.requireNonNull(packageName, "packageName");
        this.accessFlags = accessFlags;

        this.superClass = superClass;
        this.interfaces = immutableSet(interfaces);
        this.methods = immutableSet(methods);
        this.fields = immutableSet(fields);
    }

    public String getInternalName() {
        return internalName;
    }

    public String getBinaryName() {
        return binaryName;
    }

    public String getPackageName() {
        return packageName;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public ClassElement getSuperClass() {
        return superClass;
    }

    public Set<ClassElement> getInterfaces() {
        return interfaces;
    }

    public Set<MethodElement> getMethods() {
        return methods;
    }

    public Set<FieldElement> getFields() {
        return fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassElement)) return false;
        ClassElement that = (ClassElement) o;
        return internalName.equals(that.internalName);
    }

    @Override
    public int hashCode() {
        return internalName.hashCode();
    }

    @Override
    public String toString() {
        return "ClassElement[" + internalName + "]";
    }

    private static <T> Set<T> immutableSet(Set<T> input) {
        Objects.requireNonNull(input, "set");
        return Collections.unmodifiableSet(Set.copyOf(input));
    }

    private static String requireNonEmpty(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be null or empty");
        }
        return value;
    }
}
