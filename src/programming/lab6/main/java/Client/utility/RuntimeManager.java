package Client.utility;

import Client.Commands.*;
import Client.network.UDPClient;
import Common.Network.CommandNames;
import Common.Utility.ConsoleFormat;
import Common.Exceptions.*;
import Common.Utility.Hint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RuntimeManager {
    private final Printable console;
    private final Map<String, Command> commands;
    private final UDPClient client;
    private final List<String> scriptStack=new ArrayList<>();

    public RuntimeManager(Console console,UDPClient client){
        ScannerManager.getScannerManager().setUserScanner(new Scanner(System.in));
        this.console = console;
        this.client =client;

        this.commands = new HashMap<>(){{
                put(CommandNames.ADD,new Add(client,console));
                put(CommandNames.CLEAR,new Clear(console,client));
                put(CommandNames.EXECUTE_SCRIPT,new ExecuteScript(console,client));
                put(CommandNames.COUNT_GREATER_THAN_ANNUAL_TURNOVER,new CountGreaterThanAnnualTurnover(console,client));
                put(CommandNames.FILTER_BY_FULL_NAME,new FilterByFullName(console,client));
                put(CommandNames.GROUP_COUNTING_BY_FULL_NAME,new GroupByFullName(console,client));
                put(CommandNames.HEAD,new Head(console,client));
                put(CommandNames.HELP,new Help(console,client));
                put(CommandNames.EXIT,new Exit());
                put(CommandNames.SHOW,new Show(console,client));
                put(CommandNames.REMOVE_FIRST,new RemoveFirst(console,client));
                put(CommandNames.REMOVE_BY_ID,new RemoveById(console,client));
                put(CommandNames.UPDATE,new Update(console,client));
                put(CommandNames.HISTORY,new History(console,client));
                put(CommandNames.INFO,new Info(console,client));
        }};
    }



    public void interactiveMode(){
        String[] userCommand;
        Scanner userInput = ScannerManager.getScannerManager().getUserScanner();

        console.printHint(Hint.HELPHINT.toString());
        console.print(ConsoleFormat.coloring("Добро пожаловать! ",ConsoleFormat.WHITE,ConsoleFormat.BOLD));
        while (true){
            console.print(ConsoleFormat.coloring("Введите команду: ",ConsoleFormat.WHITE,ConsoleFormat.BOLD));
            try {
                userCommand = (userInput.nextLine().trim().toLowerCase(Locale.ROOT)+" ").split(" ",2);
                userCommand[1] = userCommand[1].trim();
                run(userCommand);
            }catch (IllegalArgumentException e){
                console.printErr(("Переданы неправильный аргументы"));
            }catch (NoSuchElementException e ){
                console.printErr("Ошибочка! Не ломайте лабу.На неё и так подорожник положили ");
                return;
            }catch (IncorrectCommandException e){
                console.printErr("Такой команды не существует или неправильно введено имя существующей команды");
                console.printHint(Hint.HELPHINT.toString());
            }catch (WrongAmountOfArgsException e){
                console.printErr("Введено неверное количество аргументов команды");
            }catch (ExitPoint e){
                console.println(ConsoleFormat.coloring("До встречи!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC,ConsoleFormat.BOLD));
                return;
            }
        }

    }



    public void scriptMode(String args){
        String[] userCommand;
        scriptStack.add(args);
        try (Scanner scriptScanner = new Scanner(new File(args))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner currentScanner = ScannerManager.getScannerManager().getUserScanner();
            ScannerManager.getScannerManager().setUserScanner(scriptScanner);
            Console.setFileMode(true);
            do {
                userCommand = (scriptScanner.nextLine().trim().toLowerCase(Locale.ROOT) + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()){
                    userCommand = (scriptScanner.nextLine().trim().toLowerCase(Locale.ROOT) + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                console.println(ConsoleFormat.coloring("Выполнение команды: "+userCommand[0],ConsoleFormat.CYAN,ConsoleFormat.BOLD));

                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].contains(script)) throw new ScriptRecursionException();
                    }
                }

                run(userCommand);
            } while (scriptScanner.hasNextLine());


            ScannerManager.getScannerManager().setUserScanner(currentScanner);
            Console.setFileMode(false);

        }catch (FileNotFoundException e) {
            console.printErr("Файл не найден!");
        }catch (NoSuchElementException e){
            console.printErr("Файл со скриптом пуст");
        }catch (ScriptRecursionException e) {
            console.printErr("Найдена рекурсия по пути выполнения скрипта");
        }catch (IncorrectCommandException e){
            console.printErr("Такой команды не существует или неправильно введено имя существующей команды");
            console.printHint(Hint.HELPHINT.toString());
        }catch (WrongAmountOfArgsException e){
            console.printErr("Введено неверное количество аргументов команды");
        }catch (ExitPoint e){
            console.println(ConsoleFormat.coloring("До встречи!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC,ConsoleFormat.BOLD));
            return;
        }finally {
            scriptStack.remove(scriptStack.size() - 1);
        }


    }


        public void run(String[] userCommand) throws IncorrectCommandException, WrongAmountOfArgsException,ExitPoint {
        var command = commands.get(userCommand[0]);
        if (command==null) throw new IncorrectCommandException();
        if (command.getName().equals("execute_script")){
            command.execute(userCommand[1]);
            scriptMode(userCommand[1]);
        }else {
            command.execute(userCommand[1]);
        }


    }




}
