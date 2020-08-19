package io.basic.assembler;

import groovy.transform.AutoClone;
import io.basic.assembler.scan.annotation.Component;
import io.basic.assembler.scan.support.ClassPathAnnotationScanner;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Hamza Ouni
 */
public class ClassPathAnnotationScannerTest {

    ClassPathAnnotationScanner scanner;

    @BeforeEach
    public void setUp() {
        scanner = new ClassPathAnnotationScanner();
    }

    @Test
    public void scan_test() {
        Stream<Class> classes = scanner.scan(Component.class,getClass().getPackage().getName());
        List<Class> result =  classes.collect(Collectors.toList());

        assertEquals(2, result.size());
    }
}
