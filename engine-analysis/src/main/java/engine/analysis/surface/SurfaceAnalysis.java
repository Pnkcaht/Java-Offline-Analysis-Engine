package engine.analysis.surface;

import engine.analysis.Analysis;
import engine.context.AnalysisContext;
import engine.model.element.ClassElement;
import engine.model.visibility.Visibility;

/**
 * Computes exposed surface area.
 */
public final class SurfaceAnalysis implements Analysis {

    @Override
    public void run(AnalysisContext context) {
        SurfaceArea area = new SurfaceArea();

        for (ClassElement clazz : context.classIndex().all()) {
            if (clazz.visibility() == Visibility.PUBLIC) {
                area.incrementPublicClasses();
            }
        }
    }
}
