package calculator;

import java.util.Scanner;

class Application {
    private static final String ACTION_EXIT = "/exit";
    private static final String ACTION_HELP = "/help";

    private final Calculator calculator;

    Application() {
        calculator = new PostfixCalculator();
    }

    void menu() {
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
        System.out.println("The program calculates the sum and difference of numbers.");
    }
}

