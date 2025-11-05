package Common.Network;

public interface CommandNames {
    String ADD = "add";
    String CLEAR="clear";
    String COUNT_GREATER_THAN_ANNUAL_TURNOVER = "count_greater_than_annual_turnover";
    String EXECUTE_SCRIPT = "execute_script";
    String EXIT ="exit";
    String FILTER_BY_FULL_NAME = "filter_by_full_name";
    String GROUP_COUNTING_BY_FULL_NAME = "group_counting_by_full_name";
    String HEAD = "head";
    String HELP = "help";
    String HISTORY ="history";
    String INFO = "info";
    String REMOVE_BY_ID = "remove_by_id";
    String REMOVE_FIRST ="remove_first";
    String SHOW = "show";
    String UPDATE = "update";

    interface Descriptions{
        String DESCR_ADD = "добавляет новый элемент в коллекцию";

        String DESCR_CLEAR ="очищает коллекцию";
        String DESCR_COUNT_GREATER_THAN_ANNUAL_TURNOVER ="выводит количество элементов, значение поля annualTurnover которых больше заданного";
        String DESCR_EXECUTE = "считывает и исполняет скрипт из указанного файла";
        String DESCR_EXIT ="завершает программу (без сохранения в файл)";
        String DESCR_FILTER_BY_FULL_NAME = "выводит элементы, значение поля fullName которых равно заданному";
        String DESCR_GROUP_COUNTING_BY_FULL_NAME = "группирует элементы коллекции по значению поля fullName, выводит количество элементов в каждой группе";
        String DESCR_HEAD = "выводит первый элемент коллекции";
        String DESCR_HELP = "выводит справку по доступным командам";
        String DESCR_HISTORY ="выводит последние 9 команд";
        String DESCR_INFO = "выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
        String DESCR_REMOVE_BY_ID = "удаляет элемент из коллекции по его id";
        String DESCR_REMOVE_FIRST ="удалить первый элемент из коллекции";
        String DESCR_SHOW = "выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
        String DESCR_UPDATE = "обновляет значение элемента коллекции, id которого равен заданному";


    }


}

