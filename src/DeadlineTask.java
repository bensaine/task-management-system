public abstract class DeadlineTask extends Task {
    protected int daysTillDue;

    public DeadlineTask() {
    }

    public DeadlineTask(String name, String description, int daysTillDue) {
        super(name, description);
        this.daysTillDue = daysTillDue;
    }

    public int getDaysTillDue() {
        return daysTillDue;
    }

    public void setDaysTillDue(int daysTillDue) {
        this.daysTillDue = daysTillDue;
    }

    public abstract boolean isOverdue();
}
