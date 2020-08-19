package io.basic.assembler.scan.support;

import jdk.internal.org.objectweb.asm.Label;

/**
 *  A non standard class, field, method or code attribute.
 *
 * @author Hamza Ouni
 */
public class Attribute {
    /**
     * The type of this attribute.
     */
    final String type;

    /**
     * The raw value of this attribute, used only for unknown attributes.
     */
    byte[] value;

    /**
     * The next attribute in this attribute list. May be <tt>null</tt>.
     */
    Attribute next;

    /**
     * Constructs a new empty attribute.
     *
     * @param type the type of the attribute.
     */
    protected Attribute(final String type) {
        this.type = type;
    }


    /**
     * Reads a {@link #type type} attribute. This method must return a
     * <i>new</i> {@link Attribute} object, of type {@link #type type},
     * corresponding to the <tt>len</tt> bytes starting at the given offset, in
     * the given class reader.
     *
     * @param cr      the class that contains the attribute to be read.
     * @param off     index of the first byte of the attribute's content in
     *                { org.objectweb.asm.ClassReader#b cr.b}. The 6 attribute header bytes,
     *                containing the type and the length of the attribute, are not
     *                taken into account here.
     * @param len     the length of the attribute's content.
     * @param buf     buffer to be used to call { org.objectweb.asm.ClassReader#readUTF8
     *                readUTF8}, { org.objectweb.asm.ClassReader#readClass(int, char[]) readClass}
     *                or {org.objectweb.asm.ClassReader#readConst readConst}.
     * @param codeOff index of the first byte of code's attribute content in
     *                { org.objectweb.asm.ClassReader#b cr.b}, or -1 if the attribute to be read
     *                is not a code attribute. The 6 attribute header bytes,
     *                containing the type and the length of the attribute, are not
     *                taken into account here.
     * @param labels  the labels of the method's code, or <tt>null</tt> if the
     *                attribute to be read is not a code attribute.
     * @return a <i>new</i> {@link Attribute} object corresponding to the given
     * bytes.
     */
    protected Attribute read(final AnnotationClassReader cr, final int off,
                             final int len, final char[] buf, final int codeOff,
                             final Label[] labels) {
        Attribute attr = new Attribute(type);
        attr.value = new byte[len];
        System.arraycopy(cr.b, off, attr.value, 0, len);
        return attr;
    }
}
