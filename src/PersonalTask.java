public class PersonalTask extends Task implements IColorable {
    public PersonalTask() {
        super();
    }

    public PersonalTask(String name, String description) {
        super(name, description);
    }

    public PersonalTask(String name, String description, UserIO.Color color) {
        super(name, description);
        this.color = color;
    }

    @Override
    public void setColor(UserIO.Color color) {
        this.color = color;
        update();
    }

    @Override
    public String getDisplayProperties() {
        String displayProperties = "";
        displayProperties += "Priority: " + priority + "\n";
        displayProperties += "Personal Task: " + name + "\n";
        displayProperties += "Description: " + description + "\n";
        displayProperties += "Completed: " + (isCompleted ? "Yes" : "No") + "\n";
        return displayProperties;
    }

    @Override
    public String toString() {
        return super.toString() + " [Personal Task]";
    }
}
