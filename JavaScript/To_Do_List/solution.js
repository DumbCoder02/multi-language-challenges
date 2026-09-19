const readline = require('readline');

const tasks = [];
let running = true;

function printTasks() {
    if (tasks.length === 0) {
        console.log('No tasks.');
        return;
    }
    tasks.forEach((task, index) => {
        const mark = task.done ? 'x' : ' ';
        console.log((index + 1) + '. [' + mark + '] ' + task.title);
    });
}

function parseIndex(text) {
    if (!/^\d+$/.test(text)) {
        return -1;
    }
    const number = parseInt(text, 10);
    if (number < 1 || number > tasks.length) {
        return -1;
    }
    return number - 1;
}

function handle(line) {
    const trimmed = line.trim();
    if (trimmed === '') {
        return;
    }
    const space = trimmed.search(/\s/);
    const command = (space === -1 ? trimmed : trimmed.slice(0, space)).toLowerCase();
    const argument = space === -1 ? '' : trimmed.slice(space).trim();
    switch (command) {
        case 'add':
            if (argument === '') {
                console.log('Usage: add <task>');
            } else {
                tasks.push({ title: argument, done: false });
                console.log('Added: ' + argument);
            }
            break;
        case 'list':
            printTasks();
            break;
        case 'done': {
            const index = parseIndex(argument);
            if (index < 0) {
                console.log('Invalid task number.');
            } else {
                tasks[index].done = true;
                console.log('Task ' + (index + 1) + ' marked as done.');
            }
            break;
        }
        case 'remove': {
            const index = parseIndex(argument);
            if (index < 0) {
                console.log('Invalid task number.');
            } else {
                const removed = tasks.splice(index, 1)[0];
                console.log('Removed: ' + removed.title);
            }
            break;
        }
        case 'quit':
            console.log('Goodbye.');
            running = false;
            break;
        default:
            console.log('Unknown command.');
    }
}

console.log('Commands: add <task>, list, done <number>, remove <number>, quit');

const rl = readline.createInterface({ input: process.stdin });

rl.on('line', line => {
    if (!running) {
        return;
    }
    handle(line);
    if (!running) {
        rl.close();
    }
});
