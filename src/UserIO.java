import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;

public class UserIO {
    private static final Scanner sc = new Scanner(System.in);
    public enum Color { Red, Green, Blue, Yellow, Pink, White, Unset}
    public static final EnumMap<Color, String> colorCodes = new EnumMap<>(Color.class) {{
        put(Color.Red, "\u001B[31m");
        put(Color.Green, "\u001B[32m");
        put(Color.Blue, "\u001B[34m");
        put(Color.Yellow, "\u001B[33m");
        put(Color.Pink, "\u001B[35m");
        put(Color.White, "\u001B[37m");
        put(Color.Unset, "\u001B[0m");
    }};

    public static String getColorCode(Color color) {
        return colorCodes.get(color);
    }

    public static String getPrintableColor(Color color) {
        return getColorCode(color)+color.name()+getColorCode(Color.Unset);
    }

    public static String getStringFromUser(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static int getIntFromUser(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(getStringFromUser(prompt));
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid number.");
            }
        }
    }

    public static float getFloatFromUser(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(getStringFromUser(prompt));
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a valid number.");
            }
        }
    }

    public static Color getColorFromUser(String prompt) {
        while (true) {
            colorCodes.keySet().forEach(color -> System.out.println(getPrintableColor(color)));
            String color = getStringFromUser(prompt);
            try {
                return Color.valueOf(color);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid Input. Please enter a valid color.");
            }
        }
    }

    public static Board getBoardFromUser(List<Board> boards) {
        return selectItem("Select a board: ", boards, true);
    }

    public static Column getColumnFromUser(Board board) {
        return selectItem("Select a column: ", board.getColumns(), true);
    }

    public static Task getTaskFromUser(Column column) {
        return selectItem("Select a task: ", column.getTasks(), true);
    }

    private static <E> E selectItem(String prompt, List<E> items, boolean showBackOption) {
        int choice = selectAction(prompt, items, showBackOption, true);
        return choice > 0 && choice <= items.size() ? items.get(choice-1) : null;
    }

    public static <E> int selectAction(String prompt, List<E> actions, boolean showBackOption, boolean titleCase) {
        if (titleCase) printTitle(prompt);
        else System.out.println(prompt);
        if (showBackOption) System.out.println("0. Back");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println((i+1)+". "+actions.get(i));
        }
        return getIntFromUser("Enter an option: ");
    }

    public static void printTitle(String title) {
        System.out.println(getTitle(title));
    }

    public static String getTitle(String title) {
        return "\n---- "+title+" ----";
    }
}
