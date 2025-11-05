package Common.Utility;

/**
 * КЛАСС-ПЕРЕЧИСЛЕНИЕ ДЛЯ ВЫВОДА СТРОКОВЫХ СООБЩЕНИЙ-ПОДСКАЗОК ПОЛЬЗОВАТЕЛЮ
 */
public enum Hint {
    HELPHINT("Для справки по всем существующим командам введите: \"help\" "),
    HISTORYHINT("Не было введено ни одной команды. История команд пуста"),
    FULLNAMEHINT("Организаций с таким полным названием не обнаружено"),
    SAVETIMEHINT("В сессии коллекция ещё не сохранялась"),
    INITTIMEHINT("В сессии коллекция  ещё не была инициализирована");
   String description;
   Hint(String description){
       this.description =description;
   }
   @Override
   public String toString(){
       return description;
   }

}
