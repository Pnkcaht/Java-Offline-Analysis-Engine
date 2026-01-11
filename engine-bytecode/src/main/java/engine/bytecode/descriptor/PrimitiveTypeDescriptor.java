package engine.bytecode.descriptor;

/**
 * JVM primitive type.
 */
public final class PrimitiveTypeDescriptor implements TypeDescriptor {

    private final char code;

    private PrimitiveTypeDescriptor(char code) {
        this.code = code;
    }

    public static PrimitiveTypeDescriptor of(char code) {
        return switch (code) {
            case 'B','C','D','F','I','J','S','Z','V' ->
                    new PrimitiveTypeDescriptor(code);
            default -> throw new IllegalArgumentException(
                    "Invalid primitive descriptor: " + code
            );
        };
    }

    public char code() {
        return code;
    }

    @Override
    public String descriptor() {
        return String.valueOf(code);
    }

    @Override
    public String toString() {
        return descriptor();
    }
}
