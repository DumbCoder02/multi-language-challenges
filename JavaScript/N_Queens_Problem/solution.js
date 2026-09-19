const readline = require('readline');

function solve(size) {
    const columns = new Array(size).fill(false);
    const diagonals = new Array(2 * size - 1).fill(false);
    const antiDiagonals = new Array(2 * size - 1).fill(false);
    const placement = new Array(size).fill(0);
    let first = null;
    let count = 0;

    function place(row) {
        if (row === size) {
            count++;
            if (first === null) {
                first = placement.slice();
            }
            return;
        }
        for (let col = 0; col < size; col++) {
            const diagonal = row - col + size - 1;
            const antiDiagonal = row + col;
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

    place(0);
    return { count, first };
}

const rl = readline.createInterface({ input: process.stdin });

rl.once('line', line => {
    const size = parseInt(line.trim(), 10);
    if (Number.isNaN(size) || size < 1 || size > 12) {
        console.log('Invalid input');
    } else {
        const result = solve(size);
        console.log('Total solutions: ' + result.count);
        if (result.count > 0) {
            console.log('First solution:');
            result.first.forEach(col => {
                console.log('.'.repeat(col) + 'Q' + '.'.repeat(size - col - 1));
            });
        }
    }
    rl.close();
});
