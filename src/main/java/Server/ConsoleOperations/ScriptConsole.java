package Server.ConsoleOperations;

import Common.Utility.ConsoleFormat;

/**
 * Класс для вывода в консоль в скриптовом режиме
 *
 *
 * @implNote  интерфейса {@link Printable}
 *
 */
public class ScriptConsole implements Printable{
    @Override
    public void print(String message) {

    }

    @Override
    public void printErr(String message) {
        StringBuilder sb = new StringBuilder(ConsoleFormat.coloring("Ошибка: ", ConsoleFormat.RED,ConsoleFormat.BOLD));
        sb.append(ConsoleFormat.coloring(message,ConsoleFormat.RED,ConsoleFormat.ITALIC));
        System.out.println(sb);
    }

    @Override
    public void println(String message) {

    }
    @Override
    public void printHint(String message){

    }
}
