package com.mySampleApplication.client;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BinaryOperator;

/**
 * Calculator service.
 * Performs binary! operation on given numbers.
 * Provides method calculate.
 */
class Calculator {
    private Map<String, BinaryOperator<Double>> operations = new TreeMap<String, BinaryOperator<Double>>() {{
        put("+", (a, b) -> a + b);
        put("-", (a, b) -> a - b);
        put("/", (a, b) -> a / b);
        put("*", (a, b) -> a * b);
        put("%", (a, b) -> a % b);
        put("//", (a, b) -> (double) (a.intValue() / b.intValue()));
        put("^", Math::pow);
    }};

    /**
     * performs given binary operation on two given numbers
     * @param operation - string representation of binary operation to apply
     * @param v1 - left-side argument
     * @param v2 - right-side argument
     * @return - the result of operation
     */
    Double calculate(String operation, double v1, double v2) {
        return operations.get(operation).apply(v1, v2);
    }

    /**
     * list supported operations as strings
     * @return set of supported operations
     */
    Set<String> supportedOperations() {
        return operations.keySet();
    }
}
