package io.basic.assembler.scan;

/**
 * Abstraction to load resources from the file system.
 *
 * @author Hamza Ouni
 */
public interface FileSystemResourceLoader extends ResourceLoader {
    /**
     * Does the loader support a prefix.
     * @param path The path to a resource including a prefix
     *             appended by a colon. Ex (classpath:, file:)
     * @return boolean
     */
    default boolean supportsPrefix(String path) {
        return path.startsWith("file:");
    }
}
