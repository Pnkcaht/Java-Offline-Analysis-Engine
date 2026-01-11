package engine.model.type;

import java.util.Map;

/**
 * JVM primitive types, including void.
 */
public enum PrimitiveType implements JvmType {

    BOOLEAN('Z'),
    BYTE('B'),
    CHAR('C'),
    SHORT('S'),
    INT('I'),
    LONG('J'),
    FLOAT('F'),
    DOUBLE('D'),
    VOID('V');

    private static final Map<Character, PrimitiveType> LOOKUP =
            Map.ofEntries(
                    Map.entry('Z', BOOLEAN),
                    Map.entry('B', BYTE),
                    Map.entry('C', CHAR),
                    Map.entry('S', SHORT),
                    Map.entry('I', INT),
                    Map.entry('J', LONG),
                    Map.entry('F', FLOAT),
                    Map.entry('D', DOUBLE),
                    Map.entry('V', VOID)
            );

    private final char descriptor;

    PrimitiveType(char descriptor) {
        this.descriptor = descriptor;
    }

    public char descriptorChar() {
        return descriptor;
    }

    public static PrimitiveType fromDescriptor(char c) {
        PrimitiveType type = LOOKUP.get(c);
        if (type == null) {
            throw new IllegalArgumentException("Invalid primitive descriptor: " + c);
        }
        return type;
    }

    @Override
    public TypeKind kind() {
        return TypeKind.PRIMITIVE;
    }

    @Override
    public String descriptor() {
        return String.valueOf(descriptor);
    }
}
