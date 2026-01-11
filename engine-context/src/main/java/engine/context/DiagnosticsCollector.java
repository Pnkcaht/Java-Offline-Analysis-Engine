package engine.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central diagnostics sink.
 */
public final class DiagnosticsCollector {

    private final List<String> messages = new ArrayList<>();

    public void report(String message) {
        messages.add(message);
    }

    public List<String> all() {
        return Collections.unmodifiableList(messages);
    }

    public boolean hasErrors() {
        return !messages.isEmpty();
    }

    @Override
    public String toString() {
        return "Diagnostics[" + messages.size() + "]";
    }
}
