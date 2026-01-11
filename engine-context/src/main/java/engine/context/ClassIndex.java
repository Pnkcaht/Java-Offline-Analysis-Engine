package engine.context;

import engine.model.element.ClassElement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Index of all discovered classes.
 */
public final class ClassIndex {

    private final Map<String, ClassElement> byInternalName = new HashMap<>();

    public void register(ClassElement element) {
        byInternalName.put(element.internalName(), element);
    }

    public Optional<ClassElement> find(String internalName) {
        return Optional.ofNullable(byInternalName.get(internalName));
    }

    public Collection<ClassElement> all() {
        return byInternalName.values();
    }

    @Override
    public String toString() {
        return "ClassIndex[size=" + byInternalName.size() + "]";
    }
}
