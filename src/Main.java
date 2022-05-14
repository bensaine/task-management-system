import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static final ArrayList<Board> boards = new ArrayList<>();

    public static void main(String[] args) {
        welcomeSequence();
    }

    private static void welcomeSequence() {
        System.out.println("Welcome to the Task Management System!");
        System.out.println("Initializing... ");
        mainMenuSequence();
    }

    private static void mainMenuSequence() {
        int choice = UserIO.selectAction("Main menu", Arrays.asList("Create board", "Search board by task", "View board", "Delete board", "Exit"), false, true);
        switch (choice) {
            case 1 -> createBoardSequence();
            case 2 -> searchBoardByTaskSequence();
            case 3 -> viewBoardSequence();
            case 4 -> deleteBoardSequence();
            case 5 -> {
                System.out.println("Exiting... goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice.");
        }
        mainMenuSequence();
    }

    // --- Board logic ---
    private static void createBoardSequence() {
        String name = UserIO.getStringFromUser("Enter the name of the board: ");
        String description = UserIO.getStringFromUser("Enter a description for the board: ");
        boards.add(new Board(name, description));
        System.out.println("Board created successfully.");
    }

    private static void searchBoardByTaskSequence() {
        String search = UserIO.getStringFromUser("Enter a fragment of the name of the task: ");
        // linear search
        boards.stream()
            .filter(board -> board.getColumns().stream()
                .flatMap(column -> column.getTasks().stream())
            .anyMatch(task -> task.getName().contains(search)))
            .forEach(System.out::println);
    }

    private static void viewBoardSequence() {
        Board board = UserIO.getBoardFromUser(boards);
        if (board != null) {
            boardActionsMenu(board);
        }
    }

    private static void deleteBoardSequence() {
        Board board = UserIO.getBoardFromUser(boards);
        if (board != null) {
            boards.remove(board);
            System.out.println("Board deleted successfully.");
        }
    }

    private static void boardActionsMenu(Board board) {
        int choice = UserIO.selectAction(board.toString(), Arrays.asList("Display this board", "Export this board to downloads", "Add column", "View column", "Delete column"), true, true);
        switch (choice) {
            case 0 -> mainMenuSequence();
            case 1 -> board.display();
            case 2 -> exportBoard(board);
            case 3 -> addColumnSequence(board);
            case 4 -> viewColumnSequence(board);
            case 5 -> deleteColumnSequence(board);
            default -> System.out.println("Invalid choice.");
        }
        boardActionsMenu(board);
    }

    private static void exportBoard(Board board) {
        try {
            board.exportToFile();
        }
        catch (IOException e) {
            System.out.println("Error exporting board.");
        }
        System.out.println("Board exported successfully.");
    }

    // --- Column logic ---
    private static void addColumnSequence(Board board) {
        String name = UserIO.getStringFromUser("Enter the name of the column: ");
        board.addColumn(new Column(name));
        System.out.println("Column created successfully.");
    }

    private static void viewColumnSequence(Board board) {
        Column column = UserIO.getColumnFromUser(board);
        if (column != null) {
            columnActionsMenu(board, column);
        }
    }

    private static void deleteColumnSequence(Board board) {
        Column column = UserIO.getColumnFromUser(board);
        if (column != null) {
            board.removeColumn(column);
            System.out.println("Column deleted successfully.");
        }
    }

    private static void columnActionsMenu(Board board, Column column) {
        int choice = UserIO.selectAction(column.toString(), Arrays.asList("Display column", "Add task", "View task", "Delete task", "Sort tasks"), true, true);
        switch (choice) {
            case 0 -> boardActionsMenu(board);
            case 1 -> column.display();
            case 2 -> addTaskSequence(column);
            case 3 -> viewTaskSequence(board, column);
            case 4 -> deleteTaskSequence(column);
            case 5 -> sortTasksSequence(column);
            default -> System.out.println("Invalid choice.");
        }
        columnActionsMenu(board, column);
    }

    private static void sortTasksSequence(Column column) {
        int choice = UserIO.selectAction(column.toString(), Arrays.asList("Sort by priority", "Sort by completion"), true, true);
        switch (choice) {
            case 1 -> column.sortTasksByPriority();
            case 2 -> column.sortTasksByCompletion();
        }
    }

    // --- Task logic ---
    private static void viewTaskSequence(Board board, Column column) {
        Task task = UserIO.getTaskFromUser(column);
        if (task != null) {
            taskActionsMenu(board, column, task);
        }
    }

    private static void taskActionsMenu(Board board, Column column, Task task) {
        int choice = UserIO.selectAction(task.toString(), Arrays.asList("Display task", "Edit task", "Complete task", "Prioritize task", "Move task"), true, true);
        switch (choice) {
            case 0 -> columnActionsMenu(board, column);
            case 1 -> task.display();
            case 2 -> editTaskSequence(task);
            case 3 -> task.complete();
            case 4 -> prioritizeTaskSequence(task);
            case 5 -> moveTaskSequence(board, column, task);
            default -> System.out.println("Invalid choice.");
        }
        taskActionsMenu(board, column, task);
    }

    private static void addTaskSequence(Column column) {
        Task task;
        int choice = UserIO.selectAction("Select the type of task you want to create: ", Arrays.asList("Personal Task", "Reminder Task", "Exam Task"), false, false);
        switch (choice) {
            case 1 -> task = new PersonalTask();
            case 2 -> task = new ReminderTask();
            case 3 -> task = new ExamTask();
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }
        task.setName(UserIO.getStringFromUser("Enter the name of the task: "));
        task.setDescription(UserIO.getStringFromUser("Enter a description for the task: "));
        if (task instanceof DeadlineTask) {
            ((DeadlineTask) task).setDaysTillDue(UserIO.getIntFromUser("Enter the amount of days until this task is due: "));
        }
        if (task instanceof IColorable) {
            ((IColorable) task).setColor(UserIO.getColorFromUser("Enter the color for the task: "));
        }
        column.addTask(task);
        System.out.println("Task created successfully.");
    }

    private static void editTaskSequence(Task task) {
        System.out.println("Current name of the task: "+task.getName());
        task.setName(UserIO.getStringFromUser("Enter the new name of the task: "));
        System.out.println("Current description of the task: "+task.getDescription());
        task.setDescription(UserIO.getStringFromUser("Enter the new description for the task: "));
        if (task instanceof DeadlineTask) {
            System.out.println("Current amount of days until this task is due: "+((DeadlineTask) task).getDaysTillDue());
            ((DeadlineTask) task).setDaysTillDue(UserIO.getIntFromUser("Enter the new amount of days until this task is due: "));
        }
        if (task instanceof IColorable) {
            System.out.println("Current color of the task: "+UserIO.getPrintableColor(task.getColor()));
            ((IColorable) task).setColor(UserIO.getColorFromUser("Enter the new color for the task: "));
        }
        System.out.println("Task edited successfully.");
    }

    private static void prioritizeTaskSequence(Task task) {
        System.out.println("Current priority of the task: "+task.getPriority());
        float priority = UserIO.getFloatFromUser("Enter the new priority of the task (0.0 being the lowest priority): ");
        task.setPriority(priority);
        System.out.println("Task prioritized successfully.");
    }

    private static void moveTaskSequence(Board board, Column column, Task task) {
        System.out.println("Current column of the task: "+column.getName());
        System.out.println("New column: ");
        Column newColumn = UserIO.getColumnFromUser(board);
        if (newColumn == null) {
            System.out.println("Invalid column.");
            return;
        }
        column.removeTask(task);
        newColumn.addTask(task);
        System.out.println("Task moved successfully.");
        columnActionsMenu(board, newColumn);
    }

    private static void deleteTaskSequence(Column column) {
        Task task = UserIO.getTaskFromUser(column);
        if (task != null) {
            column.removeTask(task);
            System.out.println("Task deleted successfully.");
        }
    }
}
