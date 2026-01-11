package engine.model.metadata;

import java.util.Objects;

/**
 * Typed key for metadata entries.
 */
public final class MetadataKey<T> {

    private final String name;
    private final Class<T> type;

    public MetadataKey(String name, Class<T> type) {
        this.name = Objects.requireNonNull(name);
        this.type = Objects.requireNonNull(type);
    }

    public String name() {
        return name;
    }

    public Class<T> type() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof MetadataKey<?> that)
                && name.equals(that.name)
                && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "MetadataKey[" + name + ":" + type.getSimpleName() + "]";
    }
}
