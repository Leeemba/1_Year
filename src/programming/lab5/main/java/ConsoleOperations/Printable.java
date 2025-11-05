package ConsoleOperations;

/**
 * Интерфейс для реализации вывода информации
 */
public interface Printable {

    /**
     * Метод для вывода без перехода на новую строку
     * @param message сообщение для вывода
     */
    void print(String message);
    /**
     * Метод для вывода ошибок
     * @param message сообщение для вывода
     */
    void printErr(String message);
    /**
     * Метод для вывода с переходом на новую строку
     * @param message сообщение для вывода
     */
    void println(String message);
    /**
     * Метод для вывода подсказок пользователю
     * @param message сообщение для вывода
     */
    void printHint(String message);

}
