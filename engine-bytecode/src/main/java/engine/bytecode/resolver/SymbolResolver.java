package engine.bytecode.resolver;

import engine.context.ClassIndex;
import engine.context.ResolutionResult;
import engine.model.element.ClassElement;
import engine.model.symbol.ClassSymbol;

import java.util.Optional;

/**
 * Resolves class symbols into model elements.
 */
public final class SymbolResolver {

    private final ClassIndex classIndex;

    public SymbolResolver(ClassIndex classIndex) {
        this.classIndex = classIndex;
    }

    public ResolutionResult<ClassElement> resolve(ClassSymbol symbol) {
        Optional<ClassElement> element =
                classIndex.find(symbol.internalName());

        return element
                .map(ResolutionResult::resolved)
                .orElseGet(() ->
                        ResolutionResult.unresolved(
                                "Class not found: " + symbol.internalName()
                        )
                );
    }
}
