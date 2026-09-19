import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    private static final int[][] WIN_LINES = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
        {0, 4, 8}, {2, 4, 6}
    };

    private static void printBoard(char[] board) {
        System.out.println();
        for (int row = 0; row < 3; row++) {
            System.out.println(" " + board[row * 3] + " | " + board[row * 3 + 1] + " | " + board[row * 3 + 2]);
            if (row < 2) {
                System.out.println("---+---+---");
            }
        }
    }

    private static boolean hasWon(char[] board, char mark) {
        for (int[] line : WIN_LINES) {
            if (board[line[0]] == mark && board[line[1]] == mark && board[line[2]] == mark) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumber(String text) {
        return text.matches("-?\\d{1,9}");
    }

    private static int readMove(Scanner scanner, char[] board, char player) {
        while (true) {
            System.out.print("Player " + player + ", enter row and column (1-3): ");
            if (!scanner.hasNextLine()) {
                return -1;
            }
            String[] parts = scanner.nextLine().trim().split("\\s+");
            if (parts.length != 2 || !isNumber(parts[0]) || !isNumber(parts[1])) {
                System.out.println("Invalid input. Enter two numbers between 1 and 3.");
                continue;
            }
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Invalid input. Enter two numbers between 1 and 3.");
                continue;
            }
            int index = (row - 1) * 3 + (col - 1);
            if (board[index] != '.') {
                System.out.println("That cell is already taken.");
                continue;
            }
            return index;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] board = new char[9];
        Arrays.fill(board, '.');
        char player = 'X';
        for (int turn = 0; turn < 9; turn++) {
            printBoard(board);
            int index = readMove(scanner, board, player);
            if (index < 0) {
                return;
            }
            board[index] = player;
            if (hasWon(board, player)) {
                printBoard(board);
                System.out.println("Player " + player + " wins!");
                return;
            }
            player = (player == 'X') ? 'O' : 'X';
        }
        printBoard(board);
        System.out.println("It's a draw!");
    }
}
