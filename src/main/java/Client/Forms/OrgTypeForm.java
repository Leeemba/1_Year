package Client.Forms;

import Client.utility.Console;
import Client.utility.Printable;
import Client.utility.ScriptConsole;
import Common.Utility.ConsoleFormat;

import Common.Exceptions.InFileModeException;
import Client.utility.ScannerManager;
import Common.Models.OrganizationType;


import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class OrgTypeForm implements  Form<OrganizationType>{
    private final Printable console;
    //private final UserInput input;

    public OrgTypeForm(Printable console) {
        this.console = Console.getFileMode()
                ? new ScriptConsole()
                : console;
       /* this.input = Console.getFileMode()
                ? new ScriptMode()
                : new ConsoleInput();*/
    }

    @Override
    public OrganizationType build()throws InFileModeException, NoSuchElementException {
        return askType();
    }

    public OrganizationType askType() throws InFileModeException{
        String argument;

        do {
            console.println(ConsoleFormat.coloring("Список доступных типов организации: "+OrganizationType.names(), ConsoleFormat.PURPLE, ConsoleFormat.ITALIC, ConsoleFormat.BOLD));
            console.print(ConsoleFormat.coloring("Выберите один из следующих типов организации: ", ConsoleFormat.YELLOW, ConsoleFormat.BOLD));
            Scanner scanner = ScannerManager.getScannerManager().getUserScanner();
            if(!scanner.hasNextLine()){
                throw new NoSuchElementException();
            }
            argument = scanner.nextLine().trim();
            argument = switch (argument) {
                case "0" -> "PUBLIC";
                case "1" -> "GOVERNMENT";
                case "2" -> "TRUST";
                default -> argument;
            };
            try {
                return OrganizationType.valueOf(argument.toUpperCase(Locale.ROOT));
            }catch (IllegalArgumentException|NullPointerException e){
                console.printErr("Такого типа организации нет в списке");
                if(Console.getFileMode()) throw new InFileModeException();
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка, кастанули запрещенное заклинание \"ctrl+d\"");
                System.exit(0);
            }
        }while (true);

    }


}
