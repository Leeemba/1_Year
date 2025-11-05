package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import Exceptions.EmptyCollectionException;
import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;

public class Show extends Command implements Executable{
    private final CollectionManager collectionManager ;
    private final Console console;
    private final static String NAME = "show";
    private final static String DESCRIPTION = "выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    public Show(CollectionManager collectionManager,Console console) {
        super(NAME, DESCRIPTION);
        this.collectionManager = collectionManager;
        this.console =console;
    }


    @Override
    public void execute(String args) throws WrongAmountOfArgsException, EmptyCollectionException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
        console.println(ConsoleFormat.coloring(collectionManager.toString(),ConsoleFormat.NONE,ConsoleFormat.BOLD));
    }
}
