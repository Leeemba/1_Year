package Client.Forms;

import Common.Utility.ConsoleFormat;

import Common.Exceptions.EmptyInputException;
import Common.Exceptions.InFileModeException;
import Common.Exceptions.InvalidFormException;
import Common.Exceptions.NotInProvidedBoundsException;
import Client.utility.ScannerManager;
import Common.Models.Address;
import Common.Models.Coordinates;
import Common.Models.Organization;
import Common.Models.OrganizationType;
import Client.utility.Console;
import Client.utility.Printable;
import Client.utility.ScriptConsole;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class OrganizationForm implements Form<Organization>{
    private Printable console;
    //private UserInput input;

    public OrganizationForm(Printable console){
        this.console = Console.getFileMode()
                ? new ScriptConsole()
                : console;
        /*this.input= Console.getFileMode()
                ? new ScriptMode()
                : new ConsoleInput();*/
    }

    @Override
    public Organization build() throws InvalidFormException,InFileModeException {
        var newOrg =  new Organization(
                1,
                askName(),
                LocalDate.now(),
                askCoordinates(),
                askAnnualTurnover(),
                askFullName(),
                askType(),
                askAddress()
        );
     if (!newOrg.validate()) throw new InvalidFormException();
     return newOrg;
    }




    public String askName() throws InFileModeException {
        do {
            try {
                console.print(ConsoleFormat.coloring("Введите название организации: ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                Scanner scanner = ScannerManager.getScannerManager().getUserScanner();
                if(!scanner.hasNextLine()){
                    throw new NoSuchElementException();
                }
                String argument = scanner.nextLine().trim();
                if(argument.isBlank()) throw new EmptyInputException();
                return argument;
            }catch (EmptyInputException e){
                console.printErr("Название не может быть пустым");
                if(Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(0);
            }
        }while (true);
    }


    public String askFullName()throws InFileModeException{
        do {
            try {
                console.print(ConsoleFormat.coloring("Введите полное название организации: ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                Scanner scanner = ScannerManager.getScannerManager().getUserScanner();
                if(!scanner.hasNextLine()){
                    throw new NoSuchElementException();
                }
                String argument = scanner.nextLine().trim();
                if(argument.isBlank()) throw new EmptyInputException();
                if (argument.length()>=818) throw new NotInProvidedBoundsException();
                return argument;
            }catch (EmptyInputException e){
                console.printErr("Полное название не может быть пустым");
                if(Console.getFileMode()) throw new InFileModeException();
            }catch (NotInProvidedBoundsException e){
                console.printErr("Упс! Похоже вы вышли за допустимые лимиты");
                if(Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(0);
            }
        }while (true);
    }

    public int askAnnualTurnover() throws InFileModeException{
        do {
            try {
                this.console.print(ConsoleFormat.coloring("Введите ежегодный оборот организации: ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                Scanner scanner = ScannerManager.getScannerManager().getUserScanner();
                if(!scanner.hasNextLine()){
                    throw new NoSuchElementException();
                }
                String argument = scanner.nextLine().trim();
                if (Integer.parseInt(argument)<0) throw new NotInProvidedBoundsException();
                return Integer.parseInt(argument);
            }catch (NotInProvidedBoundsException e){
                console.printErr("Упс! Похоже вы вышли за допустимые лимиты");
                if(Console.getFileMode()) throw new InFileModeException();
            }catch (NumberFormatException e){
                console.printErr("Ежегодный оборот должен быть представлен в формате int");
                if(Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(0);
            }
        }while (true);
    }


    public OrganizationType askType() throws InFileModeException{
        return new OrgTypeForm(console).build();
    }

    public Coordinates askCoordinates() throws InvalidFormException,InFileModeException{
        this.console.println(ConsoleFormat.coloring("Введите значение координат",ConsoleFormat.BLUE,ConsoleFormat.ITALIC,ConsoleFormat.BOLD));
        return new CoordinatesForm(console).build();
    }

    public Address askAddress() throws InvalidFormException,InFileModeException{
        return new AddressForm(console).build();
    }



}
