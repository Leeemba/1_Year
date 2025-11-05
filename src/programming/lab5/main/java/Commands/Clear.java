package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import Exceptions.EmptyCollectionException;
import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;

public class Clear extends Command implements Executable{
    private final Console console;
    private final CollectionManager collectionManager;
    private final static String NAME = "clear";
    private final static String DESCRIPTION = "очищает коллекцию";
    public Clear(Console console,CollectionManager collectionManager) {
        super(NAME,DESCRIPTION);
        this.console=console;
        this.collectionManager = collectionManager;
    }



    @Override
    public void execute(String args) throws EmptyCollectionException, WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        if (collectionManager.collectionSize() == 0) throw  new EmptyCollectionException();
        collectionManager.removeAll();
        console.println(ConsoleFormat.coloring("Коллекция очищена успешно!",ConsoleFormat.GREEN,ConsoleFormat.BOLD));

    }

}
