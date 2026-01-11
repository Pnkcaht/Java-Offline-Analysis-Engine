package engine.bytecode.reader;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Low-level bytecode reader.
 *
 * <p>Responsible only for loading and parsing JVM class files.</p>
 */
public final class BytecodeReader {

    public ClassReader read(InputStream input) throws IOException {
        Objects.requireNonNull(input, "input");
        return new ClassReader(input);
    }
}
