package engine.bytecode.descriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * JVM descriptor parser.
 */
public final class DescriptorParser {

    private final String input;
    private int index = 0;

    private DescriptorParser(String input) {
        this.input = input;
    }

    public static FieldDescriptor parseField(String descriptor) {
        DescriptorParser p = new DescriptorParser(descriptor);
        TypeDescriptor type = p.parseType();
        if (!p.atEnd()) {
            throw new IllegalArgumentException(
                    "Trailing data in field descriptor: " + descriptor
            );
        }
        return new FieldDescriptor(type);
    }

    public static MethodDescriptor parseMethod(String descriptor) {
        DescriptorParser p = new DescriptorParser(descriptor);

        if (p.next() != '(') {
            throw new IllegalArgumentException(
                    "Invalid method descriptor: " + descriptor
            );
        }

        List<TypeDescriptor> params = new ArrayList<>();
        while (p.peek() != ')') {
            params.add(p.parseType());
        }
        p.next(); // ')'

        TypeDescriptor returnType = p.parseType();
        if (!p.atEnd()) {
            throw new IllegalArgumentException(
                    "Trailing data in method descriptor: " + descriptor
            );
        }

        return new MethodDescriptor(params, returnType);
    }

    private TypeDescriptor parseType() {
        char c = next();

        if (c == '[') {
            int dims = 1;
            while (peek() == '[') {
                next();
                dims++;
            }
            return new ArrayTypeDescriptor(parseType(), dims);
        }

        if (c == 'L') {
            int start = index;
            while (peek() != ';') next();
            String name = input.substring(start, index);
            next(); // ';'
            return new ObjectTypeDescriptor(name);
        }

        return PrimitiveTypeDescriptor.of(c);
    }

    private char peek() {
        return input.charAt(index);
    }

    private char next() {
        return input.charAt(index++);
    }

    private boolean atEnd() {
        return index == input.length();
    }
}
