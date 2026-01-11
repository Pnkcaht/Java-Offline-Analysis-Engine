package engine.model.type;

/**
 * Structural JVM type validation utilities.
 */
public final class TypeValidation {

    private TypeValidation() {}

    public static void validate(JvmType type) {
        switch (type.kind()) {
            case PRIMITIVE -> {}
            case OBJECT -> {}
            case ARRAY -> validate(((ArrayType) type).componentType());
            case METHOD -> {
                MethodType m = (MethodType) type;
                m.parameterTypes().forEach(TypeValidation::validate);
                validate(m.returnType());
            }
        }
    }
}
