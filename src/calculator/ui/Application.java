package calculator.ui;

import calculator.domain.Calculator;
import calculator.domain.PostfixCalculator;
import calculator.validation.Validation;

import java.util.Scanner;

import static java.lang.String.join;

public class Application {
    private static final String ACTION_EXIT = "/exit";
    private static final String ACTION_HELP = "/help";

    private final Calculator calculator;

    public Application() {
        calculator = new PostfixCalculator();
    }

    public void menu() {
        final var sc = new Scanner(System.in);

        while (true) {
            final var line = sc.nextLine().trim().replaceAll("\\s+", " ");

            if (line.isBlank()) {
                continue;
            }
            if (ACTION_EXIT.equals(line)) {
                break;
            }
            if (ACTION_HELP.equals(line)) {
                printHelp();
                continue;
            }
            if (line.charAt(0) == '/') {
                System.out.println(Validation.ERR_UNKNOWN_COMMAND);
                continue;
            }
            if (line.contains("=")) {
                calculator.doAssignment(line);
                continue;
            }
            if (calculator.setExpression(line)) {
                System.out.println(calculator.calculate());
            }
        }
        System.out.println("Bye!");
        sc.close();
    }

    void printHelp() {
        System.out.println(join("\n",
                "The program calculates the arithmetic expressions for big numbers.",
                "The program support variables assigment and use.",
                "Supported operations: + - * / % ^",
                "Supported commands:",
                "/help - shows this help",
                "/exit - exit the program."
        ));
    }
}

