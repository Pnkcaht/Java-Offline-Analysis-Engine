package engine.bytecode.visitor;

import engine.context.SymbolTable;
import engine.model.symbol.FieldSymbol;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Collects field access symbols.
 */
public final class FieldVisitorAdapter extends FieldVisitor {

    private final String ownerInternalName;
    private final SymbolTable symbolTable;

    public FieldVisitorAdapter(
            String ownerInternalName,
            SymbolTable symbolTable
    ) {
        super(Opcodes.ASM9);
        this.ownerInternalName = ownerInternalName;
        this.symbolTable = symbolTable;
    }

    @Override
    public void visitEnd() {
        // field-level hooks go here if needed later
    }

    public void recordAccess(String name, String descriptor) {
        FieldSymbol symbol =
                new FieldSymbol(ownerInternalName, name, descriptor);

        symbolTable.resolve(symbol);
    }
}
