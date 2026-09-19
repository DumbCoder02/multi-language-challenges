const readline = require('readline');

function isPalindrome(text) {
    const cleaned = text.toLowerCase().replace(/[^a-z0-9]/g, '');
    return cleaned === cleaned.split('').reverse().join('');
}

const rl = readline.createInterface({ input: process.stdin });

rl.once('line', line => {
    console.log(isPalindrome(line) ? 'Palindrome' : 'Not a palindrome');
    rl.close();
});
