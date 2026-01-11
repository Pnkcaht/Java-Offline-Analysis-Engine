package engine.model.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses JVM type descriptors into canonical JvmType instances.
 */
public final class TypeDescriptorParser {

    private final String descriptor;
    private int index;

    private TypeDescriptorParser(String descriptor) {
        this.descriptor = descriptor;
    }

    public static JvmType parse(String descriptor) {
        TypeDescriptorParser p = new TypeDescriptorParser(descriptor);
        JvmType type = p.parseType();
        if (p.index != descriptor.length()) {
            throw new IllegalArgumentException("Trailing characters in descriptor: " + descriptor);
        }
        return type;
    }

    private JvmType parseType() {
        char c = descriptor.charAt(index);

        if (c == '(') {
            return parseMethodType();
        }

        if (c == '[') {
            return parseArrayType();
        }

        if (PrimitiveType.fromDescriptor(c) != null) {
            index++;
            return PrimitiveType.fromDescriptor(c);
        }

        if (c == 'L') {
            return parseObjectType();
        }

        throw new IllegalArgumentException("Invalid descriptor at index " + index);
    }

    private MethodType parseMethodType() {
        index++; // '('
        List<JvmType> params = new ArrayList<>();

        while (descriptor.charAt(index) != ')') {
            params.add(parseType());
        }

        index++; // ')'
        JvmType returnType = parseType();
        return new MethodType(params, returnType);
    }

    private ArrayType parseArrayType() {
        int dims = 0;
        while (descriptor.charAt(index) == '[') {
            dims++;
            index++;
        }
        return new ArrayType(parseType(), dims);
    }

    private ObjectType parseObjectType() {
        index++; // 'L'
        int start = index;
        while (descriptor.charAt(index) != ';') {
            index++;
        }
        String name = descriptor.substring(start, index);
        index++; // ';'
        return new ObjectType(name);
    }
}
