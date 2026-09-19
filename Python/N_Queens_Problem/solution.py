def solve(size):
    columns = set()
    diagonals = set()
    anti_diagonals = set()
    placement = []
    first = []
    count = 0

    def place(row):
        nonlocal count
        if row == size:
            count += 1
            if not first:
                first.extend(placement)
            return
        for col in range(size):
            if col in columns or (row - col) in diagonals or (row + col) in anti_diagonals:
                continue
            columns.add(col)
            diagonals.add(row - col)
            anti_diagonals.add(row + col)
            placement.append(col)
            place(row + 1)
            placement.pop()
            columns.remove(col)
            diagonals.remove(row - col)
            anti_diagonals.remove(row + col)

    place(0)
    return count, first


def main():
    try:
        size = int(input().strip())
    except ValueError:
        print('Invalid input')
        return
    if size < 1 or size > 12:
        print('Invalid input')
        return
    count, first = solve(size)
    print('Total solutions:', count)
    if count > 0:
        print('First solution:')
        for col in first:
            print('.' * col + 'Q' + '.' * (size - col - 1))


if __name__ == '__main__':
    main()
