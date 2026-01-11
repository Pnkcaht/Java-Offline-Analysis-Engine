package engine.bytecode.descriptor;

import java.util.List;

/**
 * Parsed method descriptor.
 */
public final class MethodDescriptor {

    private final List<TypeDescriptor> parameterTypes;
    private final TypeDescriptor returnType;

    public MethodDescriptor(
            List<TypeDescriptor> parameterTypes,
            TypeDescriptor returnType
    ) {
        this.parameterTypes = List.copyOf(parameterTypes);
        this.returnType = returnType;
    }

    public List<TypeDescriptor> parameterTypes() {
        return parameterTypes;
    }

    public TypeDescriptor returnType() {
        return returnType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (TypeDescriptor p : parameterTypes) {
            sb.append(p.descriptor());
        }
        sb.append(")").append(returnType.descriptor());
        return sb.toString();
    }
}
