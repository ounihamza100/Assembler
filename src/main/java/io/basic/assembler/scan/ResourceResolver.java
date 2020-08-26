package io.basic.assembler.scan;

import io.basic.assembler.scan.loader.ClassPathResourceLoader;
import io.basic.assembler.scan.loader.FileSystemResourceLoader;
import io.basic.assembler.scan.loader.ResourceLoader;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.basic.assembler.scan.util.ArgumentUtils;

/**
 * Resolves resources from a set of {@link ResourceLoader} instances.
 *
 * @author Hamza Ouni
 */
public class ResourceResolver {

    private final List<ResourceLoader> resourceLoaders;

    /**
     * @param resourceLoaders The resource loaders
     */
    public ResourceResolver(@NonNull ResourceLoader[] resourceLoaders) {
        this(Arrays.asList(resourceLoaders));
    }

    /**
     * @param resourceLoaders The resource loaders
     */
    public ResourceResolver(@NonNull List<ResourceLoader> resourceLoaders) {
        ArgumentUtils.requireNonNull("resourceLoaders", resourceLoaders);
        this.resourceLoaders = resourceLoaders;
    }

    /**
     * Default constructor.
     */
    public ResourceResolver() {
        this(new ResourceLoader[]{
                ClassPathResourceLoader.defaultLoader(ResourceResolver.class.getClassLoader()),
                FileSystemResourceLoader.defaultLoader()});
    }

    /**
     * Searches resource loaders for one that matches or is a subclass of the specified type.
     *
     * @param resolverType The type of resolver to retrieve
     * @param <T>          The type
     * @return An optional resource loader
     */
    public @NonNull <T extends ResourceLoader> Optional<T> getLoader(@NonNull Class<T> resolverType) {
        ArgumentUtils.requireNonNull("resolverType", resolverType);
        return resourceLoaders.stream()
                .filter(rl -> resolverType.isAssignableFrom(rl.getClass()))
                .map(rl -> (T) rl)
                .findFirst();
    }

    /**
     * Searches resource loaders for one that supports the given prefix.
     *
     * @param prefix The prefix the loader should support. (classpath:, file:, etc)
     * @return An optional resource loader
     */
    public @NonNull  Optional<ResourceLoader> getSupportingLoader(@NonNull String prefix) {
        ArgumentUtils.requireNonNull("prefix", prefix);
        return resourceLoaders.stream()
                .filter(rl -> rl.supportsPrefix(prefix))
                .findFirst();
    }

    /**
     * Searches resource loaders for one that supports the given path. If found, create a new loader with the
     * context of the base path.
     *
     * @param basePath The path to load resources from
     * @return An optional resource loader
     */
    public @NonNull Optional<ResourceLoader> getLoaderForBasePath(@NonNull String basePath) {
        ArgumentUtils.requireNonNull("basePath", basePath);
        Optional<ResourceLoader> resourceLoader = getSupportingLoader(basePath);
        return resourceLoader.map(rl -> rl.forBase(basePath));
    }

    /**
     * Searches resource loaders for one that supports the given path. If found, return the resource stream.
     *
     * @param path The path to the resource
     * @return An optional input stream
     */
    public @NonNull Optional<InputStream> getResourceAsStream(@NonNull String path) {
        ArgumentUtils.requireNonNull("path", path);
        Optional<ResourceLoader> resourceLoader = getSupportingLoader(path);
        if (resourceLoader.isPresent()) {
            return resourceLoader.get().getResourceAsStream(path);
        }
        return Optional.empty();
    }

    /**
     * Searches resource loaders for one that supports the given path. If found, return the resource URL.
     *
     * @param path The path to the resource
     * @return An optional URL
     */
    public @NonNull Optional<URL> getResource(@NonNull String path) {
        ArgumentUtils.requireNonNull("path", path);
        Optional<ResourceLoader> resourceLoader = getSupportingLoader(path);
        if (resourceLoader.isPresent()) {
            return resourceLoader.get().getResource(path);
        }
        return Optional.empty();
    }

    /**
     * Searches resource loaders for one that supports the given path. If found, return a stream of matching resources.
     *
     * @param path The path to the resource
     * @return A stream of URLs
     */
    public @NonNull
    Stream<URL> getResources(@NonNull String path) {
        ArgumentUtils.requireNonNull("path", path);
        Optional<ResourceLoader> resourceLoader = getSupportingLoader(path);
        if (resourceLoader.isPresent()) {
            return resourceLoader.get().getResources(path);
        }
        return Stream.empty();
    }
}
