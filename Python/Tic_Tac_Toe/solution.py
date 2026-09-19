WIN_LINES = [
    (0, 1, 2), (3, 4, 5), (6, 7, 8),
    (0, 3, 6), (1, 4, 7), (2, 5, 8),
    (0, 4, 8), (2, 4, 6),
]


def print_board(board):
    rows = []
    for row in range(3):
        rows.append(' ' + ' | '.join(board[row * 3:row * 3 + 3]))
    print('\n' + '\n---+---+---\n'.join(rows))


def has_won(board, mark):
    return any(all(board[i] == mark for i in line) for line in WIN_LINES)


def read_move(board, player):
    while True:
        try:
            raw = input(f'Player {player}, enter row and column (1-3): ')
        except EOFError:
            return None
        try:
            row, col = map(int, raw.split())
        except ValueError:
            print('Invalid input. Enter two numbers between 1 and 3.')
            continue
        if not (1 <= row <= 3 and 1 <= col <= 3):
            print('Invalid input. Enter two numbers between 1 and 3.')
            continue
        index = (row - 1) * 3 + (col - 1)
        if board[index] != '.':
            print('That cell is already taken.')
            continue
        return index


def main():
    board = ['.'] * 9
    player = 'X'
    for _ in range(9):
        print_board(board)
        index = read_move(board, player)
        if index is None:
            return
        board[index] = player
        if has_won(board, player):
            print_board(board)
            print(f'Player {player} wins!')
            return
        player = 'O' if player == 'X' else 'X'
    print_board(board)
    print("It's a draw!")


if __name__ == '__main__':
    main()
