package io.basic.assembler.scan;

import groovy.transform.AutoClone;
import io.basic.assembler.scan.annotation.Component;
import jdk.nashorn.internal.runtime.logging.Logger;

/**
 * @author Hamza Ouni
 */
@Component
public class Component2 {

    public Component2() {
        System.out.println("component2 loding...");
    }
}
