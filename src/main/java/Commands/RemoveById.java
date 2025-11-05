package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import Exceptions.EmptyCollectionException;
import Exceptions.NotFoundException;
import Exceptions.WrongAmountOfArgsException;
import Managers.CollectionManager;

import java.util.NoSuchElementException;

public class RemoveById  extends  Command{
    private final CollectionManager collectionManager;
    private final Console console;
    private final static String NAME = "remove_by_id";
    private final static String DESCRIPTION = "удаляет элемент из коллекции по его id";
    public RemoveById(Console console,CollectionManager collectionManager) {
        super(NAME, DESCRIPTION);
        this.collectionManager =collectionManager;
        this.console =console;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException,EmptyCollectionException {
        if (args.isBlank()) throw new WrongAmountOfArgsException();
        if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
        int id = Integer.parseInt(args);
        try {
            if (!collectionManager.checkExist(id)) throw new NotFoundException();
            collectionManager.removeElement(collectionManager.getById(id));
            console.println(ConsoleFormat.coloring("Объект с id: "+id+" успешно удален",ConsoleFormat.GREEN,ConsoleFormat.ITALIC));
        }catch (NotFoundException e){
            console.printErr("Объекта с таким id нет в списке!");
        }catch (NumberFormatException e){
            console.printErr("Id должно быть числом типа int!");
        }
    }
}
