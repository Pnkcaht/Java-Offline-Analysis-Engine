package engine.model.type;

/**
 * Canonical JVM type.
 *
 * <p>All implementations are immutable and value-based.</p>
 */
public sealed interface JvmType
        permits PrimitiveType, ObjectType, ArrayType, MethodType {

    TypeKind kind();

    /**
     * JVM descriptor representation.
     */
    String descriptor();
}
