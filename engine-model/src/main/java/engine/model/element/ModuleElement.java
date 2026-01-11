package engine.model.element;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Immutable representation of a Java module (JPMS).
 */
public final class ModuleElement {

    private final String name;

    private final Set<String> requires;
    private final Set<String> exports;
    private final Set<String> opens;
    private final Set<String> uses;
    private final Set<String> provides;

    public ModuleElement(
            String name,
            Set<String> requires,
            Set<String> exports,
            Set<String> opens,
            Set<String> uses,
            Set<String> provides
    ) {
        this.name = requireNonEmpty(name, "name");
        this.requires = immutableSet(requires);
        this.exports = immutableSet(exports);
        this.opens = immutableSet(opens);
        this.uses = immutableSet(uses);
        this.provides = immutableSet(provides);
    }

    public String getName() {
        return name;
    }

    public Set<String> getRequires() {
        return requires;
    }

    public Set<String> getExports() {
        return exports;
    }

    public Set<String> getOpens() {
        return opens;
    }

    public Set<String> getUses() {
        return uses;
    }

    public Set<String> getProvides() {
        return provides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModuleElement)) return false;
        ModuleElement that = (ModuleElement) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "ModuleElement[" + name + "]";
    }

    private static <T> Set<T> immutableSet(Set<T> input) {
        Objects.requireNonNull(input, "set");
        return Collections.unmodifiableSet(Set.copyOf(input));
    }

    private static String requireNonEmpty(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be null or empty");
        }
        return value;
    }
}
