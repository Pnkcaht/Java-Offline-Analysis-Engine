package engine.model.visibility;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * Allowed visibilities for a given access context.
 */
public final class VisibilitySet {

    private final Set<Visibility> allowed;

    private VisibilitySet(Set<Visibility> allowed) {
        this.allowed = Set.copyOf(allowed);
    }

    public static VisibilitySet all() {
        return new VisibilitySet(EnumSet.allOf(Visibility.class));
    }

    public static VisibilitySet publicOnly() {
        return new VisibilitySet(EnumSet.of(Visibility.PUBLIC));
    }

    public static VisibilitySet publicAndProtected() {
        return new VisibilitySet(EnumSet.of(
                Visibility.PUBLIC,
                Visibility.PROTECTED
        ));
    }

    public boolean allows(Visibility visibility) {
        Objects.requireNonNull(visibility);
        return allowed.contains(visibility);
    }

    @Override
    public String toString() {
        return "VisibilitySet" + allowed;
    }
}
