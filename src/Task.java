/**
 * Represents a single to-do task with a title and a completion status.
 */
public class Task {

    private String title;
    private boolean completed;

    /**
     * Creates a new incomplete task.
     *
     * @param title The description of the task.
     */
    public Task(String title) {
        this.title = title;
        this.completed = false;
    }

    /**
     * Creates a task with an explicit completion status.
     * Used when loading tasks from a file.
     *
     * @param title     The description of the task.
     * @param completed Whether the task is already completed.
     */
    public Task(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marks this task as completed.
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Returns a formatted string for display in the task list.
     * Example: "[x] Buy groceries" or "[ ] Write report"
     */
    @Override
    public String toString() {
        return (completed ? "[x] " : "[ ] ") + title;
    }

    /**
     * Returns a string suitable for saving to a file.
     * Format: "DONE|Buy groceries" or "TODO|Write report"
     */
    public String toFileString() {
        return (completed ? "DONE" : "TODO") + "|" + title;
    }

    /**
     * Parses a line from the save file and returns a Task object.
     *
     * @param line A line in the format "DONE|title" or "TODO|title".
     * @return A Task object, or null if the line is invalid.
     */
    public static Task fromFileString(String line) {
        String[] parts = line.split("\\|", 2);
        if (parts.length != 2) return null;
        boolean done = parts[0].equals("DONE");
        return new Task(parts[1], done);
    }
}
