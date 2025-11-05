package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import ConsoleOperations.Hint;

import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;



public class FilterByFullName extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    private final static String NAME = "filter_by_full_name";
    private final static String DESCRIPTION = "выводит элементы, значение поля fullName которых равно заданному";
    public FilterByFullName(Console console,CollectionManager collectionManager) {
        super(NAME,DESCRIPTION);
        this.console= console;
        this.collectionManager =collectionManager;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (args.isBlank()) throw  new WrongAmountOfArgsException();

        var fullName = args.trim();
        var temp =collectionManager.filterByFullName(fullName);

        if (temp.isEmpty()) console.printHint(Hint.FULLNAMEHINT.toString());
        temp.forEach(org -> console.println(ConsoleFormat.coloring(org.toString(),ConsoleFormat.NONE,ConsoleFormat.BOLD)));
    }
}
