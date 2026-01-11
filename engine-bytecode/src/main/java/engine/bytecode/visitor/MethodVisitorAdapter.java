package engine.bytecode.visitor;

import engine.context.SymbolTable;
import engine.model.symbol.MethodSymbol;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Collects method invocation symbols. ⚠️⚠️
 */
public final class MethodVisitorAdapter extends MethodVisitor {

    private final String ownerInternalName;
    private final SymbolTable symbolTable;

    public MethodVisitorAdapter(
            String ownerInternalName,
            SymbolTable symbolTable
    ) {
        super(Opcodes.ASM9);
        this.ownerInternalName = ownerInternalName;
        this.symbolTable = symbolTable;
    }

    @Override
    public void visitMethodInsn(
            int opcode,
            String owner,
            String name,
            String descriptor,
            boolean isInterface
    ) {
        MethodSymbol symbol =
                new MethodSymbol(owner, name, descriptor);

        // resolução vem depois — aqui só registra referência
        symbolTable.resolve(symbol);
    }
}
