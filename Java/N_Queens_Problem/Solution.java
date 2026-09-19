import java.util.Scanner;

public class Solution {
    private static int size;
    private static int count;
    private static int[] placement;
    private static int[] firstSolution;
    private static boolean[] columns;
    private static boolean[] diagonals;
    private static boolean[] antiDiagonals;

    private static void place(int row) {
        if (row == size) {
            count++;
            if (firstSolution == null) {
                firstSolution = placement.clone();
            }
            return;
        }
        for (int col = 0; col < size; col++) {
            int diagonal = row - col + size - 1;
            int antiDiagonal = row + col;
            if (columns[col] || diagonals[diagonal] || antiDiagonals[antiDiagonal]) {
                continue;
            }
            columns[col] = true;
            diagonals[diagonal] = true;
            antiDiagonals[antiDiagonal] = true;
            placement[row] = col;
            place(row + 1);
            columns[col] = false;
            diagonals[diagonal] = false;
            antiDiagonals[antiDiagonal] = false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input");
            return;
        }
        size = scanner.nextInt();
        if (size < 1 || size > 12) {
            System.out.println("Invalid input");
            return;
        }
        placement = new int[size];
        columns = new boolean[size];
        diagonals = new boolean[2 * size - 1];
        antiDiagonals = new boolean[2 * size - 1];
        place(0);
        System.out.println("Total solutions: " + count);
        if (count > 0) {
            System.out.println("First solution:");
            for (int col : firstSolution) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    line.append(i == col ? 'Q' : '.');
                }
                System.out.println(line);
            }
        }
    }
}
