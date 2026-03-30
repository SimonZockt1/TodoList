import java.util.Scanner;

/**
 * TodoApp - A simple command-line To-Do List application.
 *
 * Features:
 *  - Add tasks
 *  - Mark tasks as completed
 *  - Delete tasks
 *  - View all tasks
 *  - Auto-saves to a file so your list persists between sessions
 *
 * Author: SimonZockt1
 */
public class TodoApp {

    private static final String SAVE_FILE = "tasks.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager(SAVE_FILE);

        System.out.println("==========================================");
        System.out.println("       Welcome to Java To-Do List         ");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Your Tasks (" + manager.getTaskCount() + " total) ---");
            manager.printTasks();
            printMenu();
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    // Add task
                    System.out.print("Enter task description: ");
                    String title = scanner.nextLine();
                    if (manager.addTask(title)) {
                        System.out.println("Task added!");
                    } else {
                        System.out.println("Task description cannot be empty.");
                    }
                    break;

                case 2:
                    // Complete task
                    System.out.print("Enter task number to mark as completed: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input.");
                        scanner.nextLine();
                        break;
                    }
                    int completeIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (manager.completeTask(completeIndex)) {
                        System.out.println("Task marked as completed!");
                    } else {
                        System.out.println("Invalid task number or task already completed.");
                    }
                    break;

                case 3:
                    // Delete task
                    System.out.print("Enter task number to delete: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input.");
                        scanner.nextLine();
                        break;
                    }
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (manager.deleteTask(deleteIndex)) {
                        System.out.println("Task deleted.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("\nGoodbye! Your tasks have been saved.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }
        }

        scanner.close();
    }

    /**
     * Prints the main menu options.
     */
    private static void printMenu() {
        System.out.println("\n------------------------------------------");
        System.out.println("  1. Add Task");
        System.out.println("  2. Complete Task");
        System.out.println("  3. Delete Task");
        System.out.println("  4. Exit");
        System.out.println("------------------------------------------");
    }
}
