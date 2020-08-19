package io.basic.assembler;

import io.basic.assembler.scan.ClassPathResourceLoader;
import io.basic.assembler.scan.DefaultClassPathResourceLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Hamza Ouni
 */
public class ClasspathResourceLoaderTest {

    ClassPathResourceLoader loader;

//    @BeforeEach
//    public void setUp() {
//        loader = new DefaultClassPathResourceLoader(getClass().getClassLoader(), null);
//    }

    @Test
    public void getResource_test() throws IOException {
        loader = new DefaultClassPathResourceLoader(getClass().getClassLoader(), null);
        URL url = loader.getResource("foo/bar.txt").get();
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        Assertions.assertEquals("bar.txt",sb.toString());
    }

    @Test
    public void getResource_test2() throws IOException {
        loader = new DefaultClassPathResourceLoader(getClass().getClassLoader(), null);
        URL url = loader.getResource("classpath:foo/bar.txt").get();
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        Assertions.assertEquals("bar.txt",sb.toString());
    }

    @Test
    public void getResource_test3() throws IOException {
        loader = new DefaultClassPathResourceLoader(getClass().getClassLoader(), "foo");
        URL url = loader.getResource("classpath:foo/bar.txt").get();
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        Assertions.assertEquals("bar.txt",sb.toString());
    }

    @Test
    public void getResource_test4() throws IOException {
        loader = new DefaultClassPathResourceLoader(getClass().getClassLoader(), "/foo");
        URL url = loader.getResource("classpath:foo/bar.txt").get();
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        Assertions.assertEquals("bar.txt",sb.toString());
    }

    @Test
    public void getResource_test5() throws IOException {
        loader = new DefaultClassPathResourceLoader(getClass().getClassLoader(), "classpath:foo");
        URL url = loader.getResource("bar.txt").get();
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        Assertions.assertEquals("bar.txt",sb.toString());
    }

    @Test
    public void getResource_test6() throws IOException {
        loader = new DefaultClassPathResourceLoader(getClass().getClassLoader(), "classpath:foo");
        URL url = loader.getResource("classpath:bar.txt").get();
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        Assertions.assertEquals("bar.txt",sb.toString());
    }

    @Test
    public void getResource_test7() throws IOException {
        loader = new DefaultClassPathResourceLoader(getClass().getClassLoader(), "classpath:/foo");
        URL url = loader.getResource("classpath:bar.txt").get();
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();

        Assertions.assertEquals("bar.txt",sb.toString());
    }
}
