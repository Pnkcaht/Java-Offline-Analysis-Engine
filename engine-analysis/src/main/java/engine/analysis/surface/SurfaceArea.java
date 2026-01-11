package engine.analysis.surface;

import engine.analysis.metrics.Metric;

/**
 * Represents exposed API surface.
 */
public final class SurfaceArea implements Metric {

    private int publicClasses;

    public void incrementPublicClasses() {
        publicClasses++;
    }

    public int publicClasses() {
        return publicClasses;
    }

    @Override
    public String toString() {
        return "SurfaceArea[publicClasses=" + publicClasses + "]";
    }
}
