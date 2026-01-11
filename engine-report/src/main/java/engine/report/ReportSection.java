package engine.report;

import java.util.Map;
import java.util.Objects;

/**
 * Single section of a report.
 */
public final class ReportSection {

    private final String title;
    private final Map<String, Object> data;

    public ReportSection(String title, Map<String, Object> data) {
        this.title = Objects.requireNonNull(title);
        this.data = Map.copyOf(Objects.requireNonNull(data));
    }

    public String title() {
        return title;
    }

    public Map<String, Object> data() {
        return data;
    }

    @Override
    public String toString() {
        return "ReportSection[" + title + "]";
    }
}
