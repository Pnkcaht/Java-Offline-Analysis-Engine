package engine.model.type;

/**
 * Canonical string rendering for JVM types.
 */
public final class TypePrinter {

    private TypePrinter() {}

    public static String print(JvmType type) {
        return type.descriptor();
    }
}
