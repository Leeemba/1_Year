package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;

import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;
import Managers.FileManager;

public class Save extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private final static String NAME = "save";
    private final static String DESCRIPTION = "сохраняет коллекцию в файл";
    public Save(Console console, CollectionManager collectionManager) {
        super(NAME, DESCRIPTION);
        this.console = console;
        this.collectionManager = collectionManager ;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        console.print(ConsoleFormat.coloring("Укажите путь,по которому лежит файл: ",ConsoleFormat.PURPLE,ConsoleFormat.BOLD));
        collectionManager.saveCollection();

    }
}
