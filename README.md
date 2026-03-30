# Java To-Do List

A simple command-line To-Do List application written in Java.

## Features

- Add tasks with a description
- Mark tasks as completed
- Delete tasks
- View all tasks with their status
- **Persistent storage** — tasks are automatically saved to a file and reloaded on next launch

## How to Run

1. Compile the source files:
   ```bash
   javac src/*.java -d out/
   ```

2. Run the application:
   ```bash
   java -cp out/ TodoApp
   ```

## Project Structure

```
TodoList/
├── src/
│   ├── TodoApp.java       # Main entry point, user interface
│   ├── TaskManager.java   # Handles all task logic and file I/O
│   └── Task.java          # Task data model
├── README.md
├── CONTRIBUTORS.md
└── LICENSE
```

## Example

```
==========================================
       Welcome to Java To-Do List         
==========================================

--- Your Tasks (0 total) ---
  No tasks yet. Add one!

------------------------------------------
  1. Add Task
  2. Complete Task
  3. Delete Task
  4. Exit
------------------------------------------
```

## License

MIT License — see [LICENSE](LICENSE) for details.
