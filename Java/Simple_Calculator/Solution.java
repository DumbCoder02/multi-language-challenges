import java.util.Locale;
import java.util.Scanner;

public class Solution {
    private static double calculate(double first, char operator, double second) {
        switch (operator) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            default:
                return first / second;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            System.out.println("Error: Invalid input");
            return;
        }
        String[] parts = scanner.nextLine().trim().split("\\s+");
        if (parts.length != 3) {
            System.out.println("Error: Invalid input");
            return;
        }
        double first;
        double second;
        try {
            first = Double.parseDouble(parts[0]);
            second = Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input");
            return;
        }
        String symbol = parts[1];
        if (!symbol.equals("+") && !symbol.equals("-") && !symbol.equals("*") && !symbol.equals("/")) {
            System.out.println("Error: Invalid operator");
            return;
        }
        char operator = symbol.charAt(0);
        if (operator == '/' && second == 0) {
            System.out.println("Error: Division by zero");
            return;
        }
        System.out.printf(Locale.US, "%.2f%n", calculate(first, operator, second));
    }
}
