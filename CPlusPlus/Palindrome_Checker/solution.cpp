#include <cctype>
#include <iostream>
#include <string>

bool isPalindrome(const std::string& text) {
    std::string cleaned;
    for (unsigned char c : text) {
        if (std::isalnum(c)) {
            cleaned += static_cast<char>(std::tolower(c));
        }
    }
    std::string reversed(cleaned.rbegin(), cleaned.rend());
    return cleaned == reversed;
}

int main() {
    std::string text;
    std::getline(std::cin, text);
    if (isPalindrome(text)) {
        std::cout << "Palindrome\n";
    } else {
        std::cout << "Not a palindrome\n";
    }
    return 0;
}
