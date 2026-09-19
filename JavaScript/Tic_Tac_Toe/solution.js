const readline = require('readline');

const WIN_LINES = [
    [0, 1, 2], [3, 4, 5], [6, 7, 8],
    [0, 3, 6], [1, 4, 7], [2, 5, 8],
    [0, 4, 8], [2, 4, 6]
];

const board = Array(9).fill('.');
let player = 'X';
let moves = 0;
let finished = false;

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

function printBoard() {
    console.log();
    for (let row = 0; row < 3; row++) {
        console.log(' ' + board.slice(row * 3, row * 3 + 3).join(' | '));
        if (row < 2) {
            console.log('---+---+---');
        }
    }
}

function hasWon(mark) {
    return WIN_LINES.some(line => line.every(i => board[i] === mark));
}

function askForMove() {
    printBoard();
    rl.setPrompt('Player ' + player + ', enter row and column (1-3): ');
    rl.prompt();
}

function retry(message) {
    console.log(message);
    rl.prompt();
}

function finish(message) {
    finished = true;
    printBoard();
    console.log(message);
    rl.close();
}

rl.on('line', line => {
    if (finished) {
        return;
    }
    const parts = line.trim().split(/\s+/);
    const valid = parts.length === 2 && parts.every(part => /^\d+$/.test(part));
    const row = Number(parts[0]);
    const col = Number(parts[1]);
    if (!valid || row < 1 || row > 3 || col < 1 || col > 3) {
        retry('Invalid input. Enter two numbers between 1 and 3.');
        return;
    }
    const index = (row - 1) * 3 + (col - 1);
    if (board[index] !== '.') {
        retry('That cell is already taken.');
        return;
    }
    board[index] = player;
    moves++;
    if (hasWon(player)) {
        finish('Player ' + player + ' wins!');
        return;
    }
    if (moves === 9) {
        finish("It's a draw!");
        return;
    }
    player = player === 'X' ? 'O' : 'X';
    askForMove();
});

askForMove();
