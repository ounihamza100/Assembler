package io.basic.assembler.scan.annotation;

import java.lang.annotation.*;

/**
 * Annotation used to Define a component.
 *
 * @author Hamza Ouni
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
}
