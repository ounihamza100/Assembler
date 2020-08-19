package io.basic.assembler.scan.support;

/**
 * @author Hamza Ouni
 */
class Context {

    /**
     * Prototypes of the attributes that must be parsed for this class.
     */
    Attribute[] attrs;

    /**
     * The {ClassReader} option flags for the parsing of this class.
     */
    int flags;

    /**
     * The buffer used to read strings.
     */
    char[] buffer;

    /**
     * The start index of each bootstrap method.
     */
    int[] bootstrapMethods;
}
