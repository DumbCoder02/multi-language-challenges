def print_tasks(tasks):
    if not tasks:
        print('No tasks.')
        return
    for number, task in enumerate(tasks, start=1):
        mark = 'x' if task['done'] else ' '
        print(f'{number}. [{mark}] {task["title"]}')


def parse_index(text, size):
    try:
        number = int(text)
    except ValueError:
        return None
    if number < 1 or number > size:
        return None
    return number - 1


def main():
    tasks = []
    print('Commands: add <task>, list, done <number>, remove <number>, quit')
    while True:
        try:
            line = input().strip()
        except EOFError:
            break
        if not line:
            continue
        parts = line.split(None, 1)
        command = parts[0].lower()
        argument = parts[1].strip() if len(parts) > 1 else ''
        if command == 'add':
            if not argument:
                print('Usage: add <task>')
            else:
                tasks.append({'title': argument, 'done': False})
                print(f'Added: {argument}')
        elif command == 'list':
            print_tasks(tasks)
        elif command == 'done':
            index = parse_index(argument, len(tasks))
            if index is None:
                print('Invalid task number.')
            else:
                tasks[index]['done'] = True
                print(f'Task {index + 1} marked as done.')
        elif command == 'remove':
            index = parse_index(argument, len(tasks))
            if index is None:
                print('Invalid task number.')
            else:
                removed = tasks.pop(index)
                print(f'Removed: {removed["title"]}')
        elif command == 'quit':
            print('Goodbye.')
            break
        else:
            print('Unknown command.')


if __name__ == '__main__':
    main()
