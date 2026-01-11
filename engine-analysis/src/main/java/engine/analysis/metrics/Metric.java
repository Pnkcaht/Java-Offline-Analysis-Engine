package engine.analysis.surface;

/**
 * Represents exposed API surface.
 */
public final class SurfaceArea {

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
