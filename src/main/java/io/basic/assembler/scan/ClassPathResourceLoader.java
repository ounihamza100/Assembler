package io.basic.assembler.scan;

/**
 * Abstraction to load resources from the the classpath.
 *
 * @author Hamza Ouni
 */
public interface ClassPathResourceLoader extends ResourceLoader {
    /**
     * @return The underlying classloader used by this {@link ClassPathResourceLoader}
     */
    ClassLoader getClassLoader();

    /**
     * @param path The path to a resource including a prefix
     *             appended by a colon. Ex (classpath:, file:)
     * @return Whether the given resource loader supports the prefix
     */
    @Override
    default boolean supportsPrefix(String path) {
        return path.startsWith("classpath:");
    }

    /**
     * Return the default {@link ClassPathResourceLoader} for the given class loader.
     *
     * @param classLoader The classloader
     * @return The default loader
     */
    static ClassPathResourceLoader defaultLoader(ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        if (classLoader == null) {
            classLoader = ClassPathResourceLoader.class.getClassLoader();
        }
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return new DefaultClassPathResourceLoader(classLoader);
    }
}
