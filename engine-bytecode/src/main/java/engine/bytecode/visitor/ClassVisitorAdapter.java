package engine.bytecode.visitor;

import engine.context.ClassIndex;
import engine.model.element.ClassElement;
import engine.model.metadata.MetadataContainer;
import engine.model.visibility.AccessFlags;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * ASM ClassVisitor adapter.
 *
 * <p>Extracts class-level structural information.</p>
 */
public final class ClassVisitorAdapter extends ClassVisitor {

    private final ClassIndex classIndex;

    private String internalName;
    private String superName;
    private AccessFlags accessFlags;

    public ClassVisitorAdapter(ClassIndex classIndex) {
        super(Opcodes.ASM9);
        this.classIndex = classIndex;
    }

    @Override
    public void visit(
            int version,
            int access,
            String name,
            String signature,
            String superName,
            String[] interfaces
    ) {
        this.internalName = name;
        this.superName = superName;
        this.accessFlags = new AccessFlags(access);
    }

    @Override
    public void visitEnd() {
        ClassElement element = new ClassElement(
                internalName,
                superName,
                accessFlags.visibility(),
                MetadataContainer.empty()
        );
        classIndex.register(element);
    }
}
