package engine.common.lifecycle;

public interface Lifecycle {

    void start();

    void stop();

    default boolean isRunning() {
        return true;
    }
}
