package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import ConsoleOperations.Hint;
import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;

public class Info extends Command implements  Executable {
    private final Console console;
    private final CollectionManager collectionManager;
    private final static String NAME = "info";
    private final static String DESCRIPTION = "выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    public Info(Console console,CollectionManager collectionManager) {
        super(NAME, DESCRIPTION);
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
    if(!args.isBlank()) throw new WrongAmountOfArgsException();
    String lastInitTime = (collectionManager.getLastInitTime() == null)
            ? Hint.INITTIMEHINT.toString()
            : collectionManager.getLastInitTime().toString();
    String lastSaveTime = (collectionManager.getLastSaveTime() == null)
            ? Hint.SAVETIMEHINT.toString()
            : collectionManager.getLastSaveTime().toString();
    StringBuilder sb = new StringBuilder();

    sb.append(ConsoleFormat.coloring("Информация о коллекции:\n",ConsoleFormat.PURPLE,ConsoleFormat.BOLD,ConsoleFormat.UNDERLINE));

    sb.append(ConsoleFormat.coloring("Тип: ",ConsoleFormat.BLUE,ConsoleFormat.BOLD))
            .append(ConsoleFormat.coloring(collectionManager.collectionType() +"\n",ConsoleFormat.NONE,ConsoleFormat.BOLD));
    sb.append(ConsoleFormat.coloring("Количество элементов: ",ConsoleFormat.BLUE,ConsoleFormat.BOLD))
            .append(ConsoleFormat.coloring(collectionManager.collectionSize() +"\n",ConsoleFormat.NONE,ConsoleFormat.BOLD));
    sb.append(ConsoleFormat.coloring("Последняя дата инициализации: ",ConsoleFormat.BLUE,ConsoleFormat.BOLD))
            .append(ConsoleFormat.coloring(lastInitTime+"\n",ConsoleFormat.NONE,ConsoleFormat.BOLD));
    sb.append(ConsoleFormat.coloring("Последняя дата сохранения: ",ConsoleFormat.BLUE,ConsoleFormat.BOLD))
            .append(ConsoleFormat.coloring(lastSaveTime+"\n",ConsoleFormat.NONE,ConsoleFormat.BOLD));

    console.print(sb.toString());
    }
}
