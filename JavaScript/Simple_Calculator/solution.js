const readline = require('readline');

function calculate(first, operator, second) {
    switch (operator) {
        case '+':
            return first + second;
        case '-':
            return first - second;
        case '*':
            return first * second;
        default:
            return first / second;
    }
}

function evaluate(line) {
    const parts = line.trim().split(/\s+/);
    if (parts.length !== 3) {
        return 'Error: Invalid input';
    }
    const first = Number(parts[0]);
    const second = Number(parts[2]);
    if (Number.isNaN(first) || Number.isNaN(second)) {
        return 'Error: Invalid input';
    }
    const operator = parts[1];
    if (!['+', '-', '*', '/'].includes(operator)) {
        return 'Error: Invalid operator';
    }
    if (operator === '/' && second === 0) {
        return 'Error: Division by zero';
    }
    return calculate(first, operator, second).toFixed(2);
}

const rl = readline.createInterface({ input: process.stdin });

rl.once('line', line => {
    console.log(evaluate(line));
    rl.close();
});
