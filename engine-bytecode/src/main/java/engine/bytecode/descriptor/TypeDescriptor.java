package engine.bytecode.descriptor;

/**
 * Canonical JVM type descriptor.
 */
public sealed interface TypeDescriptor
        permits PrimitiveTypeDescriptor,
        ObjectTypeDescriptor,
        ArrayTypeDescriptor {

    /**
     * JVM descriptor form.
     */
    String descriptor();
}
