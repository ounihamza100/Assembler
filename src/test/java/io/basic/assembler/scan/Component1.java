package io.basic.assembler.scan;

import groovy.transform.AutoClone;
import io.basic.assembler.scan.annotation.Component;
import jdk.nashorn.internal.runtime.logging.Logger;

/**
 * @author Hamza Ouni
 */
@Component
public class Component1 {
    public Component1() {
        System.out.println("component1 loding...");
    }
}
