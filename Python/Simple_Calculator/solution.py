def calculate(first, operator, second):
    if operator == '+':
        return first + second
    if operator == '-':
        return first - second
    if operator == '*':
        return first * second
    return first / second


def main():
    parts = input().split()
    if len(parts) != 3:
        print('Error: Invalid input')
        return
    try:
        first = float(parts[0])
        second = float(parts[2])
    except ValueError:
        print('Error: Invalid input')
        return
    operator = parts[1]
    if operator not in ('+', '-', '*', '/'):
        print('Error: Invalid operator')
        return
    if operator == '/' and second == 0:
        print('Error: Division by zero')
        return
    print(f'{calculate(first, operator, second):.2f}')


if __name__ == '__main__':
    main()
