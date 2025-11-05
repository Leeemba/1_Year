package client.forms;

import client.utility.Console;
import client.utility.Printable;
import client.utility.ScannerManager;
import client.utility.ScriptConsole;
import common.exceptions.InFileModeException;
import common.exceptions.InvalidFormException;
import common.exceptions.NotInProvidedBoundsException;
import common.models.User;
import common.utility.ConsoleFormat;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AuthenticateForm implements Form<User>{

    private Printable console;

    public AuthenticateForm(Printable console){
        this.console = Console.getFileMode()
                ? new ScriptConsole()
                : console;
    }



    @Override
    public User build() throws InvalidFormException, InFileModeException {
        var user  = new User(askLogin(),askPassword());
        if (!user.validate()) throw  new InvalidFormException();
        return user;
    }



    public String askLogin() throws InFileModeException {
        Scanner scanner = ScannerManager.getScannerManager().getUserScanner();
        while (true){
            try {
                console.print(ConsoleFormat.coloring("Введите имя пользователя(логин): ", ConsoleFormat.YELLOW, ConsoleFormat.BOLD));
                String input = scanner.nextLine().trim();
                if (input.isBlank()||input.length()>=32) throw new NotInProvidedBoundsException();
                return input;
            }catch (NotInProvidedBoundsException e){
                console.printErr("Ошибочка! Вы вышли за допустимый диапазон значений: имя пользователя может быть длины от 1 до 32 символов.");
                if (Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(1);
            }
        }
    }


    public String askPassword() throws InFileModeException{
        while (true){
            try {
                console.print(ConsoleFormat.coloring("Введите пароль: ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                String input = readPassword();
                if (input.isBlank()|| input.length()>=32) throw new NotInProvidedBoundsException();
                return input;
            } catch (NotInProvidedBoundsException e) {
                console.printErr("Ошибочка! Вы вышли за допустимый диапазон значений: имя пользователя может быть длины от 1 до 32 символов.");
                if (Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(1);
            }
        }
    }



    protected String readPassword(){
        if (System.console() == null){
            return ScannerManager.getScannerManager().getUserScanner().nextLine().trim();
        }
        return new String(System.console().readPassword());
    }



}
