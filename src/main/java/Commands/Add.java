package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import Exceptions.InFileModeException;
import Exceptions.WrongAmountOfArgsException;
import Exceptions.InvalidFormException;
import Managers.CollectionManager;
import Models.Forms.OrganizationForm;

import java.util.NoSuchElementException;


public class Add extends Command implements  Executable{
    private final CollectionManager collectionManager;
    private final Console console;
    private final static String NAME = "add";
    private final static String DESCRIPTION = "добавляет новый элемент в коллекцию";
    public Add(CollectionManager collectionManager,Console console) {
        super(NAME,DESCRIPTION);
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        console.println(ConsoleFormat.coloring("Создание объекта типа \"Organization\"",ConsoleFormat.PURPLE,ConsoleFormat.BOLD));
        try {
            collectionManager.addElement(new OrganizationForm(console).build());
            console.println(ConsoleFormat.coloring("Создание объекта типа \"Organization\" прошло успешно!", ConsoleFormat.GREEN, ConsoleFormat.BOLD, ConsoleFormat.ITALIC));
        }catch (InvalidFormException e){
            console.printErr("Поля объекта невалидны,объект не создан!");
        }catch (InFileModeException e){
            console.printErr("Произошла ошибка в скриптовом режиме");
        }

    }
}
