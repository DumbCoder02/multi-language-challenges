import java.util.Scanner;

public class Solution {
    private static boolean isPalindrome(String text) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        String forward = cleaned.toString();
        String backward = new StringBuilder(forward).reverse().toString();
        return forward.equals(backward);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.hasNextLine() ? scanner.nextLine() : "";
        if (isPalindrome(text)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not a palindrome");
        }
    }
}
