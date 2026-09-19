#include <exception>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <string>

double calculate(double first, char op, double second) {
    switch (op) {
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

bool parseNumber(const std::string& text, double& value) {
    try {
        std::size_t used = 0;
        value = std::stod(text, &used);
        return used == text.size();
    } catch (const std::exception&) {
        return false;
    }
}

int main() {
    std::string line;
    std::getline(std::cin, line);
    std::istringstream input(line);
    std::string firstText;
    std::string opText;
    std::string secondText;
    std::string extra;
    if (!(input >> firstText >> opText >> secondText) || (input >> extra)) {
        std::cout << "Error: Invalid input\n";
        return 0;
    }
    double first = 0;
    double second = 0;
    if (!parseNumber(firstText, first) || !parseNumber(secondText, second)) {
        std::cout << "Error: Invalid input\n";
        return 0;
    }
    char op = opText[0];
    if (opText.size() != 1 || (op != '+' && op != '-' && op != '*' && op != '/')) {
        std::cout << "Error: Invalid operator\n";
        return 0;
    }
    if (op == '/' && second == 0) {
        std::cout << "Error: Division by zero\n";
        return 0;
    }
    std::cout << std::fixed << std::setprecision(2) << calculate(first, op, second) << '\n';
    return 0;
}
