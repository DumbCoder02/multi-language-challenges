#include <iostream>
#include <sstream>
#include <string>

const int WIN_LINES[8][3] = {
    {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
    {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
    {0, 4, 8}, {2, 4, 6}
};

void printBoard(const std::string& board) {
    std::cout << '\n';
    for (int row = 0; row < 3; ++row) {
        std::cout << ' ' << board[row * 3] << " | " << board[row * 3 + 1] << " | " << board[row * 3 + 2] << '\n';
        if (row < 2) {
            std::cout << "---+---+---\n";
        }
    }
}

bool hasWon(const std::string& board, char mark) {
    for (const auto& line : WIN_LINES) {
        if (board[line[0]] == mark && board[line[1]] == mark && board[line[2]] == mark) {
            return true;
        }
    }
    return false;
}

int readMove(const std::string& board, char player) {
    std::string line;
    while (true) {
        std::cout << "Player " << player << ", enter row and column (1-3): ";
        if (!std::getline(std::cin, line)) {
            return -1;
        }
        std::istringstream input(line);
        int row = 0;
        int col = 0;
        std::string extra;
        if (!(input >> row >> col) || (input >> extra) || row < 1 || row > 3 || col < 1 || col > 3) {
            std::cout << "Invalid input. Enter two numbers between 1 and 3.\n";
            continue;
        }
        int index = (row - 1) * 3 + (col - 1);
        if (board[index] != '.') {
            std::cout << "That cell is already taken.\n";
            continue;
        }
        return index;
    }
}

int main() {
    std::string board(9, '.');
    char player = 'X';
    for (int turn = 0; turn < 9; ++turn) {
        printBoard(board);
        int index = readMove(board, player);
        if (index < 0) {
            return 0;
        }
        board[index] = player;
        if (hasWon(board, player)) {
            printBoard(board);
            std::cout << "Player " << player << " wins!\n";
            return 0;
        }
        player = (player == 'X') ? 'O' : 'X';
    }
    printBoard(board);
    std::cout << "It's a draw!\n";
    return 0;
}
