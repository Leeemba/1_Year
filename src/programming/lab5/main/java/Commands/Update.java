package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import Exceptions.*;
import Managers.CollectionManager;
import Models.Forms.OrganizationForm;

public class Update extends Command {
    private final CollectionManager collectionManager;
    private final Console console;
    private final static String NAME = "update";
    private final static String DESCRIPTION = "обновляет значение элемента коллекции, id которого равен заданному";
    public Update(Console console,CollectionManager collectionManager){
        super(NAME,DESCRIPTION);
        this.collectionManager =collectionManager;
        this.console =console;
    }

    @Override
    public void execute(String args) throws EmptyCollectionException, WrongAmountOfArgsException {
        if (args.isBlank()) throw new WrongAmountOfArgsException();
        if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
        try{
            int id = Integer.parseInt(args.trim());
            if(!collectionManager.checkExist(id)) throw new NotFoundException();
            collectionManager.editById(id,new OrganizationForm(console).build());
            console.println(ConsoleFormat.coloring("Изменение объекта типа \"Organization\" прошло успешно!", ConsoleFormat.GREEN, ConsoleFormat.BOLD, ConsoleFormat.ITALIC));
        }catch (NotFoundException e){
            console.printErr("Объекта с введенным id нет в списке,объект не создан!");
        }catch (InvalidFormException  e){
            console.printErr("Поля объекта невалидны,объект не создан!");
        }catch (NumberFormatException e){
            console.printErr("Id должно быть числом типа int!");
        }catch (InFileModeException e){
            console.printErr("Произошла ошибка в скриптовом режиме");
        }

    }
}
