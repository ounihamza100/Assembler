package io.basic.assembler.scan;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Loads resources from the file system.
 *
 * @author Hamza Ouni
 */
public class DefaultFileSystemResourceLoader implements FileSystemResourceLoader {

    private final Optional<File> baseDir;

    /**
     * Default constructor.
     */
    public DefaultFileSystemResourceLoader() {
        this.baseDir = Optional.empty();
    }

    /**
     * @param baseDir The base directory
     */
    public DefaultFileSystemResourceLoader(File baseDir) {
        this.baseDir = Optional.of(baseDir);
    }

    /**
     * @param path The path
     */
    public DefaultFileSystemResourceLoader(String path) {
        this.baseDir = Optional.of(new File(normalize(path)));
    }


    @Override
    public Optional<InputStream> getResourceAsStream(String path) {
        File file = getFile(normalize(path));
        try {
            return Optional.of(Files.newInputStream(file.toPath()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<URL> getResource(String path) {
        File file = getFile(normalize(path));
        if (file.exists() && file.canRead() && !file.isDirectory()) {
            try {
                URL url = file.toURI().toURL();
                return Optional.of(url);
            } catch (MalformedURLException e) {
            }
        }
        return Optional.empty();
    }

    @Override
    public Stream<URL> getResources(String name) {
        throw new UnsupportedOperationException(getClass().getName() + " does not support retrieving a stream of resources");
    }

    /**
     * @param basePath The path to load resources
     * @return The resource loader
     */
    public ResourceLoader forBase(String basePath) {
        return new DefaultFileSystemResourceLoader(basePath);
    }

    @SuppressWarnings("MagicNumber")
    private static String normalize(String path) {
        if (path == null) {
            return null;
        }
        if (path.startsWith("file:")) {
            path = path.substring(5);
        }
        return path;
    }

    private File getFile(String path) {
        return baseDir.map(dir -> new File(dir, path)).orElse(new File(path));
    }

}
