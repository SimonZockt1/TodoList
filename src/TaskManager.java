import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks.
 * Handles adding, completing, deleting, listing, saving, and loading tasks.
 */
public class TaskManager {

    private List<Task> tasks;
    private final String saveFilePath;

    /**
     * Creates a TaskManager and loads existing tasks from the save file.
     *
     * @param saveFilePath Path to the file where tasks are stored.
     */
    public TaskManager(String saveFilePath) {
        this.saveFilePath = saveFilePath;
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Adds a new task with the given title.
     *
     * @param title The task description. Must not be empty.
     * @return true if the task was added, false if the title was empty.
     */
    public boolean addTask(String title) {
        if (title == null || title.trim().isEmpty()) {
            return false;
        }
        tasks.add(new Task(title.trim()));
        saveToFile();
        return true;
    }

    /**
     * Marks the task at the given index as completed (1-based).
     *
     * @param index The 1-based index of the task.
     * @return true if successful, false if the index is out of range or task is already done.
     */
    public boolean completeTask(int index) {
        if (index < 1 || index > tasks.size()) return false;
        Task task = tasks.get(index - 1);
        if (task.isCompleted()) return false;
        task.markCompleted();
        saveToFile();
        return true;
    }

    /**
     * Deletes the task at the given index (1-based).
     *
     * @param index The 1-based index of the task.
     * @return true if successful, false if out of range.
     */
    public boolean deleteTask(int index) {
        if (index < 1 || index > tasks.size()) return false;
        tasks.remove(index - 1);
        saveToFile();
        return true;
    }

    /**
     * Prints all tasks to the console with their index numbers.
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("  No tasks yet. Add one!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Returns the total number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Saves all tasks to the save file.
     */
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not save tasks to file. (" + e.getMessage() + ")");
        }
    }

    /**
     * Loads tasks from the save file. If the file doesn't exist, starts with an empty list.
     */
    private void loadFromFile() {
        File file = new File(saveFilePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not load tasks from file. (" + e.getMessage() + ")");
        }
    }
}
