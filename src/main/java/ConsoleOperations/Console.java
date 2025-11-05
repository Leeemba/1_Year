package ConsoleOperations;


/**
 * Класс для вывода в консоль
 *
 *
 * @implNote  интерфейса {@link Printable}
 *
 */
public class Console implements Printable {
    private static volatile boolean isFileMode = false;

    public static void setFileMode(boolean isFileMode){
        Console.isFileMode = isFileMode;
    }

    public static boolean getFileMode(){
        return isFileMode;
    }


    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void printErr(String message) {
        StringBuilder sb = new StringBuilder(ConsoleFormat.coloring("Ошибка: ", ConsoleFormat.RED,ConsoleFormat.BOLD));
        sb.append(ConsoleFormat.coloring(message,ConsoleFormat.RED,ConsoleFormat.ITALIC));
        System.out.println(sb);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void printHint(String message){
        StringBuilder sb = new StringBuilder(ConsoleFormat.coloring("[Подсказка]: ", ConsoleFormat.CYAN,ConsoleFormat.BOLD));
        sb.append(ConsoleFormat.coloring(message,ConsoleFormat.CYAN,ConsoleFormat.ITALIC));
        System.out.println(sb);
    }
}
