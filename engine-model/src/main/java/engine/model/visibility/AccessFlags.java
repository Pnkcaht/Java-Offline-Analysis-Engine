package engine.model.visibility;

/**
 * JVM access flags abstraction.
 *
 * <p>Encapsulates raw JVM ACC_* flags and exposes
 * semantic queries.</p>
 */
public final class AccessFlags {

    private final int rawFlags;

    public AccessFlags(int rawFlags) {
        this.rawFlags = rawFlags;
    }

    public int raw() {
        return rawFlags;
    }

    public boolean isPublic() {
        return (rawFlags & 0x0001) != 0;
    }

    public boolean isPrivate() {
        return (rawFlags & 0x0002) != 0;
    }

    public boolean isProtected() {
        return (rawFlags & 0x0004) != 0;
    }

    public boolean isStatic() {
        return (rawFlags & 0x0008) != 0;
    }

    public boolean isFinal() {
        return (rawFlags & 0x0010) != 0;
    }

    public boolean isAbstract() {
        return (rawFlags & 0x0400) != 0;
    }

    public boolean isSynthetic() {
        return (rawFlags & 0x1000) != 0;
    }

    public Visibility visibility() {
        if (isPublic()) return Visibility.PUBLIC;
        if (isProtected()) return Visibility.PROTECTED;
        if (isPrivate()) return Visibility.PRIVATE;
        return Visibility.PACKAGE_PRIVATE;
    }

    @Override
    public String toString() {
        return "AccessFlags[" + Integer.toHexString(rawFlags) + "]";
    }
}
