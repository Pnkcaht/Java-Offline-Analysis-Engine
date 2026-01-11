package engine.model.visibility;

public final class VisibilityResolver {

    private static final int ACC_PUBLIC    = 0x0001;
    private static final int ACC_PRIVATE   = 0x0002;
    private static final int ACC_PROTECTED = 0x0004;

    private VisibilityResolver() {}

    public static Visibility fromAccess(int accessFlags) {
        if ((accessFlags & ACC_PUBLIC) != 0) return Visibility.PUBLIC;
        if ((accessFlags & ACC_PROTECTED) != 0) return Visibility.PROTECTED;
        if ((accessFlags & ACC_PRIVATE) != 0) return Visibility.PRIVATE;
        return Visibility.PACKAGE_PRIVATE;
    }
}
