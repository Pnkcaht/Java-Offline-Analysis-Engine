package engine.context;

import java.util.ArrayList;
import java.util.List;

/**
 * Collects diagnostics (info, warnings, errors)
 * produced during analysis execution.
 */
public final class DiagnosticsCollector {

    public enum Level {
        INFO,
        WARN,
        ERROR
    }

    public static final class Diagnostic {
        private final Level level;
        private final String message;

        public Diagnostic(Level level, String message) {
            this.level = level;
            this.message = message;
        }

        public Level level() {
            return level;
        }

        public String message() {
            return message;
        }

        @Override
        public String toString() {
            return "[" + level + "] " + message;
        }
    }

    private final List<Diagnostic> diagnostics = new ArrayList<>();

    // === emitters ===

    public void info(String message) {
        diagnostics.add(new Diagnostic(Level.INFO, message));
    }

    public void warn(String message) {
        diagnostics.add(new Diagnostic(Level.WARN, message));
    }

    public void error(String message) {
        diagnostics.add(new Diagnostic(Level.ERROR, message));
    }

    // === queries ===

    public boolean hasErrors() {
        return diagnostics.stream()
                .anyMatch(d -> d.level() == Level.ERROR);
    }

    public List<Diagnostic> all() {
        return List.copyOf(diagnostics);
    }
}
