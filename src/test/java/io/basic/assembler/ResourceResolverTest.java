package io.basic.assembler;

import io.basic.assembler.scan.ResourceResolver;
import io.basic.assembler.scan.loader.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Hamza Ouni
 */
public class ResourceResolverTest {

    ResourceResolver resolver;

    @BeforeEach
    public void setUp() {
        resolver = new ResourceResolver();
    }

    @Test
    public void getLoader_ClassPathResourceLoader_test() {
        ResourceLoader r1 = resolver.getLoader(ClassPathResourceLoader.class).get();
        boolean flag = r1 instanceof DefaultClassPathResourceLoader;
        Assertions.assertEquals(flag, true);
    }

    @Test
    public void getLoader_FileSystemResourceLoader_test() {
        ResourceLoader r1 = resolver.getLoader(FileSystemResourceLoader.class).get();
        boolean flag = r1 instanceof DefaultFileSystemResourceLoader;
        Assertions.assertEquals(flag, true);
    }

    @Test
    public void getLoader_getSupportingClassPathResourceLoader_test() {
        ResourceLoader r1 = resolver.getSupportingLoader("classpath:foo").get();
        boolean flag = r1 instanceof DefaultClassPathResourceLoader;
        Assertions.assertEquals(flag, true);
    }

    @Test
    public void getLoader_getSupportingFileSystemResourceLoader_test() {
        ResourceLoader r1 = resolver.getSupportingLoader("file:foo").get();
        boolean flag = r1 instanceof DefaultFileSystemResourceLoader;
        Assertions.assertEquals(flag, true);
    }


}
