package io.basic.assembler.scan.support;

/**
 * An interface that provides an abstraction for checking for the existence of annotations.
 *
 * @author Hamza Ouni
 */
public interface AnnotatedTypeInfo {

    /**
     * @return Is the type abstract
     */
    boolean isAbstract();

    /**
     * @return The name of the annotated type
     */
    String getTypeName();

    /**
     * Checks whether an annotation for the given name exists.
     *
     * @param annotationName The annotation name
     * @return True if it does
     */
    boolean hasAnnotation(String annotationName);
}
