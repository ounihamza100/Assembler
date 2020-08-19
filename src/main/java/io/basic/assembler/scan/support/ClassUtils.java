package io.basic.assembler.scan.support;

/**
 * @author Hamza Ouni
 */
public class ClassUtils {
    /**
     * Default extension for class files.
     */
    public static final String CLASS_EXTENSION = ".class";

    /**
     * <p>Converts a URI to a class file reference to the class name</p>.
     * <p>
     * <p>ie. ClassUtils.pathToClassName("foo/bar/MyClass.class") == "foo.bar.MyClass"</p>
     *
     * @param path The path name
     * @return The class name
     */
    public static String pathToClassName(String path) {
        path = path.replace('/', '.');
        if (path.endsWith(CLASS_EXTENSION)) {
            path = path.substring(0, path.length() - CLASS_EXTENSION.length());
        }
        return path;
    }
}
