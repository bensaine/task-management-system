import java.util.ArrayList;
import java.util.Comparator;

public class Column {
    private String name;
    private final ArrayList<Task> tasks;
    private final long createdAt;
    private long updatedAt;

    public Column(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        update();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        update();
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        update();
    }

    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority).reversed());
    }

    public void sortTasksByCompletion() {
        tasks.sort(Comparator.comparing(Task::isCompleted));
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    private void update() {
        updatedAt = System.currentTimeMillis();
    }

    public String getDisplayString() {
        StringBuilder printableString = new StringBuilder();
        printableString.append(UserIO.getTitle(name)).append("\n");
        if (tasks.isEmpty()) {
            printableString.append("No tasks\n");
            return printableString.toString();
        }
        ArrayList<Task> sortedTasks = tasks;
        sortedTasks.sort(Comparator.comparing(Task::getPriority).reversed());
        for (Task task : sortedTasks) {
            printableString.append(task.getDisplayString());
        }
        return printableString.toString();
    }

    public void display() {
        System.out.println(getDisplayString());
    }

    public String toString() {
        return "Column: "+name;
    }
}
