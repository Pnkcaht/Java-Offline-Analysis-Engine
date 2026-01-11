package engine.analysis.metrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregates metrics from multiple analyses.
 */
public final class MetricsReport {

    private final List<Metric> metrics = new ArrayList<>();

    public void add(Metric metric) {
        metrics.add(metric);
    }

    public List<Metric> all() {
        return List.copyOf(metrics);
    }
}
