#include <algorithm>
#include <cctype>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

struct Task {
    std::string title;
    bool done;
};

std::string trim(const std::string& text) {
    const std::string whitespace = " \t\r\n";
    std::size_t start = text.find_first_not_of(whitespace);
    if (start == std::string::npos) {
        return "";
    }
    std::size_t end = text.find_last_not_of(whitespace);
    return text.substr(start, end - start + 1);
}

int parseIndex(const std::string& text, std::size_t size) {
    std::istringstream input(text);
    int number = 0;
    char extra = 0;
    if (!(input >> number) || (input >> extra)) {
        return -1;
    }
    if (number < 1 || number > static_cast<int>(size)) {
        return -1;
    }
    return number - 1;
}

void printTasks(const std::vector<Task>& tasks) {
    if (tasks.empty()) {
        std::cout << "No tasks.\n";
        return;
    }
    for (std::size_t i = 0; i < tasks.size(); ++i) {
        char mark = tasks[i].done ? 'x' : ' ';
        std::cout << (i + 1) << ". [" << mark << "] " << tasks[i].title << '\n';
    }
}

int main() {
    std::vector<Task> tasks;
    std::cout << "Commands: add <task>, list, done <number>, remove <number>, quit\n";
    std::string line;
    while (std::getline(std::cin, line)) {
        line = trim(line);
        if (line.empty()) {
            continue;
        }
        std::istringstream stream(line);
        std::string command;
        stream >> command;
        std::string argument;
        std::getline(stream, argument);
        argument = trim(argument);
        std::transform(command.begin(), command.end(), command.begin(),
                       [](unsigned char c) { return static_cast<char>(std::tolower(c)); });
        if (command == "add") {
            if (argument.empty()) {
                std::cout << "Usage: add <task>\n";
            } else {
                tasks.push_back({argument, false});
                std::cout << "Added: " << argument << '\n';
            }
        } else if (command == "list") {
            printTasks(tasks);
        } else if (command == "done") {
            int index = parseIndex(argument, tasks.size());
            if (index < 0) {
                std::cout << "Invalid task number.\n";
            } else {
                tasks[index].done = true;
                std::cout << "Task " << (index + 1) << " marked as done.\n";
            }
        } else if (command == "remove") {
            int index = parseIndex(argument, tasks.size());
            if (index < 0) {
                std::cout << "Invalid task number.\n";
            } else {
                std::cout << "Removed: " << tasks[index].title << '\n';
                tasks.erase(tasks.begin() + index);
            }
        } else if (command == "quit") {
            std::cout << "Goodbye.\n";
            return 0;
        } else {
            std::cout << "Unknown command.\n";
        }
    }
    return 0;
}
