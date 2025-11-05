package Models.Forms;

import ConsoleOperations.*;
import Exceptions.InFileModeException;
import Exceptions.InvalidFormException;
import Exceptions.NotInProvidedBoundsException;
import Managers.ScannerManager;
import Models.Address;

import java.util.NoSuchElementException;
import java.util.Scanner;


public class AddressForm implements Form<Address> {
    private final Printable console;
    //private final UserInput input;

    public AddressForm(Printable console){
        this.console = (Console.getFileMode())
                ? new ScriptConsole()
                : console;
        /*this.input = (Console.getFileMode())
                ? new ScriptMode()
                : new ConsoleInput();*/
    }

    public String askZipCode() throws InFileModeException{
        do {
            try{
                console.print(ConsoleFormat.coloring("Введите значение почтового индекса: ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                Scanner scanner = ScannerManager.getScannerManager().getUserScanner();
                if(!scanner.hasNextLine()){
                    throw new NoSuchElementException();
                }
                String argument = scanner.nextLine().trim();
                if (argument.length()>=26) throw new NotInProvidedBoundsException();
                return argument;
            }catch (NotInProvidedBoundsException e){
                console.printErr("Упс! Похоже вы превысили возможные лимиты для почтового индекса");
                if (Console.getFileMode()) throw new InFileModeException();
            }catch (NullPointerException|IllegalStateException e){
                console.printErr("Возникла неизвестная ошибка");
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(0);
            }
        } while (true);


    }



    @Override
    public Address build() throws InvalidFormException, InFileModeException {
        var address = new Address(askZipCode());
        if(!address.validate()) throw new InvalidFormException();
        return address;
    }


}
