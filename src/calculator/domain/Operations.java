package calculator.domain;

import java.math.BigInteger;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public enum Operations {
    POWER(5, "^", (a, b) -> a.pow(b.intValue())),
    MODULO(4, "%", BigInteger::mod),
    MULTIPLY(4, "*", BigInteger::multiply),
    DIVIDE(4, "/", BigInteger::divide),
    ADD(2, "+", BigInteger::add),
    SUBTRACT(2, "-", BigInteger::subtract);

    private final String symbol;
    private final BinaryOperator<BigInteger> operation;
    private final int priority;

    Operations(int priority, String symbol, BinaryOperator<BigInteger> operation) {
        this.symbol = symbol;
        this.operation = operation;
        this.priority = priority;
    }

    static Operations getOperation(String symbol) {
        return Stream.of(Operations.values()).filter(i -> i.getSymbol().equals(symbol)).findAny().orElseThrow();
    }

    String getSymbol() {
        return symbol;
    }

    int getPriority() {
        return priority;
    }

    BigInteger calculate(BigInteger a, BigInteger b) {
        return operation.apply(a, b);
    }
}
