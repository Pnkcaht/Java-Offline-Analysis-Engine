package engine.model.type;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * JVM method descriptor type.
 */
public final class MethodType implements JvmType {

    private final List<JvmType> parameterTypes;
    private final JvmType returnType;

    public MethodType(List<JvmType> parameterTypes, JvmType returnType) {
        Objects.requireNonNull(parameterTypes, "parameterTypes");
        this.parameterTypes = Collections.unmodifiableList(List.copyOf(parameterTypes));
        this.returnType = Objects.requireNonNull(returnType, "returnType");

        if (returnType.kind() == TypeKind.METHOD) {
            throw new IllegalArgumentException("Return type cannot be METHOD");
        }
    }

    public List<JvmType> parameterTypes() {
        return parameterTypes;
    }

    public JvmType returnType() {
        return returnType;
    }

    @Override
    public TypeKind kind() {
        return TypeKind.METHOD;
    }

    @Override
    public String descriptor() {
        StringBuilder sb = new StringBuilder("(");
        for (JvmType p : parameterTypes) {
            sb.append(p.descriptor());
        }
        return sb.append(")").append(returnType.descriptor()).toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof MethodType that)
                && parameterTypes.equals(that.parameterTypes)
                && returnType.equals(that.returnType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterTypes, returnType);
    }

    @Override
    public String toString() {
        return descriptor();
    }
}
