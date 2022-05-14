public class ExamTask extends DeadlineTask {
    public ExamTask() {
        this.color = UserIO.Color.Red;
    }

    public ExamTask(String name, String description, int daysTillDue) {
        super(name, description, daysTillDue);
        this.color = UserIO.Color.Red;
    }

    @Override
    public boolean isOverdue() {
        return daysTillDue < 0;
    }

    @Override
    public String getDisplayProperties() {
        String displayProperties = "";
        displayProperties += "Priority: " + priority + "\n";
        displayProperties += "Exam Task: " + name + "\n";
        displayProperties += "Description: " + description + "\n";
        displayProperties += "Exam Completed: " + (isCompleted ? "Yes" : "No") + "\n";
        displayProperties += "Days till Exam: " + daysTillDue + "\n";
        displayProperties += "Overdue: " + (isOverdue() ? "Yes" : "No") + "\n";
        return displayProperties;
    }

    @Override
    public String toString() {
        return super.toString() + " [Exam Task]";
    }
}
