package common.utility;

/**
 * Класс-перечисление, предназначенный для изменения текста из потока вывода
 *
 */
public enum ConsoleFormat {

    // Для раскрашивания сообщений вывода
    BLACK("\u001B[30m","черный"),  //Резерв
    RED("\u001B[31m","красный"),   //Для ошибок
    GREEN("\u001B[32m","зеленый"),  // Имена команд/успешное действие
    YELLOW("\u001B[33m","желтый"),  // Для предложений ввода значений
    BLUE("\u001B[34m","синий"),    //Резерв
    PURPLE("\u001B[35m","фиолетовый"),  //При выводе списка возможных значений/"крупных" по-значимости сообщений
    CYAN("\u001B[36m","бирюзовый"), //Для подсказок пользователю
    WHITE("\u001B[37m","белый"),  //Резерв

    // Для возвращения значения по умолчанию
    RESET("\u001B[0m","по умолчанию"), //Восстановление изначального значения терминала
    NONE("",""),  // Пустота, когда не нужно раскрашивание/выделение

    // Для изменения стиля текста
    BOLD("\u001B[1m","жирный"),
    ITALIC("\u001B[3m","курсив"),
    UNDERLINE("\u001B[4m","подчеркнутый"),
    STRIKETHROUGH("\u001B[9m","зачеркнутый");


    final String codeANSI;
    final String rusName;


    ConsoleFormat(String name,String rusName){
        this.codeANSI = name;
        this.rusName = rusName;
    }

    /**
     * Метод, позволяющий отформатировать выводимый в терминал текст(раскрасить и применить стиль)
     * @param text  исходное сообщение для изменения
     * @param color цвет раскрашивания
     * @param format стиль текста
     * @return результирующий текст после раскрашивания и применения стиля(-ей)
     */
    public static String coloring(String text, ConsoleFormat color,ConsoleFormat... format)  {
        StringBuilder sb = new StringBuilder(color.getName());
        for (ConsoleFormat s: format) {
            if(s.equals(BLACK)
                    ||s.equals(RED)
                    ||s.equals(GREEN)
                    ||s.equals(YELLOW)
                    ||s.equals(BLUE)
                    ||s.equals(PURPLE)
                    ||s.equals(CYAN)
                    ||s.equals(WHITE)){
                sb.append(NONE);
            }else {
                sb.append(s);
            }
        }
        sb.append(text).append(RESET);

        return sb.toString();
    }

    public String getName() {
        return codeANSI;
    }
    public String getTranslation(){              // Неиспользуемый метод для возвращения перевода функционала на русский
        return rusName;
    }


    @Override
    public  String toString(){
        return codeANSI;
    }

}
