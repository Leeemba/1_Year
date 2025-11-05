package Commands;

import ConsoleOperations.Console;
import Exceptions.EmptyCollectionException;
import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;

import static java.util.stream.Collectors.groupingBy;

public class GroupByFullName extends  Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private final static String NAME = "group_counting_by_full_name";
    private final static String DESCRIPTION = "группирует элементы коллекции по значению поля fullName, выводит количество элементов в каждой группе";
    public GroupByFullName(Console console,CollectionManager collectionManager) {
        super(NAME,DESCRIPTION);
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException,EmptyCollectionException{
    if(!args.isBlank()) throw new WrongAmountOfArgsException();
    if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
    console.println(collectionManager.groupByFullName().toString());
    }
}
