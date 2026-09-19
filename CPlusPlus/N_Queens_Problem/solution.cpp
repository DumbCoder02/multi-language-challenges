#include <iostream>
#include <string>
#include <vector>

class NQueens {
public:
    explicit NQueens(int n)
        : size(n),
          count(0),
          placement(n),
          columns(n, false),
          diagonals(2 * n - 1, false),
          antiDiagonals(2 * n - 1, false) {}

    void solve() {
        place(0);
    }

    int getCount() const {
        return count;
    }

    const std::vector<int>& getFirst() const {
        return first;
    }

private:
    int size;
    int count;
    std::vector<int> placement;
    std::vector<int> first;
    std::vector<bool> columns;
    std::vector<bool> diagonals;
    std::vector<bool> antiDiagonals;

    void place(int row) {
        if (row == size) {
            ++count;
            if (first.empty()) {
                first = placement;
            }
            return;
        }
        for (int col = 0; col < size; ++col) {
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
};

int main() {
    int size = 0;
    if (!(std::cin >> size) || size < 1 || size > 12) {
        std::cout << "Invalid input\n";
        return 0;
    }
    NQueens solver(size);
    solver.solve();
    std::cout << "Total solutions: " << solver.getCount() << '\n';
    if (solver.getCount() > 0) {
        std::cout << "First solution:\n";
        for (int col : solver.getFirst()) {
            std::string row(size, '.');
            row[col] = 'Q';
            std::cout << row << '\n';
        }
    }
    return 0;
}
