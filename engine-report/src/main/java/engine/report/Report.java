package engine.report;

import java.util.List;
import java.util.Objects;

/**
 * Immutable analysis report.
 */
public final class Report {

    private final List<ReportSection> sections;

    public Report(List<ReportSection> sections) {
        this.sections = List.copyOf(
                Objects.requireNonNull(sections)
        );
    }

    public List<ReportSection> sections() {
        return sections;
    }

    @Override
    public String toString() {
        return "Report[sections=" + sections.size() + "]";
    }
}
