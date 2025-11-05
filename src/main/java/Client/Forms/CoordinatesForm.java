package Client.Forms;

import Client.utility.Console;
import Client.utility.Printable;
import Client.utility.ScriptConsole;
import Common.Utility.ConsoleFormat;

import Common.Exceptions.InFileModeException;
import Common.Exceptions.InvalidFormException;
import Client.utility.ScannerManager;
import Common.Models.Coordinates;



import java.util.NoSuchElementException;
import java.util.Scanner;


public class CoordinatesForm implements Form<Coordinates>{
     private Printable console;
     //private UserInput input;


    public CoordinatesForm(Printable console){
        this.console = Console.getFileMode()
                ? new ScriptConsole()
                : console;
        /*this.input = Console.getFileMode()
                ? new ScriptMode()
                : new ConsoleInput();*/
    }


    @Override
    public Coordinates build() throws InvalidFormException,InFileModeException {
        var coordinates = new Coordinates(askX(),askY());
        if(!coordinates.validate()) throw  new InvalidFormException();
        return coordinates;
    }

    public int askX() throws  InFileModeException{
        int x;
        do{
            try {
                console.print(ConsoleFormat.coloring("Введите значение координаты \"x\" : ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                Scanner scanner = ScannerManager.getScannerManager().getUserScanner();
                if(!scanner.hasNextLine()){
                    throw new NoSuchElementException();
                }
                String argument = scanner.nextLine().trim();
                x = Integer.parseInt(argument);
                return x;
            }catch (NumberFormatException e){
                console.printErr("Неправильная форма представления элемента. \"x\" должно быть числом");
                if (Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(0);
            }

        }while (true);
    }

    public double askY() throws  InFileModeException{
        double y;
        do{
            try {
                console.print(ConsoleFormat.coloring("Введите значение координаты \"y\" : ",ConsoleFormat.YELLOW,ConsoleFormat.BOLD));
                Scanner scanner = ScannerManager.getScannerManager().getUserScanner();
                if(!scanner.hasNextLine()){
                    throw new NoSuchElementException();
                }
                String argument = scanner.nextLine().trim();
                argument = argument.replace(",",".");
                y = Double.parseDouble(argument);
                return y;

            }catch (NumberFormatException e){
                console.printErr("Неправильная форма представления элемента. \"y\" должно быть числом с плавающей запятой");
                if (Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(0);
            }
        }while (true);

    }





}
