package engine.model.metadata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable metadata container.
 */
public final class MetadataContainer {

    private final Map<MetadataKey<?>, Object> values;

    private MetadataContainer(Map<MetadataKey<?>, Object> values) {
        this.values = Map.copyOf(values);
    }

    public static MetadataContainer empty() {
        return new MetadataContainer(Map.of());
    }

    public <T> Optional<T> get(MetadataKey<T> key) {
        Objects.requireNonNull(key);
        Object value = values.get(key);
        if (value == null) return Optional.empty();
        return Optional.of(key.type().cast(value));
    }

    public <T> MetadataContainer with(MetadataKey<T> key, T value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        Map<MetadataKey<?>, Object> copy = new HashMap<>(values);
        copy.put(key, value);
        return new MetadataContainer(copy);
    }

    @Override
    public String toString() {
        return "MetadataContainer" + values.keySet();
    }
}
