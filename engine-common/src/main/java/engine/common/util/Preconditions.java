package engine.common.util;

public final class Preconditions {

    private Preconditions() {}

    public static <T> T notNull(T value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }
}
