package engine.analysis.surface;

import engine.analysis.Analysis;
import engine.context.AnalysisContext;
import engine.model.element.ClassElement;
import engine.model.visibility.Visibility;
import engine.model.visibility.VisibilityResolver;

public final class SurfaceAnalysis implements Analysis {

    @Override
    public void run(AnalysisContext context) {
        SurfaceArea area = new SurfaceArea();

        for (ClassElement clazz : context.classIndex().all()) {
            if (VisibilityResolver.fromAccess(clazz.getAccessFlags()) == Visibility.PUBLIC) {
                area.incrementPublicClasses();
            }
        }

        context.putResult(SurfaceArea.class, area);
    }
}
