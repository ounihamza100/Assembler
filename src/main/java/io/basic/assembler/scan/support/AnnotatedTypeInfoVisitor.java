package io.basic.assembler.scan.support;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;

import java.util.HashSet;
import java.util.Set;

/**
 * Discovers the annotation names of a class.
 *
 * @author Hamza Ouni
 */
public class AnnotatedTypeInfoVisitor extends ClassVisitor implements AnnotatedTypeInfo {
    private Set<String> annotations = new HashSet<>();
    private String className;
    private boolean isAbstract;

    /**
     * Default constructor.
     */
    public AnnotatedTypeInfoVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String supername, String[] interfaces) {
        this.className = ClassUtils.pathToClassName(name);
        boolean isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
        boolean isAnnotation = ((access & Opcodes.ACC_ANNOTATION) != 0);
        this.isAbstract = isInterface || isAnnotation || ((access & Opcodes.ACC_ABSTRACT) != 0);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        annotations.add(className);
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public String getTypeName() {
        return className;
    }

    @Override
    public boolean hasAnnotation(String annotationName) {
        return annotations.contains(annotationName);
    }
}
