package calculator.domain;

import calculator.validation.ExpressionValidation;
import calculator.validation.Validation;

import java.math.BigInteger;
import java.util.*;

import static calculator.domain.Operations.getOperation;

public class PostfixCalculator implements Calculator {
    private static final String TOKENS =
            "(?<=[-+]?\\w)(?=[-+/*^])|(?<=[^-+/*^][-+/*^])(?=[-+]?\\w)|(?=[()])|(?<=[()])";

    private final Validation validation;
    private final Map<String, BigInteger> variables;

    private List<String> postfix;

    public PostfixCalculator() {
        variables = new HashMap<>();
        validation = new ExpressionValidation(variables);
    }

    @Override
    public boolean setExpression(final String expression) {

        final var isCorrectExpression =
                validation.checkExpression(expression)
                && validation.checkParentheses(expression)
                && validation.checkVariables(expression);

        if (isCorrectExpression) {
            toPostfix(splitIntoTokens(expression));
        }

        return isCorrectExpression;
    }

    @Override
    public BigInteger calculate() {
        final var stack = new ArrayDeque<BigInteger>();

        for (final var element : postfix) {
            final var isOperator = OPERATOR.matcher(element).matches();
            if (isOperator) {
                var b = stack.pop();
                var a = stack.pop();
                stack.push(getOperation(element).calculate(a, b));
            } else {
                stack.push(parseOperand(element));
            }
        }
        return stack.pop();
    }

    private BigInteger parseOperand(String operand) {
        final var id = operand.replaceAll("[^\\p{Alpha}]", "");
        if (!id.isBlank()) {
            operand = operand.replace(id, variables.get(id).toString());
        }
        return new BigInteger(operand.replaceAll("[\\s+]+", "").replaceAll("--", ""));
    }

    @Override
    public void doAssignment(final String line) {
        if (!validation.checkAssignment(line)) {
            return;
        }
        final var parts = line.split("=", 2);
        final var identifier = parts[0].trim();
        final var expression = parts[1].trim();

        if (validation.checkIdentifier(identifier) && setExpression(expression)) {
            variables.put(identifier, calculate());
        }
    }

    private String[] splitIntoTokens(String expression) {
        return expression
                .replaceAll("--", "+")
                .replaceAll("\\+\\++", "+")
                .replaceAll("\\+-", "-")
                .replaceAll("\\s+", "")
                .split(TOKENS);
    }

    private void toPostfix(String[] infix) {
        postfix = new LinkedList<>();
        final var stack = new ArrayDeque<String>();

        for (var element : infix) {
            final var isOperand = !element.matches("[-+/%*^()]");

            if (isOperand) {
                postfix.add(element);
                continue;
            }

            if (LEFT_PARENTHESIS.equals(element)) {
                stack.push(element);
                continue;
            }

            if (RIGHT_PARENTHESIS.equals(element)) {
                while (!LEFT_PARENTHESIS.equals(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.pop();
                continue;
            }

            if (stack.isEmpty() || LEFT_PARENTHESIS.equals(stack.peek())) {
                stack.push(element);
                continue;
            }

            if (getOperation(element).getPriority() > getOperation(stack.peek()).getPriority()) {
                stack.push(element);
                continue;
            }

            while (!stack.isEmpty() && !stack.peek().equals(LEFT_PARENTHESIS)
                    && getOperation(element).getPriority() <= getOperation(stack.peek()).getPriority()) {
                postfix.add(stack.pop());
            }

            stack.push(element);
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }
    }
}
