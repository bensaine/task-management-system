public class ReminderTask extends DeadlineTask implements IColorable {
    private static final long overdueOffset = 2;

    public ReminderTask() {
        super();
    }

    public ReminderTask(String name, String description, int daysTillDue) {
        super(name, description, daysTillDue);
    }

    public ReminderTask(String name, String description, int daysTillDue, UserIO.Color color) {
        super(name, description, daysTillDue);
        this.color = color;
    }

    @Override
    public void setColor(UserIO.Color color) {
        this.color = color;
        update();
    }

    @Override
    public boolean isOverdue() {
        return (daysTillDue + overdueOffset) < 0;
    }

    @Override
    public String getDisplayProperties() {
        String displayProperties = "";
        displayProperties += "Priority: " + priority + "\n";
        displayProperties += "Reminder Task: " + name + "\n";
        displayProperties += "Description: " + description + "\n";
        displayProperties += "Completed: " + (isCompleted ? "Yes" : "No") + "\n";
        displayProperties += "Days till due: " + daysTillDue + "\n";
        displayProperties += "Overdue: " + (isOverdue() ? "Yes" : "No") + "\n";
        return displayProperties;
    }

    @Override
    public String toString() {
        return super.toString() + " [Reminder Task]";
    }
}
