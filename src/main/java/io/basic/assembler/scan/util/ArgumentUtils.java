package io.basic.assembler.scan.util;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Utility methods for checking method argument values.
 *
 * @author Hamza Ouni
 */
public class ArgumentUtils {

    /**
     * Adds a check that the given number is positive.
     *
     * @param name The name of the argument
     * @param value The value
     * @throws IllegalArgumentException if the argument is not positive
     * @return The value
     */
    public static @NonNull Number requirePositive(String name, Number value) {
        requireNonNull(name, value);
        requirePositive(name, value.intValue());
        return value;
    }

    /**
     * Adds a check that the given number is not null.
     *
     * @param name The name of the argument
     * @param value The value
     * @param <T> The generic type
     * @throws NullPointerException if the argument is null
     * @return The value
     */
    public static <T> T requireNonNull(String name, T value) {
        if (value == null) {
            throw new NullPointerException("Argument [" + name + "] cannot be null");
        }
        return value;
    }

    /**
     * Adds a check that the given number is positive.
     *
     * @param name The name of the argument
     * @param value The value
     * @throws IllegalArgumentException if the argument is not positive
     * @return The value
     */
    public static int requirePositive(String name, int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Argument [" + name + "] cannot be negative");
        }
        return value;
    }

    /**
     * Perform a check on an argument.
     *
     * @param check The check
     * @return The {@link ArgumentCheck}
     */
    public static ArgumentCheck check(Check check) {
        return new ArgumentCheck(check);
    }

    /**
     * Perform a check on an argument.
     *
     * @param name  The name of the argument
     * @param value The value of the argument
     * @param <T>   The value type
     * @return The {@link ArgumentCheck}
     */
    public static <T> ArgumentCheck check(String name, T value) {
        return new ArgumentCheck<>(name, value);
    }

    /**
     * Allows producing error messages.
     *
     * @param <T> The type
     */
    public static class ArgumentCheck<T> {
        private final Check check;
        private final String name;
        private final T value;

        /**
         * @param check The check
         */
        public ArgumentCheck(Check check) {
            this.check = check;
            this.name = null;
            this.value = null;
        }

        /**
         * @param name  The name
         * @param value The value
         */
        public ArgumentCheck(String name, T value) {
            this.check = null;
            this.name = name;
            this.value = value;
        }

        /**
         * Fail the argument with the given message.
         *
         * @param message The message
         * @throws IllegalArgumentException Thrown with the given message if the check fails
         */
        public void orElseFail(String message) {
            if (check != null && !check.condition()) {
                throw new IllegalArgumentException(message);
            }
        }

        /**
         * Fail the argument with the given message.
         *
         * @throws NullPointerException Thrown with the given message if the check fails
         */
        public void notNull() {
            if (value == null) {
                throw new NullPointerException("Argument [" + name + "] cannot be null");
            }
        }
    }

    /**
     * Functional interface the check a condition.
     */
    @FunctionalInterface
    public interface Check {

        /**
         * @return Whether the condition is true
         */
        boolean condition();
    }
}
