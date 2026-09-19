import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Solution {
    private static class Task {
        private final String title;
        private boolean done;

        private Task(String title) {
            this.title = title;
            this.done = false;
        }
    }

    private static void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String mark = task.done ? "x" : " ";
            System.out.println((i + 1) + ". [" + mark + "] " + task.title);
        }
    }

    private static int parseIndex(String text, int size) {
        try {
            int number = Integer.parseInt(text);
            if (number >= 1 && number <= size) {
                return number - 1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        System.out.println("Commands: add <task>, list, done <number>, remove <number>, quit");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+", 2);
            String command = parts[0].toLowerCase(Locale.ROOT);
            String argument = parts.length > 1 ? parts[1].trim() : "";
            if (command.equals("add")) {
                if (argument.isEmpty()) {
                    System.out.println("Usage: add <task>");
                } else {
                    tasks.add(new Task(argument));
                    System.out.println("Added: " + argument);
                }
            } else if (command.equals("list")) {
                printTasks(tasks);
            } else if (command.equals("done")) {
                int index = parseIndex(argument, tasks.size());
                if (index < 0) {
                    System.out.println("Invalid task number.");
                } else {
                    tasks.get(index).done = true;
                    System.out.println("Task " + (index + 1) + " marked as done.");
                }
            } else if (command.equals("remove")) {
                int index = parseIndex(argument, tasks.size());
                if (index < 0) {
                    System.out.println("Invalid task number.");
                } else {
                    Task removed = tasks.remove(index);
                    System.out.println("Removed: " + removed.title);
                }
            } else if (command.equals("quit")) {
                System.out.println("Goodbye.");
                return;
            } else {
                System.out.println("Unknown command.");
            }
        }
    }
}
