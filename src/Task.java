import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task {
    protected String name;
    protected String description;
    protected UserIO.Color color = UserIO.Color.Unset;
    protected boolean isCompleted = false;
    protected float priority = 0;
    protected final long createdAt;
    protected long updatedAt;
    private static final String timeFormat = "yyyy-MM-dd HH:mm:ss";

    public Task() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        update();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        update();
    }

    public UserIO.Color getColor() {
        return color;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void complete() {
        isCompleted = true;
        update();
        System.out.print("Woohoo! Task completed ");
        try {
            for (int i = 0; i < 4; i++) {
                System.out.print("⌙");
                System.out.print('o');
                System.out.print('¬');
                Thread.sleep(500);
                System.out.print('\b');
                System.out.print('\b');
                System.out.print('\b');
                System.out.print("\\");
                System.out.print('o');
                System.out.print('/');
                Thread.sleep(500);
                System.out.print('\b');
                System.out.print('\b');
                System.out.print('\b');
            }
        } catch (InterruptedException e) {
            System.out.println("Animation Interrupted");
        }
        System.out.print("\\");
        System.out.print('o');
        System.out.print('/');
        System.out.println();

    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
        update();
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    protected void update() {
        updatedAt = System.currentTimeMillis();
    }

    public abstract String getDisplayProperties();

    public String getDisplayString() {
        String displayString = "";
        displayString += UserIO.getColorCode(color) + "\n";
        displayString += "-------------------------" + "\n";
        displayString += getDisplayProperties();
        displayString += getFormattedDates();
        displayString += "-------------------------" + "\n";
        displayString += UserIO.getColorCode(UserIO.Color.Unset);
        return displayString;
    }

    public void display() {
        System.out.println(getDisplayString());
    }

    public String getFormattedDates() {
        String dateString = "";
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        dateString += "Created at: " + formatter.format(new Date(createdAt)) + "\n";
        dateString += "Updated at: " + formatter.format(new Date(updatedAt)) + "\n";
        return dateString;
    }

    @Override
    public String toString() {
        return "Task: " + name;
    }
}
