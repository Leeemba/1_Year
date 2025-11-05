package client.forms;

import client.utility.Console;
import client.utility.Printable;
import client.utility.ScriptConsole;
import common.exceptions.InFileModeException;
import common.exceptions.InvalidFormException;
import common.exceptions.NotInProvidedBoundsException;
import common.models.User;
import common.utility.ConsoleFormat;

import java.util.NoSuchElementException;

public class RegisterForm extends  AuthenticateForm{
    private Printable console;
    public RegisterForm(Printable console){
        super(console);
        this.console = Console.getFileMode()
                ? new ScriptConsole()
                : console;
    }

    @Override
    public User build() throws InFileModeException, InvalidFormException {
        var user = new User( askLogin(), askPassword());
        if (!user.validate()) throw new InvalidFormException();
        return user;
    }

    @Override
    public String askPassword() throws  InFileModeException{
        while (true){
            try {
                console.print(ConsoleFormat.coloring("Введите пароль: ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                String input = readPassword();
                if (input.isBlank()) throw new NotInProvidedBoundsException();
                console.print(ConsoleFormat.coloring("Повторно введите пароль: ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                String repeat = readPassword();
                if(!input.equals(repeat)) throw new InvalidFormException();
                return input;
            } catch (NotInProvidedBoundsException e) {
                console.printErr("Ошибочка! значение поля пароль не может быть пустым");
                if (Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(1);
            } catch (InvalidFormException e) {
                console.printErr("Ошибочка! Пароли не совпадают");
            }
        }
    }



}
