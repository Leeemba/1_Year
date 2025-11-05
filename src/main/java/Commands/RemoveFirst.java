package Commands;

import ConsoleOperations.Console;
import Exceptions.EmptyCollectionException;
import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;

public class RemoveFirst extends Command{
    private final CollectionManager collection;
    private final Console console;
    private final static String NAME = "remove_first";
    private final static String DESCRIPTION = "удалить первый элемент из коллекции";
    public RemoveFirst(Console console,CollectionManager collection) {
        super(NAME, DESCRIPTION);
        this.collection =collection;
        this.console=console;
    }

    @Override
    public void execute(String args) throws EmptyCollectionException, WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        if (collection.collectionSize() == 0) throw new EmptyCollectionException();
        collection.removeFirst();
    }
}
