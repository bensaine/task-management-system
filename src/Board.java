import java.io.FileWriter;
import java.util.ArrayList;

public class Board {
    private String name;
    private String description;
    private final ArrayList<Column> columns;
    private final long createdAt;
    private long updatedAt;

    public Board(String name, String description) {
        this.name = name;
        this.description = description;
        this.columns = new ArrayList<>();
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

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        columns.add(column);
        update();
    }

    public void removeColumn(Column column) {
        columns.remove(column);
        update();
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

    public void display() {
        for (Column c: columns) {
            c.display();
        }
    }

    public void exportToFile() throws java.io.IOException {
        System.out.println("Exporting board to downloads folder...");
        String filePath = System.getProperty("user.home")+"/Downloads/"+name+"-board.txt";
        FileWriter writer = new FileWriter(filePath);
        writer.write("Board name: " + name + "\n");
        writer.write("Board description: " + description + "\n");
        writer.write("Board columns: \n");
        for (Column c: columns) {
            writer.write("\t" + c.getDisplayString() + "\n");
        }
        writer.close();
    }

    public String toString() {
        return name + " (" + description + ")";
    }
}
